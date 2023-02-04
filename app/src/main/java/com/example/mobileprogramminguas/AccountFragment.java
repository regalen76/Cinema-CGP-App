package com.example.mobileprogramminguas;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileprogramminguas.Helper.RecyclerViewInterface;
import com.example.mobileprogramminguas.Helper.TheAdapterPembelian;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment implements RecyclerViewInterface {

    Button logOut;

    ImageView profileImage;
    TextView name, email;

    TheAdapterPembelian myAdapter;
    RecyclerView recyclerViewPembelian;
    List<Pembelian> pembelians = new ArrayList<Pembelian>();

    ProgressDialog progressDialog;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        setUp(view);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeToLoginPage();
            }
        });

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Database...");
        progressDialog.show();
        setupRecyclerViewPembelian(view);

        return view;
    }

    private void setUp(View view) {
        mDatabase = FirebaseDatabase.getInstance("https://mobileprogramminguas-f7872-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Pembelian");

        logOut = view.findViewById(R.id.logoutBtn);
        profileImage = view.findViewById(R.id.profileImage);
        name = view.findViewById(R.id.profileName);
        email = view.findViewById(R.id.profileEmail);

        Picasso.get().load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).into(profileImage);
        name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        email.setText("Email: " + FirebaseAuth.getInstance().getCurrentUser().getEmail());
    }

    private void setupRecyclerViewPembelian(View view) {
        pembelians.clear();

        recyclerViewPembelian = view.findViewById(R.id.historyContainer);
        myAdapter =  new TheAdapterPembelian(this,getActivity().getApplicationContext(),pembelians);

        recyclerViewPembelian.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewPembelian.setAdapter(myAdapter);

        mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Pembelian pembelian = dataSnapshot.getValue(Pembelian.class);
                    pembelians.add(pembelian);
                }
                progressDialog.dismiss();
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("key", pembelians.get(position).getInvoice());

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.changeToCompletedPage(bundle);
    }
}
package com.example.mobileprogramminguas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {

    ImageView image;
    TextView title,plot,genres,duration,release,rating;
    Button rent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        setUp(view);

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FirebaseAuth.getInstance().getCurrentUser() == null){ //jika gak login
                    Toast.makeText(getActivity(),"You must login first",Toast.LENGTH_LONG).show();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.changeToLoginPage();
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title.getText().toString());
                    bundle.putString("duration", duration.getText().toString());

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.changeToPaymentPage(bundle);
                }
            }
        });

        return view;
    }

    private void setUp(View view) {
        image = view.findViewById(R.id.imageDetails);
        title = view.findViewById(R.id.titleDetails);
        plot = view.findViewById(R.id.plotDetails);
        genres = view.findViewById(R.id.genresDetails);
        duration = view.findViewById(R.id.runtimeDetails);
        release = view.findViewById(R.id.releaseDetails);
        rating = view.findViewById(R.id.ratingDetails);
        rent = view.findViewById(R.id.rentStudio);

        Bundle bundle = this.getArguments();

        Picasso.get().load(bundle.getString("image")).resize(1700, 2500).onlyScaleDown().into(image);
        title.setText("Title: "+bundle.getString("title"));
        plot.setText(bundle.getString("plot"));
        genres.setText("Genres: "+bundle.getString("genres"));
        duration.setText("Duration: "+bundle.getString("duration"));
        release.setText("Release Date: "+bundle.getString("date"));
        rating.setText("Movie Rating: "+bundle.getString("rating"));
    }
}
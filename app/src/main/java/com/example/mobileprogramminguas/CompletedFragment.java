package com.example.mobileprogramminguas;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class CompletedFragment extends Fragment {

    TextView invoiceText, itemText, durationText, paymentText;
    ImageView qrImage;

    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        setData(view);

        return view;
    }

    private void setData(View view) {
        String[] trueData = new String[4];

        Bundle bundle = this.getArguments();
        String data = bundle.getString("key");

        mDatabase = FirebaseDatabase.getInstance("https://mobileprogramminguas-f7872-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Pembelian");
        mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(data).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().child("invoice").exists()){   //jika invoice benar
                        DataSnapshot dataSnapshot = task.getResult();
                        trueData[0] = String.valueOf(dataSnapshot.child("invoice").getValue());
                        trueData[1] = String.valueOf(dataSnapshot.child("title").getValue());
                        trueData[2] = String.valueOf(dataSnapshot.child("duration").getValue());
                        trueData[3] = String.valueOf(dataSnapshot.child("method").getValue());

                        setTextView(view, trueData);
                    }else{
                        Toast.makeText(getActivity(),"Failed To Find The Invoice",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Failed To Read",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setTextView(View view, String[] data) {
        invoiceText = view.findViewById(R.id.invoiceText);
        itemText = view.findViewById(R.id.itemNameText);
        durationText = view.findViewById(R.id.itemDurationText);
        paymentText = view.findViewById(R.id.paymentMethodText);
        qrImage = view.findViewById(R.id.qrimage);

        invoiceText.setText("Invoice: " + data[0]);
        itemText.setText(data[1]);
        durationText.setText(data[2]);
        paymentText.setText("Payment Method: " +data[3]);

        //generate QR
        MultiFormatWriter writer = new MultiFormatWriter();
        try{
            BitMatrix matrix = writer.encode(data[0], BarcodeFormat.QR_CODE, 800,800);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
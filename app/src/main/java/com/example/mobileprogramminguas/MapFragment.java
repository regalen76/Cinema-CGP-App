package com.example.mobileprogramminguas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap googleMap;

    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        setRadio(view);
        radioFunc();

        if(checkGPService()){  //check Google Play Service
            SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().add(R.id.map_layout,supportMapFragment).commit();
            supportMapFragment.getMapAsync((OnMapReadyCallback) this);
        }else {
            Toast.makeText(getActivity(),"Google playservices not avaible", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        radioGroup.check(R.id.radioBtn1);
    }

    private void setRadio(View view) {
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.radioBtn1);
        radioButton2 = view.findViewById(R.id.radioBtn2);
    }

    private void radioFunc() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioBtn1){
                    LatLng latLng = new LatLng(-6.193924061113853,106.78813220277623);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                    googleMap.animateCamera(cameraUpdate);
                }else if(i == R.id.radioBtn2){
                    LatLng latLng = new LatLng(-6.20175020412279,106.78223868546155);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
                    googleMap.animateCamera(cameraUpdate);
                }
            }
        });
    }

    private boolean checkGPService() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int result = googleApiAvailability.isGooglePlayServicesAvailable(getActivity());
        if(result == ConnectionResult.SUCCESS){
            return true;
        } else if (googleApiAvailability.isUserResolvableError(result)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(this, result, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Toast.makeText(getActivity(),"User Cancelled Dialogue", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }
        return false;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        LatLng latLng = new LatLng(-6.193924061113853,106.78813220277623);
        LatLng latLng2 = new LatLng(-6.20175020412279,106.78223868546155);

        MarkerOptions markerOptions = new MarkerOptions();
        MarkerOptions markerOptions2 = new MarkerOptions();

        markerOptions.position(latLng);
        markerOptions.title("Cinema CGP Alpha");
        markerOptions2.position(latLng2);
        markerOptions2.title("Cinema CGP Beta");

        googleMap.clear();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions2);
    }
}
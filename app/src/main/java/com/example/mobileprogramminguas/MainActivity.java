package com.example.mobileprogramminguas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    LoginFragment loginFragment = new LoginFragment();
    AccountFragment accountFragment = new AccountFragment();
    MapFragment mapFragment = new MapFragment();
    DetailsFragment detailsFragment = new DetailsFragment();
    PaymentFragment paymentFragment = new PaymentFragment();
    CompletedFragment completedFragment = new CompletedFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBottonNavigationFragment();
    }

    private void getBottonNavigationFragment() {
        bottomNavigationView = findViewById(R.id.bot_nav);

        getSupportFragmentManager().beginTransaction().replace(R.id.containers,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers,homeFragment).commit();
                        return true;
                    case R.id.location:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers,mapFragment).commit();
                        return true;
                    case R.id.account:
                        if (FirebaseAuth.getInstance().getCurrentUser()!=null) //check jika sudah login ato belom
                        {
                            Log.d("testd",FirebaseAuth.getInstance().getCurrentUser().getEmail() + "-" + FirebaseAuth.getInstance().getCurrentUser().getDisplayName() + "-" + FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl());
                            getSupportFragmentManager().beginTransaction().replace(R.id.containers, accountFragment).commit();
                            return true;
                        }else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.containers, loginFragment).commit();
                            return true;
                        }
                }
                return false;
            }
        });
    }

    public void changeToMoviePage(Bundle bundle){
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,detailsFragment).commit();
    }

    public void changeToLoginPage(){
        bottomNavigationView.setSelectedItemId(R.id.account);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,loginFragment).commit();
    }

    public void changeToHomePage(){
        bottomNavigationView.setSelectedItemId(R.id.home);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,homeFragment).commit();
    }

    public void changeToPaymentPage(Bundle bundle){
        paymentFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,paymentFragment).commit();
    }

    public void changeToCompletedPage(Bundle bundle){
        completedFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.containers,completedFragment).commit();
    }
}
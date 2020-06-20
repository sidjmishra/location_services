package com.example.locationservices;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    Button fetch;
    TextView user_location;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetch = findViewById(R.id.button);
        user_location = findViewById(R.id.textview);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                            @SuppressLint("SetTextI18n")
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();

                                    user_location.setText("Latitude:" + latitude + "\nLongitude:" + longitude);
                                }
                            }
                        });
            }
        });
    }
}

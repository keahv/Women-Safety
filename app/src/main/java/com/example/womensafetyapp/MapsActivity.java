package com.example.womensafetyapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int Request_code = 101;
    private double lat, lng;
    private ImageButton atm,bank,res,hosp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        atm =findViewById(R.id.atm);
        res =findViewById(R.id.res);
        bank =findViewById(R.id.bank);
        hosp =findViewById(R.id.hospital);

        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this.getApplicationContext());

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);


//        GoogleMapOptions options = new GoogleMapOptions();
//
//        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
//                .compassEnabled(false)
//                .rotateGesturesEnabled(false)
//                .tiltGesturesEnabled(false);

        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=atm");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);
            }
        });


        hosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=hospital");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=resturants");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);
            }
        });

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder stringBuilder = new StringBuilder
                        ("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                stringBuilder.append("location=" + lat + "," + lng);
                stringBuilder.append("&radius=1000");
                stringBuilder.append("&type=bank");
                stringBuilder.append("&sensor=true");
                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_key));

                String url = stringBuilder.toString();
                Object dataFetch[] = new Object[2];
                dataFetch[0]=mMap;
                dataFetch[1]=url;

                FetchData fetchData = new FetchData();
                fetchData.execute(dataFetch);
            }
        });
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions
                    (this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_code);
            return;
        }

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(60000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(5000);
        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
//                Toast.makeText(getApplicationContext(), "location result is=" + locationResult
//                        , Toast.LENGTH_SHORT).show();

                if (locationResult == null){
                    Toast.makeText(getApplicationContext(), "Current location is null",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Location location: locationResult.getLocations()){

                    if (location != null){
//                        Toast.makeText(getApplicationContext(), "Current location is",
//                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                 if (location != null){

                     lat = location.getLatitude();
                     lng= location.getLongitude();

                     LatLng latLng= new LatLng(lat, lng);
                     mMap.addMarker(new MarkerOptions().position(latLng).title("current location"));
                     mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                     mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                 }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getCurrentLocation();

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      switch (Request_code){

          case Request_code:
              if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                  getCurrentLocation();
              }
      }
    }
}
package com.example.utscalvin2201792796;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS=23;
    private GoogleMap mMap;
    Button findBtn;
    Spinner spinnerBtn;

    private String[] storeName = {
            "Kemanggisan Store",
            "Alam Sutera Store"
    };
    LatLng myLoc;
    TextView disctanceTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        init();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, storeName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBtn.setAdapter(adapter);
        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myLoc != null){
                    moveZoom( spinnerBtn.getSelectedItem().toString() );
                }
            }
        });
        checkPermission();
    }

    private void init(){
        findBtn = findViewById(R.id.findBtn);
        spinnerBtn = (Spinner)findViewById(R.id.spinnerBtn);
        disctanceTxt = findViewById(R.id.disctanceTxt);
    }

    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS );
        }
        else{
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if(grantResults.length > 0
                      && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
                else{
                    //permission denied! Dismiss the functionality that depends on this permission
                }
                return;
            }
        }
    }

    private void moveZoom(String address) {
        switch (address){
            case "Kemanggisan Store":{
                LatLng jktBinus = new LatLng(-6.200626786640842, 106.78510782104438);
                final MarkerOptions a = new MarkerOptions();
                a.position(jktBinus);
                a.title("Kemanggisan Store");
                CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(jktBinus, 15);
                mMap.animateCamera(cameraUpdate);
                double hasil = CalculationByDistance(jktBinus, myLoc);
                hasil = Math.round(hasil * 100.0) / 100.0;
                String hy = hasil + " Km";
                disctanceTxt.setText(hy);
                break;
            }
            case "Alam Sutera Store":{
                LatLng alsBinus = new LatLng(-6.2232004023710585, 106.6488380704966);
                final MarkerOptions b = new MarkerOptions();
                b.position(alsBinus);
                b.title("Alam Sutera Store");
                CameraUpdate cameraUpdate= CameraUpdateFactory.newLatLngZoom(alsBinus, 15);
                mMap.animateCamera(cameraUpdate);
                double hasil = CalculationByDistance(alsBinus, myLoc);
                hasil = Math.round(hasil * 100.0) / 100.0;
                String hy = hasil + " Km";
                disctanceTxt.setText(hy);
                break;
            }
        }
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        LatLng jktBinus = new LatLng(-6.200626786640842, 106.78510782104438);
        final MarkerOptions a = new MarkerOptions();
        a.position(jktBinus);
        a.title("Kemanggisan Store");
        LatLng alsBinus = new LatLng(-6.2232004023710585, 106.6488380704966);
        final MarkerOptions b = new MarkerOptions();
        b.position(alsBinus);
        b.title("Alam Sutera Store");
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                jktBinus, 15);
        mMap.animateCamera(cameraUpdate);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLoc = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(myLoc);

                markerOptions.title("Your Location");
                mMap.clear();
                mMap.addMarker(markerOptions);
                mMap.addMarker(a);
                mMap.addMarker(b);
            }
        });
    }
}
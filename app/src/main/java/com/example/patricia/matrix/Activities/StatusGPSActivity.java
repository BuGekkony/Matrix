package com.example.patricia.matrix.Activities;

import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.patricia.matrix.AlertDialogActivities.AlertDialogActivities;
import com.example.patricia.matrix.Constants.ConstantsVideo;
import com.example.patricia.matrix.R;

public class StatusGPSActivity extends AppCompatActivity {

    private final String TAG = StatusGPSActivity.class.getSimpleName();
    private ImageButton imageButtonNext;
    private ImageButton imageButtonGPS;
    private LocationManager locationManager;
    private AlertDialogActivities alertDialogActivities;
    private Intent intent;
    private boolean gpsIsEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_gps);
        imageButtonGPS = (ImageButton) findViewById(R.id.imagebutton_status_gps);
        imageButtonNext = (ImageButton) findViewById(R.id.imagebutton_next_status_gps);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        alertDialogActivities = new AlertDialogActivities(StatusGPSActivity.this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void checkStatusGPS(View view) {
        gpsIsEnabled = gpsIsEnabled();
        if ( gpsIsEnabled ) {
            Toast.makeText(StatusGPSActivity.this, "GPS ESTÁ HABILITADO.", Toast.LENGTH_SHORT).show();
        } else {
            alertDialogActivities.alertDialogOpenGPS();
        }
    }

    public void nextStatusGPS(View view) {
        gpsIsEnabled = gpsIsEnabled();
        if ( gpsIsEnabled ) {
            intent = new Intent(StatusGPSActivity.this, VideoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(StatusGPSActivity.this, "HABILITE O GPS DE SEU SMARTPHONE PARA PROSSEGUIR.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean gpsIsEnabled() {
        boolean statusGPS;
        statusGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d(TAG, "STATUS GPS: " + statusGPS);
        return statusGPS;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
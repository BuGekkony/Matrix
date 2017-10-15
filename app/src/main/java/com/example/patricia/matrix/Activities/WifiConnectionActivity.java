package com.example.patricia.matrix.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.patricia.matrix.ConnectionWifi.ConnectionWifi;
import com.example.patricia.matrix.R;

import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;

public class WifiConnectionActivity extends AppCompatActivity {

    private final String TAG = WifiConnectionActivity.class.getSimpleName();
    private ImageButton imageButtonNext;
    private ImageButton imageButtonWifi;
    private ConnectionWifi connectionWifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ON CREATE.");
        setContentView(R.layout.activity_wifi_connection);
        imageButtonWifi = (ImageButton) findViewById(R.id.imagebutton_wifi_connection);
        imageButtonNext = (ImageButton) findViewById(R.id.imagebutton_next_wifi_connection);
        connectionWifi = new ConnectionWifi(WifiConnectionActivity.this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ON START.");
    }

    public void checkWifiConnection(View view) {
        Log.d(TAG, "MÉTODO CHECK WIFI CONNECTION.");
        connectionWifi.testWifiConnectionWithDialogProgress(Hero4BlackCommands.status);
    }

    public void nextWifiConnection(View view) {
        Log.d(TAG, "MÉTODO NEXT WIFI CONNECTION.");
        connectionWifi.testWifiConnection(Hero4BlackCommands.status);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ON STOP.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ON DESTROY.");
    }
}
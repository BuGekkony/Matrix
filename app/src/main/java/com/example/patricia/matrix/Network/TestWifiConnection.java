package com.example.patricia.matrix.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.example.patricia.matrix.AlertDialogActivities.AlertDialogActivities;
import com.example.patricia.matrix.Constants.ConstantsVideo;

public class TestWifiConnection {

    private final String TAG = TestWifiConnection.class.getSimpleName();
    private AlertDialogActivities alertDialogActivities;
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private WifiManager wifiManager;
    private WifiInfo wifiInfo;
    private String ipAddress;
    private String macAddress;
    private boolean statusWifi;
    private int IP;


    public TestWifiConnection(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        wifiInfo = wifiManager.getConnectionInfo();
        networkInfo = connectivityManager.getActiveNetworkInfo();
        statusWifi = wifiManager.isWifiEnabled();
        IP = wifiInfo.getIpAddress();
        ipAddress = String.format("%d.%d.%d.%d", (IP & 0xff), (IP >> 8 & 0xff), (IP >> 16 & 0xff), (IP >> 24 & 0xff));
        macAddress = wifiInfo.getMacAddress();
        Log.d(TAG, "MAC ADDRESS " + macAddress);
        alertDialogActivities = new AlertDialogActivities(context);
    }

    public boolean wifiGoProIsEnabled() {
        Log.d(TAG, "MÉTODO WIFI GOPRO IS ENABLED.");
        if ( (networkInfo != null) && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) && (ipAddress.equals(ConstantsVideo.IP_GOPRO) ) && (statusWifi == true) ) {
            Log.d(TAG, "WIFI GOPRO CONECTADO.");
            return true;
        }
        else {
            Log.d(TAG, "WIFI GOPRO DESCONECTADO.");
            return false;
        }
    }

    public void testWifiConnectionGoPro(Context context) {
        Log.d(TAG, "MÉTODO TEST WIFI CONNECTION GOPRO.");
        if ( (networkInfo != null) && (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) && (ipAddress.equals(ConstantsVideo.IP_GOPRO) ) && (statusWifi == true) ) {
            Log.d(TAG, "WIFI GOPRO CONECTADO.");
            Toast.makeText(context, "WIFI GOPRO CONECTADO.", Toast.LENGTH_SHORT).show();
        }
        else {
            alertDialogActivities.alertDialogWifiConnection();
        }
    }
}
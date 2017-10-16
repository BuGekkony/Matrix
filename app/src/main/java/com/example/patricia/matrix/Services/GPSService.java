package com.example.patricia.matrix.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.example.patricia.matrix.Activities.VideoActivity;
import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GPSService extends Service implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private final String TAG = GPSService.class.getSimpleName();
    private final String messageStartService = "Serviço Matrix em execução.";
    private String lastUpdateTime;
    private static boolean isServiceRunning;
    private boolean startRecording;
    private SimpleDateFormat simpleDateFormat;
    private final int INTERVAL = 1000;
    private final int FASTEST_INTERVAL = 1000;
    private int notificationId = 77144;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location currentLocation;
    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private Map<String, String> map;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "ON CREATE.");
        isServiceRunning = false;
        startRecording = false;
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
        jsonObject = new JSONObject();
        jsonArray = new JSONArray();
        map = new HashMap<>();
        handler = new Handler();
    }

    public void sendToast(String message) {
        Log.d(TAG, "MÉTODO SEND TOAST.");
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void buildJSON_Object() {
        Log.d(TAG, "MÉTODO BUILD JSON OBJECT. LENGTH JSON ARRAY:  " + jsonArray.length());
        try {
            jsonObject.put("Data", jsonArray);
        }catch(JSONException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "ON START COMMAND.");
        GPSService.setIsServiceRunning(true);
        startRecording = true;
        buildGoogleApiClient();
        connectGoogleApiClient();
        if ( googleApiClient.isConnected() ) {
            startLocationUpdates();
        }
        return START_NOT_STICKY;
    }

    public void updateNotification(String text) {

        Log.d(TAG, "UPDATE NOTIFICATION.");

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(GPSService.this)
                        .setSmallIcon(R.drawable.service)
                        .setContentTitle("Matrix")
                        .setContentText(text);

        Intent resultIntent = new Intent(GPSService.this, VideoActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(GPSService.this);
        stackBuilder.addParentStack(VideoActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification notification = mBuilder.build();
        mNotificationManager.notify(notificationId, notification);
    }

    public void cancelNotification() {
        Log.d(TAG, "CANCEL NOTIFICATION.");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(notificationId);
    }

    public synchronized void buildGoogleApiClient() {
        Log.d(TAG, "BUILD GOOGLE API CLIENT.");
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    public void createLocationRequest() {
        Log.d(TAG, "CREATE LOCATION REQUEST.");
        locationRequest = new LocationRequest();
        locationRequest.setInterval(this.INTERVAL);
        locationRequest.setFastestInterval(this.FASTEST_INTERVAL);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void connectGoogleApiClient() {
        Log.d(TAG, "CONNECT GOOGLE API CLIENT.");
        if ( googleApiClient != null ) {
            if ( !(googleApiClient.isConnected()) ) {
                googleApiClient.connect();
            }
        }
    }

    public void disconnectGoogleApiClient() {
        Log.d(TAG, "DISCONNECT GOOGLE API CLIENT.");
        if ( googleApiClient != null ) {
            if ( googleApiClient.isConnected() ) {
                googleApiClient.disconnect();
            }
        }
    }

    public static boolean isServiceRunning() {
        return (GPSService.isServiceRunning);
    }

    public static void setIsServiceRunning(boolean isServiceRunning) {
        GPSService.isServiceRunning = isServiceRunning;
    }

    public void writeDataJSON_Array(Location location) {
        Log.d(TAG, "MÉTODO WRITE DATA JSON_ARRAY.");
        try {
            JSONObject localJsonObject = new JSONObject();
            lastUpdateTime = simpleDateFormat.format(new Date()).toString();
            localJsonObject.put("Time", lastUpdateTime);
            localJsonObject.put("Latitude", String.valueOf(location.getLatitude()));
            localJsonObject.put("Longitude", String.valueOf(location.getLongitude()));
            localJsonObject.put("Speed", String.valueOf(location.getSpeed()));
            jsonArray.put(localJsonObject);
        } catch (JSONException exception) {
            exception.printStackTrace();
            Log.e(TAG, "EXCEÇÃO WRITE DATA JSON OBJECT: ", exception);
        }
    }

    public void startLocationUpdates() {
        Log.d(TAG, "START LOCATION UPDATES.");
        if ( googleApiClient != null ) {
            if ( googleApiClient.isConnected() ) {
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
            } else {
                connectGoogleApiClient();
            }
        }
    }

    public void stopLocationUpdates() {
        Log.d(TAG, "STOP LOCATION UPDATES.");
        if ( googleApiClient != null ) {
            if( googleApiClient.isConnected() ) {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "ON CONNECTED.");
        if ( currentLocation == null ) {
            currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if ( currentLocation != null ) {
                Log.d(TAG, "LATITUDE : " + currentLocation.getLatitude() + " LONGITUDE : " + currentLocation.getLongitude());
            }
        }
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "ON CONNECTION SUSPENDED.");
        if (!(googleApiClient.isConnected())) {
            googleApiClient.reconnect();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "ON CONNECTION FAILED.");
        if (!(googleApiClient.isConnected())) {
            googleApiClient.reconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.d(TAG, "ON LOCATION CHANGED.");

        if ( startRecording ) {
            VideoActivity.setFragmentSurfaceView(Hero4BlackCommands.status);
            updateNotification(messageStartService);
            NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.setStartCamera, "COMANDO PARA INICIAR GRAVAÇÃO DE VÍDEO ENVIADO. SERVIÇO INICIADO.");
            writeDataJSON_Array(location);
            startRecording = false;
        }
        else {
            writeDataJSON_Array(location);
          }
    }

    public void executeHandler() {
        Log.d(TAG, "MÉTODO EXECUTE HANDLER.");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sendToast("OS DADOS DE GPS SERÃO ENVIADOS VIA WIFI. AGUARDE POR FAVOR.");
                buildJSON_Object();
                NetworkVolley.sendGPS_Data_JSONObject(Hero4BlackCommands.sendGPS_Data, jsonObject);
                VideoActivity.setFragmentVideo(Hero4BlackCommands.status);
                GPSService.setIsServiceRunning(false);
                cancelNotification();
            }
        }, 1500);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "ON DESTROY.");
        stopLocationUpdates();
        disconnectGoogleApiClient();
        NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.setStopCamera, "COMANDO PARA ENCERRAR GRAVAÇÃO DE VÍDEO ENVIADO.");
        executeHandler();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
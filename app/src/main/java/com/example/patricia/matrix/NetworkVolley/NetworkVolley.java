package com.example.patricia.matrix.NetworkVolley;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.patricia.matrix.Camera.Camera;
import com.example.patricia.matrix.Notifications.Notifications;

import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class NetworkVolley extends Application {

    private static final String TAG = NetworkVolley.class.getSimpleName();
    private static Context context;
    private static NetworkVolley networkVolley;
    private static Notifications notifications;
    private static RequestQueue requestQueue;
    private static StringRequest stringRequest;
    private static JsonObjectRequest jsonObjectRequest;
    private static Camera camera;

    @Override
    public void onCreate() {
        super.onCreate();
        networkVolley = NetworkVolley.this;
        context = getApplicationContext();
        notifications = new Notifications(context);
        requestQueue = Volley.newRequestQueue(NetworkVolley.this);
        camera = new Camera(context);
    }

    public synchronized static NetworkVolley getInstance() {
        return networkVolley;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static void sendCommand(String command) {

        Log.d(TAG, "MÉTODO SEND COMMAND.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "REQUISIÇÃO REALIZADA COM SUCESSO. RESPOSTA: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "SEND COMMAND: " + error.toString());
                notifications.errorConnection();
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    public static void sendGPS_Data_JSONObject(String command, JSONObject jsonObject) {

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, command, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "RESPONSE:  " + response.toString());
                        Toast.makeText(context, "DADOS DE GPS ENVIADOS COM SUCESSO. SERVIÇO ENCERRADO.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "SEND GPS_DATA:  " + error.toString());
                notifications.errorSendDataGPS();
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(jsonObjectRequest);
    }

    public static void sendCommandWithMessage(String command, final String message) {

        Log.d(TAG, "MÉTODO SEND COMMAND WITH MESSAGE.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "SEND COMMAND WITH MESSAGE:  " + error.toString());
                notifications.errorConnection();
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }
}
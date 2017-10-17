package com.example.patricia.matrix.Camera;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.patricia.matrix.AlertDialogActivities.AlertDialogActivities;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.Services.GPSService;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("deprecation")
public class Camera {

    private final String TAG = Camera.class.getSimpleName();
    private final String CATEGORY_STATUS = "status";
    private String status;
    private String timeVideoRemaining;
    private String isRecording;
    private boolean serviceIsRunning;
    private double time_remaining;
    private double limit;
    private AlertDialogActivities alertDialogActivities;
    private Context context;
    private StringRequest stringRequest;

    public Camera(Context context) {
        Log.d(TAG, "CONSTRUTOR.");
        this.context = context;
        alertDialogActivities = new AlertDialogActivities(context);
        serviceIsRunning = false;
        limit = 600.0;
    }

    public void sendMessageError() {
        alertDialogActivities.alertDialogErrorSendStart();
        context.stopService(new Intent(context, GPSService.class));
    }

    public void sendCommandStartCameraGoPro(String command, final String message) {

        Log.d(TAG, "MÉTODO SEND COMMAND START CAMERA GOPRO.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "RESPONSE: " + response);
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "SEND COMMAND START CAMERA GOPRO:  " + error.toString());
                sendMessageError();
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    public void SDCard(String command) {

        Log.d(TAG, "SD CARD.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            status = String.valueOf(Html.fromHtml(response));

                            timeVideoRemaining = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("35");
                            isRecording = new JSONObject(status).getJSONObject("status").getString("8");
                            time_remaining = Double.parseDouble(timeVideoRemaining);
                            serviceIsRunning = GPSService.isServiceRunning();

                            if( ( time_remaining < limit ) && ( serviceIsRunning ) ) {
                                alertDialogActivities.alertDialogSDCard();
                                context.stopService(new Intent(context, GPSService.class));
                            }

                            /*
                            Se serviço GPS está ativo e gravação é igual a falso
                            ocorreu erro de cartão SD
                             */

                            /*
                            if( (isRecording.equals("0")) && (serviceIsRunning) ) {
                                alertDialogActivities.alertDialogErrorSDCard();
                                context.stopService(new Intent(context, GPSService.class));
                            }
                            */
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO MÉTODO SD CARD: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO SD CARD. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }
}

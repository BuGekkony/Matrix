package com.example.patricia.matrix.ConnectionWifi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.patricia.matrix.Activities.StatusGPSActivity;
import com.example.patricia.matrix.Activities.VideoActivity;
import com.example.patricia.matrix.AlertDialogActivities.AlertDialogActivities;
import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;

@SuppressWarnings("deprecation")
public class ConnectionWifi {

    private Context context;
    private StringRequest stringRequest;
    private final String TAG = ConnectionWifi.class.getSimpleName();
    private AlertDialogActivities alertDialogActivities;
    private ProgressDialog dialog;
    private Intent intent;

    public ConnectionWifi(Context context) {
        this.context = context;
        alertDialogActivities = new AlertDialogActivities(context);
        dialog = new ProgressDialog(context);
    }

    public void testWifiConnectionWithDialogProgress(String command) {

        Log.d(TAG, "MÉTODO TEST WIFI CONNECTION WITH DIALOG PROGRESS.");

        dialog.setMessage("AGUARDE ENQUANTO A CONEXÃO WIFI É VERIFICADA ...");
        dialog.show();

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context, "WIFI GOPRO HERO CONECTADO.", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "ERROR RESPONSE: " + error.toString());
                dialog.dismiss();
                alertDialogActivities.alertDialogWifiConnection();
            }
        });
        //Volley.newRequestQueue(context).add(stringRequest);
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    public void testWifiConnection(String command) {

        Log.d(TAG, "MÉTODO TEST WIFI CONNECTION.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        intent = new Intent(context, StatusGPSActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "ERROR RESPONSE: " + error.toString());
                alertDialogActivities.alertDialogWifiConnection();
            }
        });
        //Volley.newRequestQueue(context).add(stringRequest);
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }
}

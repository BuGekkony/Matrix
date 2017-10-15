package com.example.patricia.matrix;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.patricia.matrix.Activities.WifiConnectionActivity;

public class IntroductoryActivity extends AppCompatActivity {

    private final String TAG = IntroductoryActivity.class.getSimpleName();
    private AlertDialog.Builder alertDialog;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        messageInitialIntroductoryActivity();
    }

    public void callWifiActivity(View view) {
        intent = new Intent(IntroductoryActivity.this, WifiConnectionActivity.class);
        startActivity(intent);
    }

    private void messageInitialIntroductoryActivity() {

        Log.d(TAG, "MENSAGEM INICIAL.");

        alertDialog = new AlertDialog.Builder(IntroductoryActivity.this);

        alertDialog.setTitle("Instruções")
                .setMessage(R.string.message_introductory_activity)
                .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        intent = new Intent(IntroductoryActivity.this, WifiConnectionActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Fechar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                }).show();
    }
}
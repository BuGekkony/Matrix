package com.example.patricia.matrix.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.R;

public class SettingsAnglesActivity extends AppCompatActivity {

    private final String TAG = SettingsAnglesActivity.class.getSimpleName();
    private final String SET_GIMBAL = "http://10.5.5.128/setGimbal/";
    private final String SET_S1 = "http://10.5.5.128/setS1/";
    private final String SET_S2 = "http://10.5.5.128/setS2/";
    private String URL;
    private Button buttonSendS1;
    private Button buttonSendS2;
    private Button buttonSendGB;
    private Button buttonClearEditText;
    private EditText editTextS1;
    private EditText editTextS2;
    private EditText editTextGB;
    private String S1;
    private String S2;
    private String GB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_angles);

        buttonSendS1 = (Button) findViewById(R.id.button_send_s1);
        buttonSendS2 = (Button) findViewById(R.id.button_send_s2);
        buttonSendGB = (Button) findViewById(R.id.button_send_gb);
        buttonClearEditText = (Button) findViewById(R.id.button_clear);

        editTextS1 = (EditText) findViewById(R.id.edit_text_s1);
        editTextS2 = (EditText) findViewById(R.id.edit_text_s2);
        editTextGB = (EditText) findViewById(R.id.edit_text_gb);

        SettingsAnglesActivity.this.setFinishOnTouchOutside(false);
    }

    public void clearEditText(View view) {
        editTextS1.setText(" ");
        editTextS2.setText(" ");
        editTextGB.setText(" ");
    }

    public void sendS1(View view) {

        Log.d(TAG, "MÉTODO SEND S1.");

        S1 = editTextS1.getText().toString();
        URL = SET_S1 + S1;
        NetworkVolley.sendCommandWithMessage(URL, "ENVIADO VALOR PARA SENSOR DE NÚMERO 1.");
    }

    public void sendS2(View view) {

        Log.d(TAG, "MÉTODO SEND S2.");

        S2 = editTextS2.getText().toString();
        URL = SET_S2 + S2;
        NetworkVolley.sendCommandWithMessage(URL, "ENVIADO VALOR PARA SENSOR DE NÚMERO 2.");
    }

    public void sendGB(View view) {

        Log.d(TAG, "MÉTODO SEND GB.");

        GB = editTextGB.getText().toString();
        URL = SET_GIMBAL + GB;
        NetworkVolley.sendCommandWithMessage(URL, "ENVIADO VALOR PARA GIMBAL.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.example.patricia.matrix.DialogsVideo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


import android.util.Log;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;

public class Sharpness extends DialogFragment {

    private String TAG;
    private String[] videoSharpness;

    public Sharpness() {
        videoSharpness = new String[]{"Alto", "Médio", "Baixo"};
        TAG = Sharpness.class.getSimpleName();
    }

    public static Sharpness newInstance() {
        return new Sharpness();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Nitidez")
                .setSingleChoiceItems(videoSharpness, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO SHARPNESS HIGHT");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.sharpnessVideoHigh, "NITIDEZ ALTA");
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO SHARPNESS MEDIUM");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.sharpnessVideoMedium, "NITIDEZ MÉDIA");
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO SHARPNESS LOWER");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.sharpnessVideoLower, "NITIDEZ BAIXA");
                                break;
                        }
                    }
                }).setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}

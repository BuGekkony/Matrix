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

public class Shutter24FPS extends DialogFragment {

    private String TAG;
    private String[] videoShutter;

    public Shutter24FPS() {
        videoShutter = new String[]{"Automático", "1/24", "1/48", "1/96"};
        TAG = Shutter24FPS.class.getSimpleName();
    }

    public static Shutter24FPS newInstance() {
        return new Shutter24FPS();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Obturador")
                .setSingleChoiceItems(videoShutter, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO SHUTTER AUTO");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_Auto, "OBTURADOR AUTOMÁTICO");
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO SHUTTER 1/24");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_1_24, "OBTURADOR 1/24");
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO SHUTTER 1/48");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_1_48, "OBTURADOR 1/48");
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO SHUTTER 1/96");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_1_96, "OBTURADOR 1/96");
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

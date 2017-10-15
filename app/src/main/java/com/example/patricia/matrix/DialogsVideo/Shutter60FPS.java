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

public class Shutter60FPS extends DialogFragment {

    private String TAG;
    private String[] videoShutter;

    public Shutter60FPS() {
        videoShutter = new String[]{"Automático", "1/60", "1/120", "1/240"};
        TAG = Shutter60FPS.class.getSimpleName();
    }

    public static Shutter60FPS newInstance() {
        return new Shutter60FPS();
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
                                Log.d(TAG, "VIDEO SHUTTER 1/60");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_1_60, "OBTURADOR 1/60");
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO SHUTTER 1/120");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_1_120, "OBTURADOR 1/120");
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO SHUTTER 1/240");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.exposureTimeVideoMode_1_240, "OBTURADOR 1/240");
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

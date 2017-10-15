package com.example.patricia.matrix.DialogsVideo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


import android.util.Log;

import com.example.patricia.matrix.Activities.VideoActivity;
import com.example.patricia.matrix.Camera.Camera;
import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.Tasks.UpdateSettingsCamera;

public class FOV_W_M extends DialogFragment {

    private String TAG;
    private String[] videoFOV;

    public FOV_W_M() {
        videoFOV = new String[]{"Amplo", "Médio"};
        TAG = FOV_W_M.class.getSimpleName();
    }

    public static FOV_W_M newInstance() {
        return new FOV_W_M();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Campo de Visão")
                .setSingleChoiceItems(videoFOV, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "FOV WIDE");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoModeFOV_Wide, "CAMPO DE VISÃO AMPLO");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 1:
                                Log.d(TAG, "FOV MEDIUM");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoModeFOV_Medium, "CAMPO DE VISÃO MÉDIO");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
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

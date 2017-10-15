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

public class ISOLimit extends DialogFragment {

    private String TAG;
    private String[] videoISOLimit;

    public ISOLimit() {
        videoISOLimit = new String[]{"400", "800", "1600", "3200", "6400"};
        TAG = ISOLimit.class.getSimpleName();
    }

    public static ISOLimit newInstance() {
        return new ISOLimit();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("ISO")
                .setSingleChoiceItems(videoISOLimit, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO ISO 400");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.ISOVideo_400, "ISO 400");
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO ISO 800");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.ISOVideo_800, "ISO 800");
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO ISO 1600");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.ISOVideo_1600, "ISO 1600");
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO ISO 3200");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.ISOVideo_3200, "ISO 3200");
                                break;
                            case 4:
                                Log.d(TAG, "VIDEO ISO 6400");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.ISOVideo_6400, "ISO 6400");
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

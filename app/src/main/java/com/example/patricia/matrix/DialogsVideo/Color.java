package com.example.patricia.matrix.DialogsVideo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import android.util.Log;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;

public class Color extends DialogFragment {

    private String TAG;
    private String[] videoColor;

    public Color() {
        videoColor = new String[]{"Flat", "GoPro"};
        TAG = Color.class.getSimpleName();
    }

    public static Color newInstance() {
        return new Color();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Cor")
                .setSingleChoiceItems(videoColor, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO COLOR FLAT");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.colorVideoFlat, "COR FLAT");
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO COLOR GOPRO");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.colorVideoGoPro, "COR GOPRO");
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

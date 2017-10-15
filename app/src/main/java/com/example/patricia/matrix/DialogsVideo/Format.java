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

public class Format extends DialogFragment {

    private String TAG;
    private String[] videoFormat;

    public Format() {
        videoFormat = new String[]{"NTSC", "PAL"};
        TAG = Format.class.getSimpleName();
    }

    public static Format newInstance() {
        return new Format();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Formato de Vídeo")
                .setSingleChoiceItems(videoFormat, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO FORMAT NTSC");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFormatNTSC, "FORMATO NTSC");
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO FORMAT PAL");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFormatPAL, "FORMATO PAL");
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

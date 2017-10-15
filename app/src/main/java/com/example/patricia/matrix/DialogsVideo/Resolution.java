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

public class Resolution extends DialogFragment {

    private String TAG;
    private String[] videoResolution;

    public Resolution() {
        videoResolution = new String[]{"4K", "4K SuperView", "2.7K", "2.7K SuperView", "2.7K 4:3", "1440", "1080", "1080 SuperView", "960", "720", "720 SuperView"};
        TAG = Resolution.class.getSimpleName();
    }

    public static Resolution newInstance() {
        return new Resolution();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Resolução")
                .setSingleChoiceItems(videoResolution, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO RESOLUTION 4K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_4K, "RESOLUÇÃO 4K");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO RESOLUTION 4K SUPERVIEW");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_4K_SuperView, "RESOLUÇÃO 4K SUPERVIEW");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);                                break;
                            case 2:
                                Log.d(TAG, "VIDEO RESOLUTION 2.7K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_27K, "RESOLUÇÃO 2.7K");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO RESOLUTION 2.7K SUPERVIEW");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_27K_SuperView, "RESOLUÇÃO 2.7K SUPERVIEW");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 4:
                                Log.d(TAG, "VIDEO RESOLUTION 2.7K 4:3");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_27K_43, "RESOLUÇÃO 2.7K 4:3");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 5:
                                Log.d(TAG, "VIDEO RESOLUTION 1440");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_1440p, "RESOLUÇÃO 1440");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 6:
                                Log.d(TAG, "VIDEO RESOLUTION 1080");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_1080p, "RESOLUÇÃO 1080");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 7:
                                Log.d(TAG, "VIDEO RESOLUTION 1080 SUPERVIEW");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_1080p_SuperView, "RESOLUÇÃO 1080 SUPERVIEW");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 8:
                                Log.d(TAG, "VIDEO RESOLUTION 960");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_960p, "RESOLUÇÃO 960");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 9:
                                Log.d(TAG, "VIDEO RESOLUTION 720");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_720p, "RESOLUÇÃO 720");
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 10:
                                Log.d(TAG, "VIDEO RESOLUTION 720 SUPERVIEW");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoResolution_720p_SuperView, "RESOLUÇÃO 720 SUPERVIEW");
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

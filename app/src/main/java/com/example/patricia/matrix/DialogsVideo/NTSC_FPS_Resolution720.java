package com.example.patricia.matrix.DialogsVideo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.patricia.matrix.Activities.VideoActivity;
import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.Tasks.UpdateSettingsCamera;

public class NTSC_FPS_Resolution720 extends DialogFragment {

    private String TAG;
    private String[] videoFPS;

    public NTSC_FPS_Resolution720() {
        videoFPS = new String[]{"30", "60", "120", "240"};
        TAG = NTSC_FPS_Resolution720.class.getSimpleName();
    }

    public static NTSC_FPS_Resolution720 newInstance() {
        return new NTSC_FPS_Resolution720();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Quadros por Segundo")
                .setSingleChoiceItems(videoFPS, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO FPS 30");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_30, "FPS 30");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO FPS 60");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_60, "FPS 60");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO FPS 120");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_120, "FPS 120");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO FPS 240");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_240, "FPS 240");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
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

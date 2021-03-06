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

public class PAL_FPS_Resolution1080SuperView extends DialogFragment {

    private String TAG;
    private String[] videoFPS;

    public PAL_FPS_Resolution1080SuperView() {
        videoFPS = new String[]{"24", "25", "48", "50", "80"};
        TAG = NTSC_FPS_Resolution1080SuperView.class.getSimpleName();
    }

    public static PAL_FPS_Resolution1080SuperView newInstance() {
        return new PAL_FPS_Resolution1080SuperView();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Quadros por Segundo")
                .setSingleChoiceItems(videoFPS, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO FPS 24");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_24, "FPS 24");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO FPS 25");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_25, "FPS 25");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO FPS 48");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_48, "FPS 48");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO FPS 50");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_50, "FPS 50");
                                VideoActivity.addFragment(Hero4BlackCommands.status);
                                new UpdateSettingsCamera(getContext()).execute(Hero4BlackCommands.status);
                                break;
                            case 4:
                                Log.d(TAG, "VIDEO FPS 80");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.videoFPS_80, "FPS 80");
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

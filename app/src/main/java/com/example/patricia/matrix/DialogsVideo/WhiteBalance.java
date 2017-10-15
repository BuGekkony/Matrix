package com.example.patricia.matrix.DialogsVideo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;

public class WhiteBalance extends DialogFragment {

    private String TAG;
    private String[] videoWhiteBalance;

    public WhiteBalance() {
        videoWhiteBalance = new String[]{"Automático", "3000K", "4000K", "4800K", "5500K", "6000K", "6500K", "Nativo"};
        TAG = WhiteBalance.class.getSimpleName();
    }

    public static WhiteBalance newInstance() {
        return new WhiteBalance();
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Balanço de Branco")
                .setSingleChoiceItems(videoWhiteBalance, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                Log.d(TAG, "VIDEO WHITE BALANCE AUTOMÁTICO");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_Auto, "BALANÇO DE BRANCO AUTOMÁTICO");
                                break;
                            case 1:
                                Log.d(TAG, "VIDEO WHITE BALANCE 3000K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_3000k, "BALANÇO DE BRANCO 3000K");
                                break;
                            case 2:
                                Log.d(TAG, "VIDEO WHITE BALANCE 4000K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_4000k, "BALANÇO DE BRANCO 4000K");
                                break;
                            case 3:
                                Log.d(TAG, "VIDEO WHITE BALANCE 4800K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_4800k, "BALANÇO DE BRANCO 4800K");
                                break;
                            case 4:
                                Log.d(TAG, "VIDEO WHITE BALANCE 5500K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_5500k, "BALANÇO DE BRANCO 5500K");
                                break;
                            case 5:
                                Log.d(TAG, "VIDEO WHITE BALANCE 6000K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_6000k, "BALANÇO DE BRANCO 6000K");
                                break;
                            case 6:
                                Log.d(TAG, "VIDEO WHITE BALANCE 6500K");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_6500k, "BALANÇO DE BRANCO 6500K");
                                break;
                            case 7:
                                Log.d(TAG, "VIDEO WHITE BALANCE NATIVO");
                                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.whiteBalanceMultishot_Native, "BALANÇO DE BRANCO NATIVO");
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

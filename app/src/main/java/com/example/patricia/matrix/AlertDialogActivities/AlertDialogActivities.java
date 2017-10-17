package com.example.patricia.matrix.AlertDialogActivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class AlertDialogActivities {

    private final String TAG = AlertDialogActivities.class.getSimpleName();
    private Context context;
    private AlertDialog.Builder alertDialogSendDataGPS;
    private AlertDialog.Builder alertDialogBluetooth;
    private AlertDialog.Builder alertDialogGPS;
    private AlertDialog.Builder alertDialogLowBattery;
    private AlertDialog.Builder alertDialogSaveData;
    private AlertDialog.Builder alertDialogSDCard;
    private AlertDialog.Builder alertDialogErrorSDCard;
    private AlertDialog.Builder alertDialogNoWifi;
    private AlertDialog.Builder alertDialogWifiConnection;
    private AlertDialog.Builder alertDialogSendErrorStart;
    private Intent intent;
    private final String TITLE_DIALOG_SAVE_DATA = "Salvar Dados";
    private final String MESSAGE_DIALOG_SAVE_DATA = "Ocorreu um erro ao obter a lista de vídeos do servidor. Tente novamente.";
    private final String TITLE_DIALOG_WIFI_CONNECTION = "Conexão GoPro";
    private final String MESSAGE_DIALOG_WIFI_CONNECTION = "O wifi da sua câmera GoPro está desconectado. Clique em Conectar para conectá-lo.";
    private final String MESSAGE_DIALOG_NO_WIFI = "O wifi da sua câmera GoPro está desconectado. Caso esteja gravando, encerre a gravação do vídeo manualmente. Feche a aplicação, conecte-se ao wifi da sua GoPro e abra novamente a aplicação";
    private final String TITLE_DIALOG_OPEN_GPS = "Serviços de localização desativados. Abrir GPS?";
    private final String MESSAGE_DIALOG_OPEN_GPS = "Ative o GPS de seu smartphone para prosseguir.";
    private final String TITLE_DIALOG_LOW_BATTERY = "Bateria GoPro";
    private final String MESSAGE_DIALOG_LOW_BATTERY_SERVICE_RUNNING = "A bateria da sua câmera está extremamente baixa. O serviço será encerrado agora e os dados de GPS transferidos.";
    private final String MESSAGE_DIALOG_LOW_BATTERY = "A bateria da sua câmera gopro está extremamente baixa.";
    private final String TITLE_DIALOG_SD_CARD = "Cartão SD";
    private final String MESSAGE_DIALOG_SD_CARD = "O seu cartão SD está praticamente cheio ou ocorreu erro com o cartão de gravação. O serviço será encerrado e os dados de GPS transferidos. Verifique sua câmera.";
    private final String TITLE_DIALOG_BLUETOOTH = "Conexão Bluetooth";
    private final String MESSAGE_DIALOG_BLUETOOTH = "Conexão com módulo bluetooth perdida. Feche a aplicação e verifique o pareamento entre seu smartphone e o módulo bluetooth.";
    private final String TITLE_DIALOG_ERROR_SD_CARD = "Cartão SD";
    private final String MESSAGE_DIALOG_ERROR_SD_CARD = "Ocorreu um erro com seu cartão de memória. O serviço será encerrado e os dados de GPS transferidos. Verifique o cartão de sua câmera.";
    private final String MESSAGE_DIALOG_SEND_DATA_GPS_OK = "Os dados de GPS foram enviados com sucesso.";
    private final String MESSAGE_DIALOG_SEND_DATA_GPS_ERROR = "Ocorreu um erro na transferência dos dados de GPS.";
    private final String TITLE_DIALOG_SEND_DATA_GPS = "Envio de dados GPS";
    private final String TITLE_DIALOG_ERROR_START = "Execução de serviço";
    private final String MESSAGE_DIALOG_ERROR_START = "Ocorreu um erro ao enviar o comando responsável por iniciar a gravação do vídeo. O serviço será encerrado. Verifique a conexão wifi e pressione o botão start novamente.";
    private final String TITLE_DIALOG_ERROR_STOP = "Ocorreu um erro ao enviar o comando responsável por encerrar a gravação do vídeo.";

    public AlertDialogActivities(Context context) {
        this.context = context;
    }

    public void alertDialogWifiConnection() {

        Log.d(TAG, "CAIXA DE DIÁLOGO WIFI CONNECTION.");

        alertDialogWifiConnection = new AlertDialog.Builder(context);

        alertDialogWifiConnection.setTitle(TITLE_DIALOG_WIFI_CONNECTION)
                .setMessage(MESSAGE_DIALOG_WIFI_CONNECTION)
                .setPositiveButton("Conectar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Fechar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void alertDialogErrorSendDataGPS() {

        Log.d(TAG, "CAIXA DE DIÁLOGO ERRO AO ENVIAR DADOS DE GPS.");

        alertDialogSendDataGPS = new AlertDialog.Builder(context);

        alertDialogSendDataGPS.setTitle(TITLE_DIALOG_SEND_DATA_GPS)
                .setMessage(MESSAGE_DIALOG_SEND_DATA_GPS_ERROR)
                .setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void alertDialogErrorSendStart() {

        Log.d(TAG, "CAIXA DE DIÁLOGO ERRO AO ENVIAR COMANDO START PARA PROTÓTIPO.");

        alertDialogSendErrorStart = new AlertDialog.Builder(context);

        alertDialogSendErrorStart.setTitle(TITLE_DIALOG_ERROR_START)
                .setMessage(MESSAGE_DIALOG_ERROR_START)
                .setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void alertDialogOpenGPS() {

        Log.d(TAG, "CAIXA DE DIÁLOGO OPEN GPS.");

        alertDialogGPS = new AlertDialog.Builder(context);

        alertDialogGPS.setTitle(TITLE_DIALOG_OPEN_GPS)
                .setMessage(MESSAGE_DIALOG_OPEN_GPS)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Fechar",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                }).show();
    }

    public void alertDialogLowBatteryServiceRunning() {

        Log.d(TAG, "CAIXA DE DIÁLOGO BATERIA BAIXA E SERVIÇO EM EXECUÇÃO.");

        alertDialogLowBattery = new AlertDialog.Builder(context);

        alertDialogLowBattery.setTitle(TITLE_DIALOG_LOW_BATTERY)
                .setMessage(MESSAGE_DIALOG_LOW_BATTERY_SERVICE_RUNNING)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    public void alertDialogLowBattery() {

        Log.d(TAG, "CAIXA DE DIÁLOGO BATERIA BAIXA.");

        alertDialogLowBattery = new AlertDialog.Builder(context);

        alertDialogLowBattery.setTitle(TITLE_DIALOG_LOW_BATTERY)
                .setMessage(MESSAGE_DIALOG_LOW_BATTERY)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }


    public void alertDialogSDCard() {

        Log.d(TAG, "CAIXA DE DIÁLOGO SD CARD.");

        alertDialogSDCard = new AlertDialog.Builder(context);

        alertDialogSDCard.setTitle(TITLE_DIALOG_SD_CARD)
                .setMessage(MESSAGE_DIALOG_SD_CARD)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    public void alertDialogErrorSDCard() {

        Log.d(TAG, "CAIXA DE DIÁLOGO ERROR SD CARD.");

        alertDialogErrorSDCard = new AlertDialog.Builder(context);

        alertDialogErrorSDCard.setTitle(TITLE_DIALOG_ERROR_SD_CARD)
                .setMessage(MESSAGE_DIALOG_ERROR_SD_CARD)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    public void alertDialogNoWifi() {

        Log.d(TAG, "CAIXA DE DIÁLOGO SEM CONEXÃO WIFI.");

        alertDialogNoWifi = new AlertDialog.Builder(context);

        alertDialogNoWifi.setTitle(TITLE_DIALOG_WIFI_CONNECTION)
                .setMessage(MESSAGE_DIALOG_NO_WIFI)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    public void alertDialogBluetooth() {

        Log.d(TAG, "CAIXA DE DIÁLOGO BLUETOOTH.");

        alertDialogBluetooth = new AlertDialog.Builder(context);

        alertDialogBluetooth.setTitle(TITLE_DIALOG_BLUETOOTH)
                .setMessage(MESSAGE_DIALOG_BLUETOOTH)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    public void alertDialogSaveData() {

        Log.d(TAG, "CAIXA DE DIÁLOGO SAVE DATA.");

        alertDialogSaveData = new AlertDialog.Builder(context);

        alertDialogSaveData.setTitle(TITLE_DIALOG_SAVE_DATA)
                .setMessage(MESSAGE_DIALOG_SAVE_DATA)
                .setPositiveButton("OK, Entendi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }
}
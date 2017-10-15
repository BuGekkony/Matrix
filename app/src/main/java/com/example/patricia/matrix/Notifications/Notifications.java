package com.example.patricia.matrix.Notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;

import com.example.patricia.matrix.Activities.VideoActivity;
import com.example.patricia.matrix.R;

@SuppressWarnings("deprecation")
public class Notifications {

    private Context context;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private Intent intent;
    private  NotificationCompat.Builder builder;
    private android.app.Notification notifications;
    private final int ID_NOTIFICATION_STATUS_WIFI_GOPRO = 100;
    private final int ID_NOTIFICATION_CONNECT_WIFI_GOPRO = 175;
    private final int ID_NOTIFICATION_ERROR_CONNECTION = 110;
    private final int ID_NOTIFICATION_ERROR_SEND_DATA_GPS = 548;

    public Notifications(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void sendNotificationStatusWifiGoPro() {

        intent = new Intent(context, VideoActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder = new NotificationCompat.Builder(context);
        builder.setTicker("Matrix");
        builder.setContentTitle("Status Wifi");
        builder.setSmallIcon(R.drawable.wifi);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.wifi));
        builder.setContentIntent(pendingIntent);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String [] descs = new String[]{"O wifi da sua câmera GoPro está desconectado.", "O serviço será encerrado.", "Se estiver gravando, encerre a gravação manualmente.", "Conecte-se ao wifi novamente e reabra o aplicativo."};
        for(int i = 0; i < descs.length; i++){
            style.addLine(descs[i]);
        }
        builder.setStyle(style);
        notifications = builder.build();
        notifications.vibrate = new long[] {150, 400, 150, 600};
        notifications.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(ID_NOTIFICATION_STATUS_WIFI_GOPRO, notifications);
        try{
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void connectWifiGoPro() {

        intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder = new NotificationCompat.Builder(context);
        builder.setTicker("Matrix");
        builder.setContentTitle("Wifi GoPro");
        builder.setSmallIcon(R.drawable.wifi);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.wifi));
        builder.setContentIntent(pendingIntent);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String [] descs = new String[]{"O wifi da sua câmera está desconectado.", "Clique sobre a notificação para conectar-se."};
        for(int i = 0; i < descs.length; i++){
            style.addLine(descs[i]);
        }
        builder.setStyle(style);
        notifications = builder.build();
        notifications.vibrate = new long[] {150, 400, 150, 600};
        notifications.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(ID_NOTIFICATION_CONNECT_WIFI_GOPRO, notifications);
        try{
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void errorConnection() {

        intent = new Intent(context, VideoActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder = new NotificationCompat.Builder(context);
        builder.setTicker("Matrix");
        builder.setContentTitle("Conexão GoPro");
        builder.setSmallIcon(R.drawable.warning);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.warning));
        builder.setContentIntent(pendingIntent);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String [] descs = new String[]{"Erro ao enviar comando para câmera GoPro.", "Verifique sua conexão Wi-Fi."};
        for(int i = 0; i < descs.length; i++){
            style.addLine(descs[i]);
        }
        builder.setStyle(style);
        notifications = builder.build();
        notifications.vibrate = new long[] {150, 600, 150, 900};
        notifications.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(ID_NOTIFICATION_ERROR_CONNECTION, notifications);
        try{
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void errorSendDataGPS() {

        intent = new Intent(context, VideoActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        builder = new NotificationCompat.Builder(context);
        builder.setTicker("Matrix");
        builder.setContentTitle("Envio de dados GPS");
        builder.setSmallIcon(R.drawable.gps);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.gps));
        builder.setContentIntent(pendingIntent);
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String [] descs = new String[]{"Ocorreu um erro na transferência dos dados de GPS."};
        for(int i = 0; i < descs.length; i++){
            style.addLine(descs[i]);
        }
        builder.setStyle(style);
        notifications = builder.build();
        notifications.vibrate = new long[] {150, 600, 150, 900};
        notifications.flags = Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(ID_NOTIFICATION_ERROR_SEND_DATA_GPS, notifications);
        try{
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }
}
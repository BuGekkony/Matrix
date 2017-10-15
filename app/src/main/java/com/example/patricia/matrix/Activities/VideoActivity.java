package com.example.patricia.matrix.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.patricia.matrix.AlertDialogActivities.AlertDialogActivities;
import com.example.patricia.matrix.Callable.CallableVideo;
import com.example.patricia.matrix.Camera.Camera;
import com.example.patricia.matrix.Constants.ConstantsVideo;
import com.example.patricia.matrix.DialogsVideo.Color;
import com.example.patricia.matrix.DialogsVideo.FOV_N;
import com.example.patricia.matrix.DialogsVideo.FOV_W;
import com.example.patricia.matrix.DialogsVideo.FOV_W_M;
import com.example.patricia.matrix.DialogsVideo.FOV_W_M_N;
import com.example.patricia.matrix.DialogsVideo.FOV_W_N;
import com.example.patricia.matrix.DialogsVideo.Format;
import com.example.patricia.matrix.DialogsVideo.ISOLimit;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution1080;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution1080SuperView;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution1440;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution27K;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution27_43;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution4K;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution4KSuperView;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution720;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution720SuperView;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution960;
import com.example.patricia.matrix.DialogsVideo.NTSC_FPS_Resolution_27SuperView;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution1080;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution1080SuperView;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution1440;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution27K;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution27_43;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution4K;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution4KSuperView;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution720;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution720SuperView;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution960;
import com.example.patricia.matrix.DialogsVideo.PAL_FPS_Resolution_27SuperView;
import com.example.patricia.matrix.DialogsVideo.Resolution;
import com.example.patricia.matrix.DialogsVideo.Sharpness;
import com.example.patricia.matrix.DialogsVideo.Shutter24FPS;
import com.example.patricia.matrix.DialogsVideo.Shutter25FPS;
import com.example.patricia.matrix.DialogsVideo.Shutter30FPS;
import com.example.patricia.matrix.DialogsVideo.Shutter48FPS;
import com.example.patricia.matrix.DialogsVideo.Shutter50FPS;
import com.example.patricia.matrix.DialogsVideo.Shutter60FPS;
import com.example.patricia.matrix.DialogsVideo.WhiteBalance;
import com.example.patricia.matrix.Fragments.FragmentInitial;
import com.example.patricia.matrix.Fragments.FragmentSurfaceView;
import com.example.patricia.matrix.Fragments.FragmentVideoActivity;
import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.Notifications.Notifications;
import com.example.patricia.matrix.R;
import com.example.patricia.matrix.Services.GPSService;
import com.example.patricia.matrix.StopExecutors.StopExecutors;
import com.example.patricia.matrix.Views.SetViewVideo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("deprecation")
public class VideoActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = VideoActivity.class.getSimpleName();

    /*
    Elementos de interface gráfica
    */
    private static TextView textViewInformationAbove;
    private static TextView textViewInformationBelow;
    private static ImageView imageViewBattery;
    private static ImageView imageViewStrengthWifi;
    private ImageView buttonService;

    /*
    Fragments
     */
    private static Fragment fragment;
    private static FragmentTransaction fragmentTransaction;
    private static FragmentManager fragmentManager;

    /*
    Variáveis inteiras
     */
    private int videoMode;
    private int videoFormat;
    private int videoResolution;
    private int videoFramesPerSecond;
    private final int TIME_FOR_UPDATING_ACTIVITY_VIDEO = 1;    // Segundo(s)
    private final int TIME_FOR_UPDATING_SDCARD = 1;  // Minuto(s)
    private final int DELAY = 0;
    private final int TIME_UPDATING_BATTERY = 1;   // Minuto(s)
    private static int numberFramesPerSecond;
    private static int seconds;
    private static int minutes;
    private static int hours;
    private static int timeRecording;
    private static int timeRemaining;
    private static int min;

    /*
    Variáveis String
     */
    private static final String CATEGORY_SETTINGS = "settings";
    private static final String CATEGORY_STATUS = "status";
    private static String status;
    private static String informationBelow;
    private static String videosInsideMemoryCard;
    private static String battery;
    private static String timeVideoRemaining;
    private static String videoTimeRecording;
    private static String resolution;
    private static String fieldOfView;
    private static String framesPerSecond;
    private static String informationAbove;
    private static String response;

    /*
    Broadcast Receivers
     */
    private BroadcastSetViewSettingsCamera broadcastSetViewSettingsCamera;
    private BroadcastWifiGopro broadcastWifiGopro;

    /*
    Intent Filters
     */
    private IntentFilter connectivityManagerWifiGoPro;
    private IntentFilter intentFilterSetViewSettingsCamera;
    private IntentFilter wifiManagerWifiGoPro;

    /*
    Future
     */
    private Future<String> future;

    /*
    Dialog Fragment
     */
    private DialogFragment dialogFragment;

    /*
    Intent
     */
    private Intent intent;

    /*
    Variáveis booleanas
    */
    private static boolean serviceIsRunning;

    // Executors
    private ScheduledExecutorService scheduledExecutorServiceSDCard;
    private ScheduledExecutorService scheduledExecutorServiceUpdateActivityVideo;
    private ScheduledExecutorService scheduledExecutorServiceBattery;
    private ScheduledExecutorService scheduledExecutorService;

    // Runnables
    private RunnableStatusBattery runnableLowerBattery;
    private RunnableUpdateViewActivityVideo runnableActivityVideo;
    private RunnableStatusSDCard runnableSDCard;

    // Outras Classes
    private static AlertDialogActivities alertDialogActivities;
    private CallableVideo callableVideo;
    private Camera camera;
    private ConnectivityManager connectivityManager;
    private static Context context;
    private ExecutorService executorService;
    private Notifications notifications;
    private static SetViewVideo setViewVideo;
    private static StringRequest stringRequest;
    private WifiManager wifiManager;

    /*
    Métodos do ciclo de vida
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ON CREATE.");
        setContentView(R.layout.activity_video);

        fragmentManager = getSupportFragmentManager();

        if( savedInstanceState == null ){
            fragment = new FragmentInitial();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame_layout_surface_view, fragment, "fragment_initial");
            fragmentTransaction.commit();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        buttonService = (ImageView) findViewById(R.id.activity_video_image_button_service);
        textViewInformationAbove = (TextView) findViewById(R.id.textview_information_above_activity_video);
        textViewInformationBelow = (TextView) findViewById(R.id.textview_information_below_activity_video);
        imageViewBattery = (ImageView) findViewById(R.id.image_view_battery_activity_video);
        imageViewBattery.setImageResource(R.drawable.battery_25);
        imageViewStrengthWifi = (ImageView) findViewById(R.id.image_view_strength_wifi_activity_video);

        context = VideoActivity.this;
        alertDialogActivities = new AlertDialogActivities(VideoActivity.this);
        camera = new Camera(VideoActivity.this);
        callableVideo = new CallableVideo();
        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        notifications = new Notifications(VideoActivity.this);
        setViewVideo = new SetViewVideo();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        runnableActivityVideo = new RunnableUpdateViewActivityVideo();
        runnableLowerBattery = new RunnableStatusBattery();
        runnableSDCard = new RunnableStatusSDCard();

        sendSettingsDefault();

        /*
        Criação de executors
         */
        createExecutorSettingsCamera();
        createExecutorBattery();
        createExecutorSDCard();
        createExecutorUpdateVideoActivity();

        /*
        Registro dos Broadcast receivers
         */
        registerBroadcastReceiverWifi();
        registerBroadcastReceiverSetViewSettingsCamera();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ON START.");
        /*
        Atualiza informações superiores: Resolução, Quadros por Segundos e Campo de Visão
        ao abrir a atividade.
         */
        choiceFragment(Hero4BlackCommands.status);
        setViewSettingsCamera(Hero4BlackCommands.status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ON RESUME.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ON STOP.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ON DESTROY.");
        stopExecutorSettingsCamera();
        stopExecutorBattery();
        stopExecutorSDCard();
        stopExecutorUpdateVideoActivity();
        unregisterBroadcastReceiverWifi();
        unregisterBroadcastReceiverSetViewSettingsCamera();
    }

    /*
    Broadcast Receiver Wifi
     */

    public void registerBroadcastReceiverWifi() {
        Log.d(TAG, "REGISTER BROADCAST RECEIVER WIFI.");
        connectivityManagerWifiGoPro = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        wifiManagerWifiGoPro = new IntentFilter(WifiManager.RSSI_CHANGED_ACTION);
        broadcastWifiGopro = new BroadcastWifiGopro();
        registerReceiver(broadcastWifiGopro, connectivityManagerWifiGoPro);
        registerReceiver(broadcastWifiGopro, wifiManagerWifiGoPro);
    }

    public void unregisterBroadcastReceiverWifi() {
        Log.d(TAG, "UNREGISTER BROADCAST RECEIVER WIFI.");
        if ( broadcastWifiGopro != null ) {
            unregisterReceiver(broadcastWifiGopro);
        }
    }

    /*
    Broadcast Receiver SetViewSettingsCamera
     */

    public void registerBroadcastReceiverSetViewSettingsCamera() {
        intentFilterSetViewSettingsCamera = new IntentFilter(ConstantsVideo.INTENT_SET_TEXTVIEW_INFORMATION_ABOVE);
        intentFilterSetViewSettingsCamera.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastSetViewSettingsCamera = new BroadcastSetViewSettingsCamera();
        registerReceiver(broadcastSetViewSettingsCamera, intentFilterSetViewSettingsCamera);
    }

    public void unregisterBroadcastReceiverSetViewSettingsCamera() {
        if( broadcastSetViewSettingsCamera != null ) {
            unregisterReceiver(broadcastSetViewSettingsCamera);
        }
    }

    /*
    Métodos Diversos
     */

    private static void setTimeRecording() {
        Log.d(TAG, "SET TIME RECORDING.");
        timeRecording = Integer.parseInt(videoTimeRecording);
        seconds = timeRecording % 60;
        min = timeRecording / 60;
        minutes = min % 60;
        hours = min / 60;
        videoTimeRecording = String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static void setTimeRemaining() {
        Log.d(TAG, "SET TIME REMAINING.");
        timeRemaining = Integer.parseInt(timeVideoRemaining);
        seconds = timeRemaining % 60;
        min = timeRemaining / 60;
        minutes = min % 60;
        hours = min / 60;
        timeVideoRemaining = String.format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void stopServiceGPS() {
        Log.d(TAG, "MÉTODO STOP SERVICE GPS.");
        stopService(new Intent(VideoActivity.this, GPSService.class));
    }

    private void startServiceGPS() {
        Log.d(TAG, "MÉTODO START SERVICE GPS.");
        startService(new Intent(VideoActivity.this, GPSService.class));
    }

    private void sendSettingsDefault() {
        Log.d(TAG, "SEND SETTINGS DEFAULT.");
        NetworkVolley.sendCommand(Hero4BlackCommands.videoModePrimary);
        NetworkVolley.sendCommand(Hero4BlackCommands.videoMode);
        NetworkVolley.sendCommand(Hero4BlackCommands.videoFormatNTSC);
        NetworkVolley.sendCommand(Hero4BlackCommands.protuneVideoEnabled);
        NetworkVolley.sendCommand(Hero4BlackCommands.bitRate_1_mbps);
        Toast.makeText(VideoActivity.this, "MODO VÍDEO PADRÃO SELECIONADO.", Toast.LENGTH_SHORT).show();
    }

    private static void callFragmentSurfaceView() {
        Log.d(TAG, "MÉTODO CALL FRAGMENT SURFACE VIEW.");
        fragment = new FragmentSurfaceView();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_surface_view, fragment);
        fragmentTransaction.commit();
    }

    private static void callFragmentVideoActivity() {
        Log.d(TAG, "MÉTODO CALL FRAGMENT VIDEO ACTIVITY.");
        fragment = new FragmentVideoActivity();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_surface_view, fragment);
        fragmentTransaction.commit();
    }

    /*
    O método addFragment será chamado dentro dos Dialogs Fragments quando o número de quadros
    por segundos for alterado enquanto o serviço de GPS estiver em execução. Pois se o serviço estiver
    executando e o FPS for alterado é necessário que a view seja atualizada ou com o uso do preview ou
    com a tela "preta" com a mensagem de que a pré-visualização é indisponível. E se o serviço não
    estiver em execução a view da atividade não precisará de atualização. Apenas será atualizado o
    textview que exibe as configurações.
     */
    public static void addFragment(String command) {

        Log.d(TAG, "MÉTODO ADD FRAGMENT.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            serviceIsRunning = GPSService.isServiceRunning();
                            framesPerSecond = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("3");
                            numberFramesPerSecond = Integer.parseInt(framesPerSecond);
                            if( serviceIsRunning ) {
                                switch ( numberFramesPerSecond ) {
                                    case ConstantsVideo.VIDEO_FPS_24:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_25:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_30:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_48:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_50:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_60:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_80:
                                        callFragmentSurfaceView();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_90:
                                        callFragmentSurfaceView();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_120:
                                        callFragmentSurfaceView();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_240:
                                        callFragmentSurfaceView();
                                        break;
                                }
                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO MÉTODO ADD FRAGMENT: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO ADD FRAGMENT. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    /*
    O método choiceFragment será chamado no onStart() para escolher qual fragment deverá ser adicionado
    ao layout da Atividade Vídeo. Será utilizado tanto para as situações quando o serviço GPS estiver em
    execução ou não. Também será chamado nas situações em que o usuário clicar no botão "Voltar" do smartphone.
    Nessa situação a atividade e o fragment serão destruídos e quando ele voltar para a atividade vídeo, será
    executado novamente o método onCreate() e posteriormente o método onStart() e quando o método choiceFragment
    for chamado será verificado se o serviço está em execução. Se sim, se verificará qual o FPS setado e de
    acordo com o valor será atualizada a view com o fragment correto e caso o serviço não esteja executando
    basta adicionar o fragment com a funcionalidade de preview.
     */
    public static void choiceFragment(String command) {

        Log.d(TAG, "MÉTODO CHOICE FRAGMENT.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            serviceIsRunning = GPSService.isServiceRunning();
                            framesPerSecond = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("3");
                            numberFramesPerSecond = Integer.parseInt(framesPerSecond);
                            if( serviceIsRunning ) {
                                switch ( numberFramesPerSecond ) {
                                    case ConstantsVideo.VIDEO_FPS_24:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_25:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_30:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_48:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_50:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_60:
                                        callFragmentVideoActivity();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_80:
                                        callFragmentSurfaceView();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_90:
                                        callFragmentSurfaceView();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_120:
                                        callFragmentSurfaceView();
                                        break;
                                    case ConstantsVideo.VIDEO_FPS_240:
                                        callFragmentSurfaceView();
                                        break;
                                }
                            } else {
                                callFragmentVideoActivity();
                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO MÉTODO CHOICE FRAGMENT: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO CHOICE FRAGMENT. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }


    /*
    O método setFragmentSurfaceView será chamado dentro do ServiceGPS quando o método
    onLocationChanged for invocado pela primeira vez. A view será atualizada de acordo
    com o número de quadros por segundos selecionado.
     */
    public static void setFragmentSurfaceView(String command) {

        Log.d(TAG, "MÉTODO SET FRAGMENT SURFACE VIEW.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            framesPerSecond = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("3");
                            numberFramesPerSecond = Integer.parseInt(framesPerSecond);
                            switch ( numberFramesPerSecond ) {
                                case ConstantsVideo.VIDEO_FPS_80:
                                    callFragmentSurfaceView();
                                    break;
                                case ConstantsVideo.VIDEO_FPS_90:
                                    callFragmentSurfaceView();
                                    break;
                                case ConstantsVideo.VIDEO_FPS_120:
                                    callFragmentSurfaceView();
                                    break;
                                case ConstantsVideo.VIDEO_FPS_240:
                                    callFragmentSurfaceView();
                                    break;
                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO MÉTODO SET FRAGMENT SURFACE VIEW: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO SET FRAGMENT SURFACE VIEW. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    /*
    O método setFragmentVideo é chamado dentro do ServiceGPS, no método onDestroy.
    Quando o serviço é encerrado e o FPS é 80, 90, 120 ou 240 , ou seja , um valor
    em que o preview não é suportado, assim que a execução do serviço for encerrada
    a view será atualizada com a tela contendo a surface view (preview).
     */
    public static void setFragmentVideo(String command) {

        Log.d(TAG, "MÉTODO SET FRAGMENT VÍDEO.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            framesPerSecond = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("3");
                            numberFramesPerSecond = Integer.parseInt(framesPerSecond);
                            switch ( numberFramesPerSecond ) {
                                case ConstantsVideo.VIDEO_FPS_80:
                                    callFragmentVideoActivity();
                                    break;
                                case ConstantsVideo.VIDEO_FPS_90:
                                    callFragmentVideoActivity();
                                    break;
                                case ConstantsVideo.VIDEO_FPS_120:
                                    callFragmentVideoActivity();
                                    break;
                                case ConstantsVideo.VIDEO_FPS_240:
                                    callFragmentVideoActivity();
                                    break;
                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO MÉTODO SET FRAGMENT VÍDEO: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO SET FRAGMENT VÍDEO. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    /*
    O método setViewActivityVideo atualiza as informações contidas no textview inferior da tela.
    Atualiza as informações do número de vídeos armazenados no cartão de memória, o tempo de gravação e
    o tempo de vídeo remanescente.
     */
    private static void setViewActivityVideo(String command) {

        Log.d(TAG, "SET VIEW ACTIVITY VIDEO.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            battery = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("2");
                            setViewVideo.getVideoBattery(imageViewBattery, battery);
                            videoTimeRecording = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("13");
                            timeVideoRemaining = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("35");
                            videosInsideMemoryCard = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("39");
                            setTimeRecording();
                            setTimeRemaining();
                            informationBelow = videosInsideMemoryCard + "   " + videoTimeRecording + " " + " / " + " " + timeVideoRemaining;
                            textViewInformationBelow.setText(informationBelow);

                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO SET VIEW ACTIVITY VIDEO: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO SET VIEW ACTIVITY VIDEO. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    /*
    O método statusBattery verifica a carga da bateria. Se a bateria estiver muito baixa e o serviço
    em execução o mesmo será encerrado e se não estiver em execução apenas será exibido uma caixa de
    diálogo.
     */
    private static void statusBattery(String command) {

        Log.d(TAG, "MÉTODO STATUS BATTERY.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            battery = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("2");
                            setViewVideo.getVideoBattery(imageViewBattery, battery);
                            serviceIsRunning = GPSService.isServiceRunning();

                            // bateria extremamente baixa e serviço em execução
                            if( ( battery.equals("0") ) && ( serviceIsRunning ) ) {
                                alertDialogActivities.alertDialogLowBatteryServiceRunning();
                                context.stopService(new Intent(context, GPSService.class));
                            }
                            // bateria extremamente baixa mas serviço não está em execução
                            if( ( battery.equals("0")) && !( serviceIsRunning ) ) {
                                alertDialogActivities.alertDialogLowBattery();
                            }
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "EXCEÇÃO MÉTODO STATUS BATTERY: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO STATUS BATTERY. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    /*
      O método setViewSettingsCamera será chamado ao iniciar a atividade no método onStart para
      atualizar as informações da câmera: resolução, quadros por segundos e campo de visão.
    */
    public static void setViewSettingsCamera(String command) {

        Log.d(TAG, "MÉTODO SET VIEW SETTINGS CAMERA.");

        stringRequest = new StringRequest(Request.Method.GET, command,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            status = String.valueOf(Html.fromHtml(response));
                            resolution = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("2");
                            framesPerSecond = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("3");
                            fieldOfView = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("4");
                            resolution = setViewVideo.getVideoResolution(resolution);
                            framesPerSecond = setViewVideo.getVideoFramesPerSecond(framesPerSecond);
                            fieldOfView = setViewVideo.getVideoFieldOfView(fieldOfView);
                            informationAbove = resolution + " / " + " " + framesPerSecond + " / " + " " + fieldOfView;
                            textViewInformationAbove.setText(informationAbove);
                        } catch (JSONException exception) {
                            exception.printStackTrace();
                            Log.e(TAG, "MÉTODO SET VIEW SETTINGS CAMERA: ", exception);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "MÉTODO SET VIEW SETTINGS CAMERA. ERROR RESPONSE: " + error.toString());
            }
        });
        NetworkVolley.getInstance().getRequestQueue().add(stringRequest);
    }

    /*
    Métodos de Interface Gráfica : Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.video, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            intent = new Intent(VideoActivity.this, SettingsAnglesActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Métodos Evento Click
     */

    /*
    O método é responsável por iniciar e encerrar a execução do serviço GPS.
     */
    public void service(View view) {
        Log.d(TAG, "MÉTODO SERVICE.");
        serviceIsRunning = GPSService.isServiceRunning();
        if( serviceIsRunning ) {
            stopServiceGPS();
        } else {
            Toast.makeText(VideoActivity.this, "AGUARDE O INÍCIO DA EXECUÇÃO DO SERVIÇO.", Toast.LENGTH_SHORT).show();
            startServiceGPS();
        }
    }

    /*
    Os métodos profile1, profile2, profile3 e profile4 são responsáveis por enviar a requisição com o número
    do perfil para que os valores dos ângulos dos sensores e gimbal sejam setados.
     */
    public void profile1(View view) {
        Log.d(TAG, "MÉTODO PROFILE 1.");
        NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.setProfile_1, "ENVIADO PERFIL DE NÚMERO 1.");
    }

    public void profile2(View view) {
        Log.d(TAG, "MÉTODO PROFILE 2.");
        NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.setProfile_2, "ENVIADO PERFIL DE NÚMERO 2.");
    }

    public void profile3(View view) {
        Log.d(TAG, "MÉTODO PROFILE 3.");
        NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.setProfile_3, "ENVIADO PERFIL DE NÚMERO 3.");
    }

    public void profile4(View view) {
        Log.d(TAG, "MÉTODO PROFILE 4.");
        NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.setProfile_4, "ENVIADO PERFIL DE NÚMERO 4.");
    }

    /*
    Métodos auxiliares: serão utilizados quando os Dialogs Fragments forem selecionados dentro do
    Navigation Drawer
     */
    /*
    O método chooseFramesPerSecond exibe ao usuário o número de quadros por segundos de acordo de acordo
    com o modo vídeo, formato de vídeo (PAL e NTSC) e resolução.
     */
    public void chooseFramesPerSecond() {
        Log.d(TAG, "CHOOSE FRAMES PER SECOND.");
        try {
            future = executorService.submit(callableVideo);
            response = future.get();
            videoMode = callableVideo.getModeCAM(response);
            Log.d(TAG, "VIDEO MODE " + videoMode );
            videoFormat = callableVideo.getVideoFormat(response);
            Log.d(TAG, "VIDEO FORMAT " + videoFormat);
            videoResolution = callableVideo.getVideoResolution(response);
            Log.d(TAG, "VIDEO RESOLUTION " + videoResolution);

            if ( (videoMode == -1) || (videoFormat == -1) || (videoResolution == -1) ) {
                // ERRO
                notifications.errorConnection();
            }
            else {
                switch ( videoMode ) {
                    case ConstantsVideo.VIDEO_MODE:
                        // MODO VÍDEO PADRÃO
                        switch( videoFormat ) {
                            // FORMATO DE VÍDEO NTSC
                            case ConstantsVideo.VIDEO_FORMAT_NTSC:
                                switch ( videoResolution ) {
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K:
                                        dialogFragment = NTSC_FPS_Resolution4K.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution4K");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K_SUPERVIEW:
                                        dialogFragment = NTSC_FPS_Resolution4KSuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution4KSuperView");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_SUPERVIEW:
                                        dialogFragment = NTSC_FPS_Resolution_27SuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution_27SuperView");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K:
                                        dialogFragment = NTSC_FPS_Resolution27K.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution27K");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_43:
                                        dialogFragment = NTSC_FPS_Resolution27_43.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution27_43");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1440P:
                                        dialogFragment = NTSC_FPS_Resolution1440.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution1440");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080_SUPERVIEW:
                                        dialogFragment = NTSC_FPS_Resolution1080SuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution1080SuperView");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080P:
                                        dialogFragment = NTSC_FPS_Resolution1080.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution1080");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_960P:
                                        dialogFragment = NTSC_FPS_Resolution960.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution960");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720P:
                                        dialogFragment = NTSC_FPS_Resolution720.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution720");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720_SUPERVIEW:
                                        dialogFragment = NTSC_FPS_Resolution720SuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "NTSC_FPS_Resolution720SuperView");
                                        break;
                                }
                                break;

                            case ConstantsVideo.VIDEO_FORMAT_PAL:
                                // FORMATO DE VÍDEO PAL
                                switch( videoResolution ) {
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K:
                                        dialogFragment = PAL_FPS_Resolution4K.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution4K");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K_SUPERVIEW:
                                        dialogFragment = PAL_FPS_Resolution4KSuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution4KSuperView");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_SUPERVIEW:
                                        dialogFragment = PAL_FPS_Resolution_27SuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution_27SuperView");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K:
                                        dialogFragment = PAL_FPS_Resolution27K.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution27K");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_43:
                                        dialogFragment = PAL_FPS_Resolution27_43.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution27_43");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1440P:
                                        dialogFragment = PAL_FPS_Resolution1440.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution1440");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080_SUPERVIEW:
                                        dialogFragment = PAL_FPS_Resolution1080SuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution1080SuperView");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080P:
                                        dialogFragment = PAL_FPS_Resolution1080.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution1080");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_960P:
                                        dialogFragment = PAL_FPS_Resolution960.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution960");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720P:
                                        dialogFragment = PAL_FPS_Resolution720.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution720");
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720_SUPERVIEW:
                                        dialogFragment = PAL_FPS_Resolution720SuperView.newInstance();
                                        dialogFragment.show(getSupportFragmentManager(), "PAL_FPS_Resolution720SuperView");
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /*
    O método chooseFieldOfView exibe ao usuários os dialogs de acordo com o modo de vídeo, formato, resolução e
    quadros por segundos.
     */
    public void chooseFieldOfView() {
        Log.d(TAG, "CHOOSE FIELD OF VIEW.");
        try {
            future = executorService.submit(callableVideo);
            response = future.get();
            videoMode = callableVideo.getModeCAM(response);
            Log.d(TAG, "VIDEO MODE " + videoMode );
            videoFormat = callableVideo.getVideoFormat(response);
            Log.d(TAG, "VIDEO FORMAT " + videoFormat);
            videoResolution = callableVideo.getVideoResolution(response);
            Log.d(TAG, "VIDEO RESOLUTION " + videoResolution);
            videoFramesPerSecond = callableVideo.getVideoFramesPerSecond(response);
            Log.d(TAG, "VIDEO FPS " + videoFramesPerSecond);

            if ( (videoMode == -1) || (videoFormat == -1) || (videoResolution == -1) || (videoFramesPerSecond == -1) ) {
                // ERRO
                notifications.errorConnection();
            }
            else {
                switch ( videoMode ) {
                    // MODO VÍDEO PADRÃO
                    case ConstantsVideo.VIDEO_MODE:
                        switch ( videoFormat ) {
                            case ConstantsVideo.VIDEO_FORMAT_NTSC:
                                switch ( videoResolution ) {
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_43:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1440P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_80:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_80:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_90:
                                                dialogFragment = FOV_W_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_N");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_960P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_30:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_60:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_240:
                                                dialogFragment = FOV_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_N");
                                                break;
                                        }
                                        break;
                                }
                                break;
                            case ConstantsVideo.VIDEO_FORMAT_PAL:
                                switch ( videoResolution ) {
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_4K_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W_M.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_27K_43:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1440P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_80:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_80:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_1080P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_24:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_48:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_90:
                                                dialogFragment = FOV_W_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_N");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_960P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720_SUPERVIEW:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W");
                                                break;
                                        }
                                        break;
                                    case ConstantsVideo.VIDEO_RESOLUTION_720P:
                                        switch ( videoFramesPerSecond ) {
                                            case ConstantsVideo.VIDEO_FPS_25:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_50:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_120:
                                                dialogFragment = FOV_W_M_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_W_M_N");
                                                break;
                                            case ConstantsVideo.VIDEO_FPS_240:
                                                dialogFragment = FOV_N.newInstance();
                                                dialogFragment.show(getSupportFragmentManager(), "FOV_N");
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /*
    O método chooseShutter exibe ao usuário o dialog correspondente ao obturador de acordo com o
    número de quadros selecionado.
     */
    public void chooseShutter() {
        Log.d(TAG, "CHOOSE SHUTTER.");
        try {
            future = executorService.submit(callableVideo);
            response = future.get();
            videoFramesPerSecond = callableVideo.getVideoFramesPerSecond(response);

            if (videoFramesPerSecond == -1) {
                // ERRO
                notifications.errorConnection();
            }
            else {
                switch ( videoFramesPerSecond ) {
                    case ConstantsVideo.VIDEO_FPS_24:
                        dialogFragment = Shutter24FPS.newInstance();
                        dialogFragment.show(getSupportFragmentManager(), "Shutter24FPS");
                        break;
                    case ConstantsVideo.VIDEO_FPS_25:
                        dialogFragment = Shutter25FPS.newInstance();
                        dialogFragment.show(getSupportFragmentManager(), "Shutter25FPS");
                        break;
                    case ConstantsVideo.VIDEO_FPS_30:
                        dialogFragment = Shutter30FPS.newInstance();
                        dialogFragment.show(getSupportFragmentManager(), "Shutter30FPS");
                        break;
                    case ConstantsVideo.VIDEO_FPS_48:
                        dialogFragment = Shutter48FPS.newInstance();
                        dialogFragment.show(getSupportFragmentManager(), "Shutter48FPS");
                        break;
                    case ConstantsVideo.VIDEO_FPS_50:
                        dialogFragment = Shutter50FPS.newInstance();
                        dialogFragment.show(getSupportFragmentManager(), "Shutter50FPS");
                        break;
                    case ConstantsVideo.VIDEO_FPS_60:
                        dialogFragment = Shutter60FPS.newInstance();
                        dialogFragment.show(getSupportFragmentManager(), "Shutter60FPS");
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /*
     Métodos de Interface Gráfica
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.nav_format:
                Log.d(TAG, "DIALOG FORMATO.");
                dialogFragment = Format.newInstance();
                dialogFragment.show(getSupportFragmentManager(), "Format");
                break;
            case R.id.nav_resolution:
                Log.d(TAG, "DIALOG RESOLUÇÃO.");
                dialogFragment = Resolution.newInstance();
                dialogFragment.show(getSupportFragmentManager(),"Resolution");
                break;
            case R.id.nav_frames_per_second:
                Log.d(TAG, "DIALOG FRAMES POR SEGUNDOS.");
                chooseFramesPerSecond();
                break;
            case R.id.nav_field_of_view:
                Log.d(TAG, "DIALOG CAMPO DE VISÃO.");
                chooseFieldOfView();
                break;
            case R.id.nav_balance_white:
                Log.d(TAG, "DIALOG BALANÇO DE BRANCO.");
                dialogFragment = WhiteBalance.newInstance();
                dialogFragment.show(getSupportFragmentManager(), "WhiteBalance");
                break;
            case R.id.nav_color:
                Log.d(TAG, "DIALOG COR.");
                dialogFragment = Color.newInstance();
                dialogFragment.show(getSupportFragmentManager(), "Color");
                break;
            case R.id.nav_shutter:
                Log.d(TAG, "DIALOG SHUTTER.");
                chooseShutter();
                break;
            case R.id.nav_iso:
                Log.d(TAG, "DIALOG ISO.");
                dialogFragment = ISOLimit.newInstance();
                dialogFragment.show(getSupportFragmentManager(), "ISOLimit");
                break;
            case R.id.nav_sharpness:
                Log.d(TAG, "DIALOG NITIDEZ.");
                dialogFragment = Sharpness.newInstance();
                dialogFragment.show(getSupportFragmentManager(), "Sharpness");
                break;
            case R.id.nav_last_file:
                Log.d(TAG, "APAGA ÚLTIMO ARQUIVO.");
                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.deleteLastMedia, "ÚLTIMO ARQUIVO DELETADO COM SUCESSO.");
                break;
            case R.id.nav_all_files:
                Log.d(TAG, "APAGA TODOS OS ARQUIVOS.");
                NetworkVolley.sendCommandWithMessage(Hero4BlackCommands.deleteAllMedia, "TODOS OS ARQUIVOS DO CARTÃO SD FORAM DELETADOS COM SUCESSO.");
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
    Métodos para Criação / Inicialização dos Executors
     */
    /*
    O método cria o executor que retorna o modo de vídeo, formato, resolução e quadros por segundos. É
    utilizado para fazer a escolha dos dialogs corretos usados nos métodos chooseFramesPerSecond, chooseShutter e
    chooseFieldOfView.
    */
    private void createExecutorSettingsCamera() {
        Log.d(TAG, "CRIAÇÃO DE EXECUTOR RETORNA CONFIGURAÇÕES DA CÂMERA.");
        executorService = Executors.newSingleThreadExecutor();
    }

    /*
    O executor battery executa a verificação periódica da carga da bateria.
    */
    private void createExecutorBattery() {
        Log.d(TAG, "CRIAÇÃO DE EXECUTOR VERIFICA STATUS DA BATERIA BAIXA.");
        scheduledExecutorServiceBattery = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorServiceBattery.scheduleAtFixedRate(runnableLowerBattery, DELAY, TIME_UPDATING_BATTERY, TimeUnit.MINUTES);
    }

    /*
    O executor SDCard executa periodicamente e verifica se o cartão de memória está quase cheio. Se estiver
    é exibido uma caixa de diálogo ao usuário e o serviço de GPS é encerrado.
     */
    private void createExecutorSDCard() {
        Log.d(TAG, "CRIAÇÃO DE EXECUTOR VERIFICA SE CARTÃO SD ESTÁ CHEIO.");
        scheduledExecutorServiceSDCard = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorServiceSDCard.scheduleAtFixedRate(runnableSDCard, DELAY, TIME_FOR_UPDATING_SDCARD, TimeUnit.MINUTES);
    }

    /*
    O executor atualiza as informações inferiores: número de vídeos armazenados no cartão de memória, tempo de gravação
    e tempo de gravação remanescente.
     */
    private void createExecutorUpdateVideoActivity() {
        Log.d(TAG, "CRIAÇÃO DE EXECUTOR ATUALIZA VIEW DA ATIVIDADE VÍDEO.");
        scheduledExecutorServiceUpdateActivityVideo = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorServiceUpdateActivityVideo.scheduleAtFixedRate(runnableActivityVideo, DELAY, TIME_FOR_UPDATING_ACTIVITY_VIDEO, TimeUnit.SECONDS);
    }

    /*
    Métodos para Encerrar os Executors
     */
    private void stopExecutorSettingsCamera() {
        Log.d(TAG, "ENCERRA EXECUTOR RETORNA CONFIGURAÇÕES DA CÂMERA.");
        StopExecutors.stopTaskExecutorService(executorService, "Configurações da Câmera");
    }

    private void stopExecutorBattery() {
        Log.d(TAG, "ENCERRA EXECUTOR VERIFICA BATERIA BAIXA.");
        StopExecutors.stopTaskExecutorsScheduled(scheduledExecutorServiceBattery, "Verifica Bateria Baixa");
    }

    private void stopExecutorUpdateVideoActivity() {
        Log.d(TAG, "ENCERRA EXECUTOR ATUALIZA VIEW.");
        StopExecutors.stopTaskExecutorsScheduled(scheduledExecutorServiceUpdateActivityVideo, "Update Activity Video");
    }

    private void stopExecutorSDCard() {
        Log.d(TAG, "ENCERRA EXECUTOR VERIFICA CARTÃO SD CHEIO.");
        StopExecutors.stopTaskExecutorsScheduled(scheduledExecutorServiceSDCard, "Verifica Status SD Card");
    }

    /*
    Broadcast Receiver para Wifi GoPro
     */

    public class BroadcastWifiGopro extends BroadcastReceiver {

        private final String TAG = BroadcastWifiGopro.class.getSimpleName();

        private AlertDialogActivities alertDialogActivities;
        private boolean statusWifi;
        private boolean serviceIsRunning;
        private ImageView imageViewStrengthWifi;
        private int IP;
        private int numLevels = 4;
        private int level;
        private NetworkInfo networkInfo;
        private String ipAddress;
        private String macAddress;
        private String action;
        private String wifiSSID;
        private WifiInfo wifiInfo;

        @Override
        public void onReceive(Context context, Intent intent) {

            imageViewStrengthWifi = (ImageView) findViewById(R.id.image_view_strength_wifi_activity_video);
            alertDialogActivities = new AlertDialogActivities(VideoActivity.this);
            wifiInfo = wifiManager.getConnectionInfo();
            networkInfo = connectivityManager.getActiveNetworkInfo();
            statusWifi = wifiManager.isWifiEnabled();
            IP = wifiInfo.getIpAddress();
            macAddress = wifiInfo.getMacAddress();
            ipAddress = String.format(Locale.US, "%d.%d.%d.%d", (IP & 0xff), (IP >> 8 & 0xff), (IP >> 16 & 0xff), (IP >> 24 & 0xff));
            wifiSSID = wifiInfo.getSSID();
            notifications = new Notifications(context);
            action = intent.getAction();

            if ( ConnectivityManager.CONNECTIVITY_ACTION.equals(action) ) {

                // NETWORK INFO DIFERENTE DE NULO
                if ( networkInfo != null ) {

                    // CONEXÃO DO WIFI
                    if ( networkInfo.getType() == ConnectivityManager.TYPE_WIFI ) {
                        // WIFI CONECTADO À CÂMERA GOPRO
                        if ( ( statusWifi == true ) && ( ipAddress.equals(ConstantsVideo.IP_GOPRO)) ) {

                            level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numLevels);

                            if ( level == 1 ) {
                                Log.d(TAG, "RSSI " + String.valueOf(wifiInfo.getRssi()));
                                imageViewStrengthWifi.setImageResource(R.drawable.wifi_2);
                                imageViewStrengthWifi.setVisibility(View.VISIBLE);
                            }
                            else if ( level == 2) {
                                Log.d(TAG, "RSSI " + String.valueOf(wifiInfo.getRssi()));
                                imageViewStrengthWifi.setImageResource(R.drawable.wifi_3);
                                imageViewStrengthWifi.setVisibility(View.VISIBLE);
                            }
                            else if ( level == 3 ) {
                                Log.d(TAG, "RSSI " + String.valueOf(wifiInfo.getRssi()));
                                imageViewStrengthWifi.setImageResource(R.drawable.wifi_4);
                                imageViewStrengthWifi.setVisibility(View.VISIBLE);
                            }
                            else {
                                Log.d(TAG, "RSSI " + String.valueOf(wifiInfo.getRssi()));
                                imageViewStrengthWifi.setImageResource(R.drawable.wifi_1);
                                imageViewStrengthWifi.setVisibility(View.VISIBLE);
                            }
                        }
                        else if ( (statusWifi == true) && !(( ipAddress.equals(ConstantsVideo.IP_GOPRO)) ) ) {
                            // WIFI CONECTADO À ALGUMA OUTRA REDE QUE NÃO A DA GOPRO
                            Log.d(TAG, "WIFI GOPRO DESCONECTADO.");
                            Log.d(TAG, "CONECTADO À REDE : " + wifiSSID);
                            imageViewStrengthWifi.setImageResource(R.drawable.wifi_signal_0);
                            imageViewStrengthWifi.setVisibility(View.VISIBLE);
                            alertDialogActivities.alertDialogNoWifi();
                            notifications.sendNotificationStatusWifiGoPro();
                            serviceIsRunning = GPSService.isServiceRunning();
                            if ( serviceIsRunning ) {
                                Log.d(TAG, "ENCERRANDO SERVIÇO.");
                                context.stopService(new Intent(context, GPSService.class));
                            }
                        }
                    }
                }
                // NETWORK INFO == NULL
                else {
                    // DISPOSITIVO SEM CONEXÃO WIFI
                    Log.d(TAG, "WIFI GOPRO DESCONECTADO.");
                    imageViewStrengthWifi.setImageResource(R.drawable.wifi_signal_0);
                    imageViewStrengthWifi.setVisibility(View.VISIBLE);
                    alertDialogActivities.alertDialogNoWifi();
                    notifications.sendNotificationStatusWifiGoPro();
                    serviceIsRunning = GPSService.isServiceRunning();
                    if ( serviceIsRunning ) {
                        Log.d(TAG, "ENCERRANDO SERVIÇO.");
                        context.stopService(new Intent(context, GPSService.class));
                    }
                }
            }
        }
    }

    /*
    Broadcast Receiver que recebe os dados enviados da AsyncTask UpdateSettingsCamera (Pacote Tasks)
     */
    public class BroadcastSetViewSettingsCamera extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            informationAbove = intent.getStringExtra(ConstantsVideo.MESSAGE_TEXTVIEW_INFORMATION_ABOVE);
            textViewInformationAbove.setText(informationAbove);
        }
    }

    /*
    Runnables para execução dos executors que verifica a carga da bateria, o cartão de memória e
    atualiza o textview inferior da atividade vídeo.
     */

    public class RunnableStatusBattery implements Runnable {
        private final String TAG = RunnableStatusBattery.class.getSimpleName();
        @Override
        public void run() {
            Log.d(TAG, "MÉTODO RUN: ATUALIZA STATUS DA BATERIA.");
            statusBattery(Hero4BlackCommands.status);
        }
    }

    public class RunnableStatusSDCard implements Runnable {
        private final String TAG = RunnableStatusSDCard.class.getSimpleName();
        @Override
        public void run() {
            Log.d(TAG, "MÉTODO RUN: VERIFICA SE CARTÃO DE MEMÓRIA ESTÁ CHEIO.");
            camera.SDCard(Hero4BlackCommands.status);
        }
    }

    public class RunnableUpdateViewActivityVideo implements Runnable {
        private final String TAG = RunnableUpdateViewActivityVideo.class.getSimpleName();
        @Override
        public void run() {
            Log.d(TAG, "MÉTODO RUN: ATUALIZA INFORMAÇÕES DA ATIVIDADE VÍDEO.");
            setViewActivityVideo(Hero4BlackCommands.status);
        }
    }
}
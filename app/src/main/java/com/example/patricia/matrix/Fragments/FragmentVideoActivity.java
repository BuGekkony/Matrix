package com.example.patricia.matrix.Fragments;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;
import com.example.patricia.matrix.LiveStreaming.LiveStreaming;
import com.example.patricia.matrix.NetworkVolley.NetworkVolley;
import com.example.patricia.matrix.R;
import com.example.patricia.matrix.StopExecutors.StopExecutors;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import static tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK;
import static tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_ERROR_UNKNOWN;

@SuppressWarnings("deprecation")
public class FragmentVideoActivity extends Fragment implements
        tv.danmaku.ijk.media.player.IjkMediaPlayer.OnBufferingUpdateListener,
        tv.danmaku.ijk.media.player.IjkMediaPlayer.OnCompletionListener,
        tv.danmaku.ijk.media.player.IjkMediaPlayer.OnPreparedListener,
        tv.danmaku.ijk.media.player.IjkMediaPlayer.OnVideoSizeChangedListener,
        tv.danmaku.ijk.media.player.IjkMediaPlayer.OnErrorListener,
        SurfaceHolder.Callback  {

    /*
    Variáveis booleanas
     */
    private boolean isVideoSizeKnown;
    private boolean isVideoReadyToBePlayed;

    /*
    IJKPlayer
     */
    private IjkMediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    /*
    Variáveis inteiras
     */
    private final int DELAY = 0;
    private final int TIME_FOR_KEEP_ALIVE = 5;           // Segundo(s)
    private int videoWidth;
    private int videoHeight;

    /*
    Scheduled executor service
     */
    private ScheduledExecutorService scheduledExecutorService;

    // Variáveis String
    private static final String TAG = FragmentVideoActivity.class.getSimpleName();
    private final String path = "udp://:8554";

    // Outras Classes
    private LiveStreaming liveStreaming;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "MÉTODO ON CREATE.");
        isVideoSizeKnown = false;
        isVideoReadyToBePlayed = false;
        liveStreaming = new LiveStreaming();
        startBroadcast();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "MÉTODO ON START.");
        createExecutorKeepAlive();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "MÉTODO ON DESTROY.");
        stopBroadcast();
        stopExecutorKeepAlive();
        closeSocket();
        stopMediaPlayer();
        super.onDestroy();
    }


    private void startBroadcast() {
        Log.d(TAG, "MÉTODO START BROADCAST.");
        NetworkVolley.sendCommand(Hero4BlackCommands.startBroadcast);
    }

    private void stopBroadcast() {
        Log.d(TAG, "MÉTODO STOP BROADCAST.");
        NetworkVolley.sendCommand(Hero4BlackCommands.stopBroadcast);
    }

    private void closeSocket() {
        Log.d(TAG, "CLOSE SOCKET.");
        if ( liveStreaming != null ) {
            liveStreaming.closeSocket();
        }
    }

    private void createExecutorKeepAlive() {
        Log.d(TAG, "CRIAÇÃO DE EXECUTOR PARA MANTER A CONEXÃO ATIVA.");
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(liveStreaming, DELAY, TIME_FOR_KEEP_ALIVE, TimeUnit.SECONDS);
    }

    private void stopExecutorKeepAlive() {
        Log.d(TAG, "ENCERRA EXECUTOR QUE MANTÉM A CONEXÃO ATIVA.");
        StopExecutors.stopTaskExecutorsScheduled(scheduledExecutorService, "Mantém Conexão Ativa");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_video_activity, container, false);

        surfaceView = (SurfaceView) viewRoot.findViewById(R.id.surface_fragment_video_activity);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(FragmentVideoActivity.this);
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);

        return (viewRoot);
    }

    private void doCleanUp() {
        Log.d(TAG,"DO CLEAN UP.");
        videoWidth = 0;
        videoHeight = 0;
        isVideoReadyToBePlayed = false;
        isVideoSizeKnown = false;
    }

    private void startVideoPlayback() {
        Log.d(TAG, "START VIDEO PLAYBACK.");
        surfaceHolder.setFixedSize(videoWidth, videoHeight);
        mediaPlayer.start();
    }

    private void stopMediaPlayer() {
        Log.d(TAG, "STOP MEDIA PLAYER.");
        releaseMediaPlayer();
        doCleanUp();
    }

    private void createMediaPlayer() {

        Log.d(TAG, "CREATE MEDIA PLAYER.");

        mediaPlayer = new IjkMediaPlayer();

        mediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "probesize", 8192);
        mediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "analyzeduration", 2000000);

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException exception) {
            exception.printStackTrace();
            Log.e(TAG, "EXCEÇÃO AO SETAR FONTE DE DADOS PARA MEDIA PLAYER: ", exception);
        }
        mediaPlayer.setDisplay(surfaceHolder);
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnBufferingUpdateListener(FragmentVideoActivity.this);
        mediaPlayer.setOnCompletionListener(FragmentVideoActivity.this);
        mediaPlayer.setOnPreparedListener(FragmentVideoActivity.this);
        mediaPlayer.setOnVideoSizeChangedListener(FragmentVideoActivity.this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "SURFACE CREATED.");
        createMediaPlayer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d(TAG, "SURFACE CHANGED.");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.d(TAG, "SURFACE DESTROYED.");
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
        Log.d(TAG,"ON BUFFERING UPDATE.");
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        Log.d(TAG, "ON COMPLETION.");
    }

    private void releaseMediaPlayer() {
        Log.d(TAG, "RELEASE MEDIA PLAYER.");
        if ( mediaPlayer != null ) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, int what, int i1) {
        Log.d(TAG, "ON ERROR.");
        switch (what) {
            case MEDIA_ERROR_UNKNOWN:
                Log.d(TAG, "ERRO: TIPO DE MÍDIA DESCONHECIDO.");
                return true;
            case MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.d(TAG, "ERRO: PLAYBACK PROGRESSIVO INVÁLIDO.");
                return true;
        }
        return false;
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.d(TAG, "ON PREPARED.");
        isVideoReadyToBePlayed = true;
        if (isVideoReadyToBePlayed && isVideoSizeKnown) {
            startVideoPlayback();
        }
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int width, int height, int i2, int i3) {
        Log.d(TAG, "ON VIDEO SIZE CHANGED.");

        if (width == 0 || height == 0) {
            Log.e(TAG, "LARGURA INVÁLIDA (" + width + ") OU ALTURA(" + height + ")");
            return;
        }
        isVideoSizeKnown = true;
        videoWidth = width;
        videoHeight = height;
        if (isVideoReadyToBePlayed && isVideoSizeKnown) {
            startVideoPlayback();
        }
    }
}

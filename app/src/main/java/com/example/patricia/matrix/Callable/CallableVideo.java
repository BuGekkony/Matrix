package com.example.patricia.matrix.Callable;

import android.text.Html;

import com.example.patricia.matrix.Hero4Black.Hero4BlackCommands;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

@SuppressWarnings("deprecation")
public class CallableVideo implements Callable<String> {

    /*
    MODO SECUNDÁRIO VÍDEO
    0- modo vídeo padrão
    1- modo vídeo time lapse
    2- modo vídeo foto
    3- modo vídeo looping
     */

    private final String TAG = CallableVideo.class.getSimpleName();
    private String modePrimary;
    private String modeSecundary;
    private String videoFormat;
    private String videoResolution;
    private String videoFramesPerSecond;
    private String videoSpotMeter;
    private String videoProtune;
    private String videoShutter;
    private String status;
    private final String CATEGORY_STATUS = "status";
    private final String CATEGORY_SETTINGS = "settings";

    @Override
    public String call() throws Exception {
        String response = "";
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet(Hero4BlackCommands.status)).getEntity().getContent()));
            String str = "";
            while (true) {
                str = buffer.readLine();
                if (str == null) {
                    break;
                }
                response = response + str;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return response;
    }

    public int getModeCAM(String response) {
        // response != null
        if ( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                modePrimary = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("43");
                modeSecundary = new JSONObject(status).getJSONObject(CATEGORY_STATUS).getString("44");
                return (Integer.parseInt(modeSecundary));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        // para response null retornar -1 como código de erro e na atividade enviar notificação
        return -1;
    }

    public int getVideoShutter(String response) {
        if ( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                videoShutter = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("73");
                return (Integer.parseInt(videoShutter));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public int getVideoFormat(String response) {
        if( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                videoFormat = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("57");
                return (Integer.parseInt(videoFormat));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public int getVideoResolution(String response) {

        if( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                videoResolution = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("2");
                return (Integer.parseInt(videoResolution));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public int getVideoFramesPerSecond(String response) {

        if( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                videoFramesPerSecond = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("3");
                return(Integer.parseInt(videoFramesPerSecond));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public int getVideoProtune(String response) {

        if ( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                videoProtune = new JSONObject(status).getJSONObject(CATEGORY_SETTINGS).getString("10");
                return (Integer.parseInt(videoProtune));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        return -1;
    }

    public int getVideoSpotMeter(String response) {

        if ( response != null ) {
            status = String.valueOf(Html.fromHtml(response));
            try {
                videoSpotMeter = new JSONObject(this.status).getJSONObject(CATEGORY_SETTINGS).getString("9");
                return (Integer.parseInt(videoSpotMeter));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        }
        return -1;
    }
}
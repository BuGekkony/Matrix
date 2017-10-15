package com.example.patricia.matrix.Tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import com.example.patricia.matrix.Constants.ConstantsVideo;
import com.example.patricia.matrix.Views.SetViewVideo;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SuppressWarnings("deprecation")
public class UpdateSettingsCamera extends AsyncTask<String, Void, String> {

    /*
    A classe UpdateSettingsCamera é responsável por atualizar as informações: resolução, quadros
    por segundos e campo de visão da atividade vídeo. É executada ao iniciar a atividade e é
    chamada dentro dos DialogsFragments que alteram a resolução, o número de quadros por segundos
    e o campo de visão.
     */

    private static final String TAG = UpdateSettingsCamera.class.getSimpleName();
    private static final String SETTINGS = "settings";
    private String informationAbove;
    private String resolution;
    private String framesPerSecond;
    private String fieldOfView;
    private String result;
    private Context context;
    private Intent intent;
    private SetViewVideo setView;

    public UpdateSettingsCamera(Context context) {
        this.context = context;
        setView = new SetViewVideo();
    }

    private void sendIntentTextViewInformationAbove(String infoAbove) {
        Log.d(TAG, "MÉTODO SEND INTENT TEXTVIEW INFORMATION ABOVE.");
        intent = new Intent();
        intent.setAction(ConstantsVideo.INTENT_SET_TEXTVIEW_INFORMATION_ABOVE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(ConstantsVideo.MESSAGE_TEXTVIEW_INFORMATION_ABOVE, infoAbove);
        context.sendBroadcast(intent);
    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        for (String url : urls) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet(url)).getEntity().getContent()));
                String str = "";
                while (true) {
                    str = buffer.readLine();
                    if (str == null) {
                        break;
                    }
                    response = response + str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @Override
    protected synchronized void onPostExecute(String response) {

        Log.d(TAG, "ASYNC TASK: ATUALIZANDO A VIEW DA ATIVIDADE VÍDEO.");

        try {

            result = String.valueOf(Html.fromHtml(response));

            resolution = new JSONObject(result).getJSONObject(SETTINGS).getString("2");
            framesPerSecond = new JSONObject(result).getJSONObject(SETTINGS).getString("3");
            fieldOfView = new JSONObject(result).getJSONObject(SETTINGS).getString("4");

            resolution = setView.getVideoResolution(resolution);
            framesPerSecond = setView.getVideoFramesPerSecond(framesPerSecond);
            fieldOfView = setView.getVideoFieldOfView(fieldOfView);
            informationAbove = resolution + " / " + " " + framesPerSecond + " / " + " " + fieldOfView;
            sendIntentTextViewInformationAbove(informationAbove);

        } catch (JSONException exception) {
            exception.printStackTrace();
            Log.e(TAG, "EXCEÇÃO ASYNC TASK UPDATE SETTINGS CAMERA: ", exception);
        }
    }
}

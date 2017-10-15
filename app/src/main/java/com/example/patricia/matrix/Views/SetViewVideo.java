package com.example.patricia.matrix.Views;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.patricia.matrix.Constants.ConstantsVideo;
import com.example.patricia.matrix.R;

public class SetViewVideo {

    private int battery;
    private int videoFormat;
    private int videoResolution;
    private int videoFramesPerSecond;
    private int videoFieldOfView;
    private int videoSpotMeter;
    private int videoProtune;
    private int videoWhiteBalance;
    private int videoColor;
    private int videoISO;
    private int videoSharpness;
    private int videoShutter;
    private int videoIntervals;
    private int videoRotation;
    private int videoQuickCapture;
    private int videoLEDBlink;
    private int videoBIPS;
    private int videoShowScreen;
    private int videoAutomaticShutdown;

    public void getVideoBattery(ImageView imageView, String valueBattery) {
        if (valueBattery != null) {
            battery = Integer.parseInt(valueBattery);
            switch (battery) {
                case ConstantsVideo.BATTERY_EXTREMELY_LOWER:
                    imageView.setImageResource(R.drawable.battery_zero);
                    break;
                case ConstantsVideo.BATTERY_LOWER:
                    imageView.setImageResource(R.drawable.battery_25);
                    break;
                case ConstantsVideo.BATTERY_HALFWAY:
                    imageView.setImageResource(R.drawable.battery_half);
                    break;
                case ConstantsVideo.BATTERY_FULL:
                    imageView.setImageResource(R.drawable.battery_full);
                    break;
                case ConstantsVideo.BATTERY_CHARGING:
                    imageView.setImageResource(R.drawable.battery_charging);
                    break;
            }
        }
    }

    public String getVideoResolution(String valueResolution) {
        if (valueResolution != null) {
            videoResolution = Integer.parseInt(valueResolution);

            switch (videoResolution) {
                case ConstantsVideo.VIDEO_RESOLUTION_4K:
                    return("4K");
                case ConstantsVideo.VIDEO_RESOLUTION_4K_SUPERVIEW:
                    return("4K SuperView");
                case ConstantsVideo.VIDEO_RESOLUTION_27K:
                    return("2.7K");
                case ConstantsVideo.VIDEO_RESOLUTION_27K_SUPERVIEW:
                    return("2.7K SuperView");
                case ConstantsVideo.VIDEO_RESOLUTION_27K_43:
                    return("2.7K 4:3");
                case ConstantsVideo.VIDEO_RESOLUTION_1440P:
                    return("1440");
                case ConstantsVideo.VIDEO_RESOLUTION_1080_SUPERVIEW:
                    return("1080p SuperView");
                case ConstantsVideo.VIDEO_RESOLUTION_1080P:
                    return("1080");
                case ConstantsVideo.VIDEO_RESOLUTION_960P:
                    return("960");
                case ConstantsVideo.VIDEO_RESOLUTION_720_SUPERVIEW:
                    return("720p SuperView");
                case ConstantsVideo.VIDEO_RESOLUTION_720P:
                    return("720");
                case ConstantsVideo.VIDEO_RESOLUTION_WVGA:
                    return("WVGA");
            }
        }
        return null;
    }

    public String getVideoFramesPerSecond(String valueFramesPerSecond) {
        if (valueFramesPerSecond != null) {
            videoFramesPerSecond = Integer.parseInt(valueFramesPerSecond);
            switch (videoFramesPerSecond) {
                case ConstantsVideo.VIDEO_FPS_240:
                    return("240");
                case ConstantsVideo.VIDEO_FPS_120:
                    return("120");
                case ConstantsVideo.VIDEO_FPS_90:
                    return("90");
                case ConstantsVideo.VIDEO_FPS_80:
                    return("80");
                case ConstantsVideo.VIDEO_FPS_60:
                    return("60");
                case ConstantsVideo.VIDEO_FPS_50:
                    return("50");
                case ConstantsVideo.VIDEO_FPS_48:
                    return("48");
                case ConstantsVideo.VIDEO_FPS_30:
                    return("30");
                case ConstantsVideo.VIDEO_FPS_25:
                    return("25");
                case ConstantsVideo.VIDEO_FPS_24:
                    return("24");
            }
        }
        return null;
    }

    public String getVideoFieldOfView(String valueFieldOfView) {
        if(valueFieldOfView != null) {
            videoFieldOfView = Integer.parseInt(valueFieldOfView);
            switch (videoFieldOfView) {
                case ConstantsVideo.VIDEO_FOV_WIDE:
                    return("Amplo");
                case ConstantsVideo.VIDEO_FOV_MEDIUM:
                    return("Médio");
                case ConstantsVideo.VIDEO_FOV_NARROW:
                    return ("Estreito");
            }
        }
        return null;
    }

    public void setVideoRotation(TextView textView, String valueRotation) {
        if(valueRotation != null) {
            videoRotation = Integer.parseInt(valueRotation);
            switch (videoRotation) {
                case ConstantsVideo.ROTATION_AUTO:
                    textView.setText("Auto");
                    break;
                case ConstantsVideo.ROTATION_UP:
                    textView.setText("Cima");
                    break;
                case ConstantsVideo.ROTATION_DOWN:
                    textView.setText("Baixo");
                    break;
            }
        }
    }

    public void setVideoQuickCapture(TextView textView, String valueQuickCapture) {
        if(valueQuickCapture != null) {
            videoQuickCapture = Integer.parseInt(valueQuickCapture);
            switch (videoQuickCapture) {
                case ConstantsVideo.QUICK_CAPTURE_OFF:
                    textView.setText("Desligado");
                    break;
                case ConstantsVideo.QUICK_CAPTURE_ON:
                    textView.setText("Ligado");
                    break;
            }
        }
    }

    public void setVideoLEDBlink(TextView textView, String valueLEDBlink) {
        if(valueLEDBlink != null) {
            videoLEDBlink = Integer.parseInt(valueLEDBlink);
            switch (videoLEDBlink) {
                case ConstantsVideo.LED_BLINK_OFF:
                    textView.setText("Desligado");
                    break;
                case ConstantsVideo.LED_BLINK_2:
                    textView.setText("2");
                    break;
                case ConstantsVideo.LED_BLINK_4:
                    textView.setText("4");
                    break;
            }
        }
    }

    public void setVideoBIPS(TextView textView, String valueBIPS) {
        if(valueBIPS != null) {
            videoBIPS = Integer.parseInt(valueBIPS);
            switch (videoBIPS) {
                case ConstantsVideo.BIPS_100:
                    textView.setText("100%");
                    break;
                case ConstantsVideo.BIPS_70:
                    textView.setText("70%");
                    break;
                case ConstantsVideo.BIPS_MUTE:
                    textView.setText("Mudo");
                    break;
            }
        }
    }

    public void setVideoShowScreen(TextView textView, String valueShowScreen) {
        if(valueShowScreen != null) {
            videoShowScreen = Integer.parseInt(valueShowScreen);
            switch(videoShowScreen) {
                case ConstantsVideo.LCD_DISPLAY_OFF:
                    textView.setText("Desligado");
                    break;
                case ConstantsVideo.LCD_DISPLAY_ON:
                    textView.setText("Ligado");
                    break;
            }
        }
    }

    public void setVideoAutomaticShutdown(TextView textView, String valueAutomaticShutdown) {  // Desligamento Automático
        if (valueAutomaticShutdown != null) {
            videoAutomaticShutdown = Integer.parseInt(valueAutomaticShutdown);
            switch (videoAutomaticShutdown) {
                case ConstantsVideo.OFF_NEVER:
                    textView.setText("Nunca");
                    break;
                case ConstantsVideo.OFF_1_MINUTE:
                    textView.setText("1 minuto");
                    break;
                case ConstantsVideo.OFF_2_MINUTES:
                    textView.setText("2 minutos");
                    break;
                case ConstantsVideo.OFF_3_MINUTES:
                    textView.setText("3 minutos");
                    break;
                case ConstantsVideo.OFF_5_MINUTES:
                    textView.setText("5 minutos");
                    break;
            }
        }
    }

    public void setVideoBattery(TextView textView, String valueBattery) {
        if(valueBattery != null) {
            battery = Integer.parseInt(valueBattery);
            switch (battery) {
                case ConstantsVideo.BATTERY_EXTREMELY_LOWER:
                    textView.setText("extremamente baixa");
                    break;
                case ConstantsVideo.BATTERY_LOWER:
                    textView.setText("baixa");
                    break;
                case ConstantsVideo.BATTERY_HALFWAY:
                    textView.setText("pela metade");
                    break;
                case ConstantsVideo.BATTERY_FULL:
                    textView.setText("cheia");
                    break;
                case ConstantsVideo.BATTERY_CHARGING:
                    textView.setText("carregando");
                    break;
            }
        }
    }

    public void setVideoFormat(TextView textView, String valueFormatVideo) {
        if(valueFormatVideo != null) {
            videoFormat = Integer.parseInt(valueFormatVideo);
            switch (videoFormat) {
                case ConstantsVideo.VIDEO_FORMAT_NTSC:
                    textView.setText("NTSC");
                    break;
                case ConstantsVideo.VIDEO_FORMAT_PAL:
                    textView.setText("PAL");
                    break;
            }
        }
    }

    public void setVideoResolution(TextView textView, String valueResolution) {
        if(valueResolution != null) {
            videoResolution = Integer.parseInt(valueResolution);
            switch (videoResolution) {
                case ConstantsVideo.VIDEO_RESOLUTION_4K:
                    textView.setText("4K");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_4K_SUPERVIEW:
                    textView.setText("4K SuperView");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_27K:
                    textView.setText("2.7K");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_27K_SUPERVIEW:
                    textView.setText("2.7K SuperView");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_27K_43:
                    textView.setText("2.7K 4:3");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_1440P:
                    textView.setText("1440");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_1080_SUPERVIEW:
                    textView.setText("1080 SuperView");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_1080P:
                    textView.setText("1080");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_960P:
                    textView.setText("960");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_720_SUPERVIEW:
                    textView.setText("720 SuperView");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_720P:
                    textView.setText("720");
                    break;
                case ConstantsVideo.VIDEO_RESOLUTION_WVGA:
                    textView.setText("WVGA");
                    break;
            }
        }
    }

    public void setVideoFramesPerSecond(TextView textView, String valueFramesPerSecond) {
        if(valueFramesPerSecond != null) {
            videoFramesPerSecond = Integer.parseInt(valueFramesPerSecond);
            switch (videoFramesPerSecond) {
                case ConstantsVideo.VIDEO_FPS_240:
                    textView.setText("240");
                    break;
                case ConstantsVideo.VIDEO_FPS_120:
                    textView.setText("120");
                    break;
                case ConstantsVideo.VIDEO_FPS_90:
                    textView.setText("90");
                    break;
                case ConstantsVideo.VIDEO_FPS_80:
                    textView.setText("80");
                    break;
                case ConstantsVideo.VIDEO_FPS_60:
                    textView.setText("60");
                    break;
                case ConstantsVideo.VIDEO_FPS_50:
                    textView.setText("50");
                    break;
                case ConstantsVideo.VIDEO_FPS_48:
                    textView.setText("48");
                    break;
                case ConstantsVideo.VIDEO_FPS_30:
                    textView.setText("30");
                    break;
                case ConstantsVideo.VIDEO_FPS_25:
                    textView.setText("25");
                    break;
                case ConstantsVideo.VIDEO_FPS_24:
                    textView.setText("24");
                    break;
            }
        }
    }


    public void setVideoFieldOfView(TextView textView, String valueFieldOfView) {
        if(valueFieldOfView != null) {
            videoFieldOfView = Integer.parseInt(valueFieldOfView);
            switch (videoFieldOfView) {
                case ConstantsVideo.VIDEO_FOV_WIDE:
                    textView.setText("Wide");
                    break;
                case ConstantsVideo.VIDEO_FOV_MEDIUM:
                    textView.setText("Medium");
                    break;
                case ConstantsVideo.VIDEO_FOV_NARROW:
                    textView.setText("Narrow");
                    break;
            }
        }
    }

    public void setVideoSpotMeter(TextView textView, String valueSpotMeter) {
        if(valueSpotMeter != null) {
            videoSpotMeter = Integer.parseInt(valueSpotMeter);
            switch (videoSpotMeter) {
                case ConstantsVideo.VIDEO_SPOT_METER_ON:
                    textView.setText("ON");
                    break;
                case ConstantsVideo.VIDEO_SPOT_METER_OFF:
                    textView.setText("OFF");
                    break;
            }
        }
    }

    public void setVideoProtune(TextView textView, String valueProtune) {
        if(valueProtune != null) {
            videoProtune = Integer.parseInt(valueProtune);
            switch (videoProtune) {
                case ConstantsVideo.VIDEO_PROTUNE_ON:
                    textView.setText("ON");
                    break;
                case ConstantsVideo.VIDEO_PROTUNE_OFF:
                    textView.setText("OFF");
                    break;
            }
        }
    }

    public void setVideoWhiteBalance(TextView textView, String valueWhiteBalance) {
        if(valueWhiteBalance != null) {
            videoWhiteBalance = Integer.parseInt(valueWhiteBalance);
            switch (videoWhiteBalance) {
                case ConstantsVideo.VIDEO_WHITE_BALANCE_AUTO:
                    textView.setText("Auto");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_3000K:
                    textView.setText("3000K");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_5500K:
                    textView.setText("5500K");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_6500K:
                    textView.setText("6500K");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_NATIVE:
                    textView.setText("Native");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_4000K:
                    textView.setText("4000K");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_4800K:
                    textView.setText("4800K");
                    break;
                case ConstantsVideo.VIDEO_WHITE_BALANCE_6000K:
                    textView.setText("6000K");
                    break;
            }
        }
    }

    public void setVideoColor(TextView textView, String valueColor) {
        if(valueColor != null) {
            videoColor = Integer.parseInt(valueColor);
            switch (videoColor) {
                case ConstantsVideo.VIDEO_COLOR_FLAT:
                    textView.setText("Flat");
                    break;
                case ConstantsVideo.VIDEO_COLOR_GOPRO:
                    textView.setText("GoPro");
                    break;
            }
        }
    }

    public void setISOVideo(TextView textView, String valueISO) {
        if(valueISO != null) {
            videoISO = Integer.parseInt(valueISO);
            switch (videoISO) {
                case ConstantsVideo.VIDEO_ISO_LIMIT_400:
                    textView.setText("400");
                    break;
                case ConstantsVideo.VIDEO_ISO_LIMIT_800:
                    textView.setText("800");
                    break;
                case ConstantsVideo.VIDEO_ISO_LIMIT_1600:
                    textView.setText("1600");
                    break;
                case ConstantsVideo.VIDEO_ISO_LIMIT_3200:
                    textView.setText("3200");
                    break;
                case ConstantsVideo.VIDEO_ISO_LIMIT_6400:
                    textView.setText("6400");
                    break;
            }
        }
    }

    public void setVideoSharpness(TextView textView, String valueSharpness) {
        if(valueSharpness != null) {
            videoSharpness = Integer.parseInt(valueSharpness);
            switch (videoSharpness) {
                case ConstantsVideo.VIDEO_SHARPNESS_HIGH:
                    textView.setText("High");
                    break;
                case ConstantsVideo.VIDEO_SHARPNESS_MEDIUM:
                    textView.setText("Medium");
                    break;
                case ConstantsVideo.VIDEO_SHARPNESS_LOW:
                    textView.setText("Low");
                    break;
            }
        }
    }

    public void setIntervalsVideoAndPhoto (TextView textView, String valueIntervals) {
        if(valueIntervals != null) {
            videoIntervals = Integer.parseInt(valueIntervals);
            switch (videoIntervals) {
                case ConstantsVideo.VIDEO_AND_PHOTO_INTERVAL_5_SECONDS:
                    textView.setText("1 Foto/5 seg");
                    break;
                case ConstantsVideo.VIDEO_AND_PHOTO_INTERVAL_10_SECONDS:
                    textView.setText("1 Foto/10 seg");
                    break;
                case ConstantsVideo.VIDEO_AND_PHOTO_INTERVAL_30_SECONDS:
                    textView.setText("1 Foto/30 seg");
                    break;
                case ConstantsVideo.VIDEO_AND_PHOTO_INTERVAL_60_SECONDS:
                    textView.setText("1 Foto/60 seg");
                    break;
            }
        }
    }

    public void setIntervalsVideoLooping (TextView textView, String valueIntervals) {
        if(valueIntervals != null) {
            videoIntervals = Integer.parseInt(valueIntervals);
            switch (videoIntervals) {
                case ConstantsVideo.LOOPING_VIDEO_INTERVAL_MAX_MINUTES:
                    textView.setText("Máximo");
                    break;
                case ConstantsVideo.LOOPING_VIDEO_INTERVAL_5_MINUTES:
                    textView.setText("5 minutos");
                    break;
                case ConstantsVideo.LOOPING_VIDEO_INTERVAL_20_MINUTES:
                    textView.setText("20 minutos");
                    break;
                case ConstantsVideo.LOOPING_VIDEO_INTERVAL_60_MINUTES:
                    textView.setText("60 minutos");
                    break;
                case ConstantsVideo.LOOPING_VIDEO_INTERVAL_120_MINUTES:
                    textView.setText("120 minutos");
                    break;
            }
        }
    }

    public void setIntervalsVideoTimeLapse (TextView textView, String valueIntervals) {
        if(valueIntervals != null) {
            videoIntervals = Integer.parseInt(valueIntervals);
            switch (videoIntervals) {
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_MEIO_SECOND:
                    textView.setText("0.5 segundos");
                    break;
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_1_SECOND:
                    textView.setText("1 segundo");
                    break;
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_2_SECONDS:
                    textView.setText("2 segundos");
                    break;
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_5_SECONDS:
                    textView.setText("5 segundos");
                    break;
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_10_SECONDS:
                    textView.setText("10 segundos");
                    break;
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_30_SECONDS:
                    textView.setText("30 segundos");
                    break;
                case ConstantsVideo.TIMELAPSE_VIDEO_INTERVAL_60_SECONDS:
                    textView.setText("60 segundos");
                    break;
            }
        }
    }

    public void setVideoShutter(TextView textView, String valueShutter) {
        if(valueShutter != null) {
            videoShutter = Integer.parseInt(valueShutter);
            switch (videoShutter) {
                case ConstantsVideo.VIDEO_SHUTTER_AUTOMATIC:
                    textView.setText("Auto");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_24:
                    textView.setText("1/24");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_48:
                    textView.setText("1/48");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_25:
                    textView.setText("1/25");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_50:
                    textView.setText("1/50");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_100:
                    textView.setText("1/100");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_30:
                    textView.setText("1/30");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_60:
                    textView.setText("1/60");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_120:
                    textView.setText("1/120");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_96:
                    textView.setText("1/96");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_192:
                    textView.setText("1/192");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_200:
                    textView.setText("1/200");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_90:
                    textView.setText("1/90");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_180:
                    textView.setText("1/180");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_360:
                    textView.setText("1/360");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_240:
                    textView.setText("1/240");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_480:
                    textView.setText("1/480");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_960:
                    textView.setText("1/960");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_80:
                    textView.setText("1/80");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_160:
                    textView.setText("1/160");
                    break;
                case ConstantsVideo.VIDEO_SHUTTER_320:
                    textView.setText("1/320");
                    break;
            }
        }
    }
}
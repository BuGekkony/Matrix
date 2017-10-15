package com.example.patricia.matrix.Constants;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class ConstantsVideo {

    public static final String INTENT_SET_TEXTVIEW_INFORMATION_ABOVE = "com.example.patricia.matrix.intent.action.INTENT_SET_TEXTVIEW_INFORMATION_ABOVE";
    public static final String MESSAGE_TEXTVIEW_INFORMATION_ABOVE = "message_textview_information_above";

    public static final String IP_GOPRO = "10.5.5.100";

    public static final int VIDEO_MODE = 0;
    public static final int VIDEO_FORMAT_NTSC = 0;
    public static final int VIDEO_FORMAT_PAL = 1;

    public static final int VIDEO_RESOLUTION_4K = 1;
    public static final int VIDEO_RESOLUTION_4K_SUPERVIEW = 2;
    public static final int VIDEO_RESOLUTION_27K_SUPERVIEW = 5;
    public static final int VIDEO_RESOLUTION_27K = 4;
    public static final int VIDEO_RESOLUTION_27K_43 = 6;
    public static final int VIDEO_RESOLUTION_1440P = 7;
    public static final int VIDEO_RESOLUTION_1080_SUPERVIEW = 8;
    public static final int VIDEO_RESOLUTION_1080P = 9;
    public static final int VIDEO_RESOLUTION_960P = 10;
    public static final int VIDEO_RESOLUTION_720_SUPERVIEW = 11;
    public static final int VIDEO_RESOLUTION_720P = 12;
    public static final int VIDEO_RESOLUTION_WVGA = 13;

    public static final int VIDEO_FPS_240 = 0;
    public static final int VIDEO_FPS_120 = 1;
    public static final int VIDEO_FPS_90 = 3;
    public static final int VIDEO_FPS_80 = 4;
    public static final int VIDEO_FPS_60 = 5;
    public static final int VIDEO_FPS_50 = 6;
    public static final int VIDEO_FPS_48 = 7;
    public static final int VIDEO_FPS_30 = 8;
    public static final int VIDEO_FPS_25 = 9;
    public static final int VIDEO_FPS_24 = 10;

    public static final int VIDEO_FOV_WIDE = 0;
    public static final int VIDEO_FOV_MEDIUM = 1;
    public static final int VIDEO_FOV_NARROW = 2;

    public static final int TIMELAPSE_VIDEO_INTERVAL_MEIO_SECOND = 0;
    public static final int TIMELAPSE_VIDEO_INTERVAL_1_SECOND = 1;
    public static final int TIMELAPSE_VIDEO_INTERVAL_2_SECONDS = 2;
    public static final int TIMELAPSE_VIDEO_INTERVAL_5_SECONDS = 3;
    public static final int TIMELAPSE_VIDEO_INTERVAL_10_SECONDS = 4;
    public static final int TIMELAPSE_VIDEO_INTERVAL_30_SECONDS = 5;
    public static final int TIMELAPSE_VIDEO_INTERVAL_60_SECONDS = 6;

    public static final int LOOPING_VIDEO_INTERVAL_MAX_MINUTES = 0;
    public static final int LOOPING_VIDEO_INTERVAL_5_MINUTES = 1;
    public static final int LOOPING_VIDEO_INTERVAL_20_MINUTES = 2;
    public static final int LOOPING_VIDEO_INTERVAL_60_MINUTES = 3;
    public static final int LOOPING_VIDEO_INTERVAL_120_MINUTES = 4;

    public static final int VIDEO_SPOT_METER_ON = 1;
    public static final int VIDEO_SPOT_METER_OFF = 0;

    public static final int VIDEO_PROTUNE_ON = 1;
    public static final int VIDEO_PROTUNE_OFF = 0;

    public static final int VIDEO_WHITE_BALANCE_AUTO = 0;
    public static final int VIDEO_WHITE_BALANCE_3000K = 1;
    public static final int VIDEO_WHITE_BALANCE_4000K = 5;
    public static final int VIDEO_WHITE_BALANCE_4800K = 6;
    public static final int VIDEO_WHITE_BALANCE_5500K = 2;
    public static final int VIDEO_WHITE_BALANCE_6000K = 7;
    public static final int VIDEO_WHITE_BALANCE_6500K = 3;
    public static final int VIDEO_WHITE_BALANCE_NATIVE = 4;

    public static final int VIDEO_COLOR_GOPRO = 0;
    public static final int VIDEO_COLOR_FLAT = 1;

    public static final int VIDEO_SHUTTER_AUTOMATIC = 0;
    public static final int VIDEO_SHUTTER_24 = 3;
    public static final int VIDEO_SHUTTER_25 = 4;
    public static final int VIDEO_SHUTTER_30 = 5;
    public static final int VIDEO_SHUTTER_48 = 6;
    public static final int VIDEO_SHUTTER_50 = 7;
    public static final int VIDEO_SHUTTER_60 = 8;
    public static final int VIDEO_SHUTTER_80 = 9;
    public static final int VIDEO_SHUTTER_90 = 10;
    public static final int VIDEO_SHUTTER_96 = 11;
    public static final int VIDEO_SHUTTER_100 = 12;
    public static final int VIDEO_SHUTTER_120 = 13;
    public static final int VIDEO_SHUTTER_160 = 14;
    public static final int VIDEO_SHUTTER_180 = 15;
    public static final int VIDEO_SHUTTER_192 = 16;
    public static final int VIDEO_SHUTTER_200 = 17;
    public static final int VIDEO_SHUTTER_240 = 18;
    public static final int VIDEO_SHUTTER_320 = 19;
    public static final int VIDEO_SHUTTER_360 = 20;
    public static final int VIDEO_SHUTTER_480 = 22;
    public static final int VIDEO_SHUTTER_960 = 23;

    public static final int VIDEO_ISO_LIMIT_6400 = 0;
    public static final int VIDEO_ISO_LIMIT_1600 = 1;
    public static final int VIDEO_ISO_LIMIT_400 = 2;
    public static final int VIDEO_ISO_LIMIT_3200 = 3;
    public static final int VIDEO_ISO_LIMIT_800 = 4;

    public static final int VIDEO_SHARPNESS_HIGH = 0;
    public static final int VIDEO_SHARPNESS_MEDIUM = 1;
    public static final int VIDEO_SHARPNESS_LOW = 2;

    public static final int VIDEO_AND_PHOTO_INTERVAL_5_SECONDS = 1;
    public static final int VIDEO_AND_PHOTO_INTERVAL_10_SECONDS = 2;
    public static final int VIDEO_AND_PHOTO_INTERVAL_30_SECONDS = 3;
    public static final int VIDEO_AND_PHOTO_INTERVAL_60_SECONDS = 4;

    public static final int BATTERY_EXTREMELY_LOWER = 0;
    public static final int BATTERY_LOWER = 1;
    public static final int BATTERY_HALFWAY = 2;
    public static final int BATTERY_FULL = 3;
    public static final int BATTERY_CHARGING = 4;

    public static final int LCD_DISPLAY_ON = 1;
    public static final int LCD_DISPLAY_OFF = 0;

    public static final int OFF_NEVER = 0;
    public static final int OFF_1_MINUTE = 1;
    public static final int OFF_2_MINUTES = 2;
    public static final int OFF_3_MINUTES = 3;
    public static final int OFF_5_MINUTES = 4;

    public static final int BIPS_MUTE = 2;
    public static final int BIPS_70 = 1;
    public static final int BIPS_100 = 0;

    public static final int LED_BLINK_OFF = 0;
    public static final int LED_BLINK_2 = 1;
    public static final int LED_BLINK_4 = 2;

    public static final int QUICK_CAPTURE_ON = 1;
    public static final int QUICK_CAPTURE_OFF = 0;

    public static final int ROTATION_AUTO = 0;
    public static final int ROTATION_UP = 1;
    public static final int ROTATION_DOWN = 2;
}
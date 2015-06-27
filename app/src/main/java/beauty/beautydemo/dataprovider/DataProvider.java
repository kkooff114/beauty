package beauty.beautydemo.dataprovider;

import android.content.Context;
import android.content.SharedPreferences;

import beauty.beautydemo.application.BeautyApplication;

/**
 * Created by LJW on 15/3/26.
 */
public class DataProvider {
    public static final String PREFS_NAME = "PrefsFile";
    public static final String WINDOW_WIDTH = "WindowWith";
    public static final String WINDOW_HEIGHT = "WindowHight";
    public static final String STATUS_HEIGHT = "statusHeight";


    //屏幕宽度
    public static int getWindowWidth() {
        SharedPreferences settings = BeautyApplication.getmContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        int userPhone = settings.getInt(WINDOW_WIDTH, 0);
        return userPhone;
    }

    public static void setWindowWidth(int phoneNumber) {
        SharedPreferences settings = BeautyApplication.getmContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(WINDOW_WIDTH, phoneNumber);
        editor.commit();
    }

    //屏幕高度
    public static int getWindowHeight() {
        SharedPreferences settings = BeautyApplication.getmContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        int userPhone = settings.getInt(WINDOW_HEIGHT, 0);
        return userPhone;
    }

    public static void setWindowHeight(int phoneNumber) {
        SharedPreferences settings = BeautyApplication.getmContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(WINDOW_HEIGHT, phoneNumber);
        editor.commit();
    }

    //状态栏高度
    public static int getStatusHeight() {
        SharedPreferences settings = BeautyApplication.getmContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        int userPhone = settings.getInt(STATUS_HEIGHT, 0);
        return userPhone;
    }

    public static void setStatusHeight(int phoneNumber) {
        SharedPreferences settings = BeautyApplication.getmContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(STATUS_HEIGHT, phoneNumber);
        editor.commit();
    }

}

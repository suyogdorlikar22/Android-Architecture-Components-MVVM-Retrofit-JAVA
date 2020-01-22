package com.snipex.suyog.mvvm.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;


import com.snipex.suyog.mvvm.R;

import org.json.JSONObject;

public class AppPreferences {

    public static AppPreferences appPreferences;
    public Context context;
    public SharedPreferences sharedPreferences;

    public AppPreferences(Context context) {
        this.context = context;
        Resources res = this.context.getResources();
        String preferencesName = res.getString(R.string.app_name);
        sharedPreferences = this.context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }

    public static AppPreferences getAppPreferences(Context context) {
        if (appPreferences != null) {
            return appPreferences;
        }
        appPreferences = new AppPreferences(context);
        return appPreferences;
    }

    //=============== For set value =====
    //      AppPreferences appPreferences = AppPreferences.getAppPreferences(context);
    //      appPreferences.putString("sessid", sessid);
    //      appPreferences.putString("session_name", session_name);

    //== For get value ==========
    //     if (!appPreferences.getString("sessid", "").equalsIgnoreCase("sessid")) {
    //        appPreferences.putString("sessid", "");
    //    }

    //=============== For remove from sharepreparance
    //    appPreferences.remove("user_id");


    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public int putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
        return value;
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public void putStringjson(String key, JSONObject json) {
        // TODO Auto-generated method stub

        String jjson = "" + json;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, jjson);
        editor.commit();
    }

    //Image Request Params
    public String getValue(Context context, String key) {
        return getAppPreferences(context).getString(key, "");
    }

    public boolean putValue(Context context, String key,
                            String value) {
        value = value == null ? "" : value;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        boolean result = editor.commit();
        return result;
    }

}

package com.nitingupta.xebiaassignment.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by nitin on 5/8/16.
 */
public class CommonUtil {
    public static String API_URL =
            "http://api.openweathermap.org/data/2.5/forecast/daily?q=Philadelphia&mode=json&units=metric&cnt=16&APPID=f279ee6273c82ce2e30067a8d9511fae";
    public static String IMAGE_URL = "http://openweathermap.org/img/w/";
    public static Object loadJSONFromString(Context mContext, String json, Class<?> className) {
        try {
            Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC, Modifier.PROTECTED).create();
            return gson.fromJson(json, className);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static boolean isOnline(Context context) {
        if (context == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean checkNetwork(Context mContext) {
        if (mContext == null)
            return false;
        //Check whether network is available or not.
        if (!isOnline(mContext)) {
            return false;
        }
        return true;
    }
    public static String degToCompass(int num) {
        int val = (int)Math.floor((num / 22.5) + 0.5);
        String arr[] = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        return arr[val % 16];
    }
}

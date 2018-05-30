package com.zy.zytools.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * author：zy
 * date:2018/5/8
 * time:13:56
 */

public class Utils {
    public static void showLog(String text) {
        if (text != null) {
            Log.e("showlog", text);
        }
    }
    public static void toast(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
    //创建一个map
    public static Map<String, String> createMap(String[] keys, String[] values) {
        Map map = new HashMap();
        if ((keys != null) && (values != null) && (keys.length == values.length)) {
            for (int i = 0; i < keys.length; i++) {
                map.put(keys[i], values[i]);
            }
        }
        return map;
    }
}

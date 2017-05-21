package net.kkmike.sptest;

import android.content.SharedPreferences;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by kkmike999 on 2017/5/20.
 */
public class SharedPreferencesHelper {

    private static Map<String, SharedPreferences> map = new ConcurrentHashMap<>();

    public static SharedPreferences getInstance(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        } else {
            SharedPreferences sharedPreferences = new ShadowSharedPreference();

            map.put(name, sharedPreferences);

            return sharedPreferences;
        }
    }

    public static void clean() {
        map.clear();
    }

    public static SharedPreferences newInstance() {
        return new ShadowSharedPreference();
    }
}

package net.kkmike.app.utils;

import android.content.Context;

/**
 * Created by kkmike999 on 2017/5/20.
 */
public class ContextProvider {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        ContextProvider.context = context;
    }
}

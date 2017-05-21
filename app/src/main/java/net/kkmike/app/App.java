package net.kkmike.app;

import android.app.Application;

import net.kkmike.app.utils.ContextProvider;

/**
 * Created by kkmike999 on 2017/5/21.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ContextProvider.setContext(this);
    }
}

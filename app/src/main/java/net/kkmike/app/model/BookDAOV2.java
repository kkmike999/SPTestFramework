package net.kkmike.app.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kkmike999 on 2017/5/20.
 */
public class BookDAOV2 {

    SharedPreferences        mSharedPre;
    SharedPreferences.Editor mEditor;

    protected BookDAOV2(SharedPreferences sharedPre) {
        this.mSharedPre = sharedPre;
        this.mEditor = sharedPre.edit();
    }

    public BookDAOV2(Context context) {
        this(context.getSharedPreferences("book", Context.MODE_PRIVATE));
    }

    public void clearAllRead() {
        mEditor.clear();
        mEditor.commit();
    }
}

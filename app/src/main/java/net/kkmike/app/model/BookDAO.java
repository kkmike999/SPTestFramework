package net.kkmike.app.model;

import android.content.Context;
import android.content.SharedPreferences;

import net.kkmike.app.utils.ContextProvider;

/**
 * Created by kkmike999 on 2017/5/20.
 */
public class BookDAO {

    SharedPreferences        mSharedPre;
    SharedPreferences.Editor mEditor;

    protected BookDAO(SharedPreferences sharedPre) {
        this.mSharedPre = sharedPre;
        this.mEditor = sharedPre.edit();
    }

    public BookDAO(Context context) {
        this(context.getSharedPreferences("book", Context.MODE_PRIVATE));
    }

    public BookDAO() {
        this.mSharedPre = ContextProvider.getContext().getSharedPreferences("book", Context.MODE_PRIVATE);
        this.mEditor = mSharedPre.edit();
    }

    /**
     * 设置某book是否已读
     *
     * @param bookId 书本id
     * @param isRead 是否已读
     */
    public void setBookRead(int bookId, boolean isRead) {
        mEditor.putBoolean(String.valueOf(bookId), isRead);
        mEditor.commit();
    }

    /**
     * book是否已读
     *
     * @param bookId 书本id
     * @return
     */
    public boolean isBookRead(int bookId) {
        return mSharedPre.getBoolean(String.valueOf(bookId), false);
    }
}

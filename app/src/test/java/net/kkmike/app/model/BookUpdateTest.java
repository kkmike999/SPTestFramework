package net.kkmike.app.model;

import net.kkmike.app.SharedPrefCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kkmike999 on 2017/5/21.
 */
public class BookUpdateTest extends SharedPrefCase {

    BookDAO   bookDAO;
    BookDAOV2 bookDAOV2;

    @Before
    public void setUp() throws Exception {
        bookDAO = new BookDAO(getInstance("book"));
        bookDAOV2 = new BookDAOV2(getInstance("book"));

        //        SharedPreferences sharedPref = SharedPreferencesHelper.newInstance();
        //
        //        bookDAO = new BookDAO(sharedPref);
        //        bookDAOV2 = new BookDAOV2(sharedPref);
    }

    @Test
    public void testClearAllRead() {
        int bookId = 10;

        // 设置已读
        bookDAO.setBookRead(bookId, true);

        // 已读
        Assert.assertTrue(bookDAO.isBookRead(bookId));

        // 清除已读
        bookDAOV2.clearAllRead();

        // 未读
        Assert.assertFalse(bookDAO.isBookRead(bookId));
    }
}

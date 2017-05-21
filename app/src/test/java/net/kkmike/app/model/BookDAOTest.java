package net.kkmike.app.model;

import net.kkmike.app.SharedPrefCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kkmike999 on 2017/5/20.
 *
 * {@linkplain BookDAO} 单元测试
 */
public class BookDAOTest extends SharedPrefCase {

    BookDAO bookDAO;

    @Before
    public void setUp() throws Exception {
        bookDAO = new BookDAO(getInstance("book"));
    }

    @Test
    public void isBookRead() throws Exception {
        int bookId = 10;

        // 未读
        Assert.assertFalse(bookDAO.isBookRead(bookId));

        // 设置已读
        bookDAO.setBookRead(bookId, true);

        // 已读
        Assert.assertTrue(bookDAO.isBookRead(bookId));
    }
}
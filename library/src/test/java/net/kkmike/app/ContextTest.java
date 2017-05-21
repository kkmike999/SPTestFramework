package net.kkmike.app;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kkmike999 on 2017/5/21.
 */
public class ContextTest extends SharedPrefCase {

    SharedPreferences mSharedPreferences;

    @Before
    public void setUp() throws Exception {
        Context context = getContext();

        mSharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);
    }

    /**
     * 测试从Context获取的SharedPreferences实际对象
     */
    @Test
    public void testSharedPreferencesObject() {
        Assert.assertTrue(mSharedPreferences.getClass().equals(ShadowSharedPreference.class));
    }
}

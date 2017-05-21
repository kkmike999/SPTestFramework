package net.kkmike.sptest;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import net.kkmike.sptest.SharedPreferencesHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by kkmike999 on 2017/5/20.
 *
 * 测试ShadowSharedPreferences正确性
 */
public class SharePreferenceTest {

    SharedPreferences        mSharedPre;
    SharedPreferences.Editor mEditor;

    Random random;

    @Before
    public void setUp() throws Exception {
        random = new Random(System.currentTimeMillis());

        mSharedPre = SharedPreferencesHelper.newInstance();
        mEditor = mSharedPre.edit();

        Assert.assertEquals(0, mSharedPre.getAll().size());
    }

    @Test
    public void testInt() {
        mEditor.putInt("key", 1);
        mEditor.commit();

        Assert.assertEquals(1, mSharedPre.getInt("key", -1));
    }

    @Test
    public void testLong() {
        long l = random.nextLong();

        mEditor.putLong("key", l);
        mEditor.commit();

        Assert.assertEquals(l, mSharedPre.getLong("key", 0));
    }

    @Test
    public void testFloat() {
        float f = random.nextFloat();

        mEditor.putFloat("key", f);
        mEditor.commit();

        Assert.assertEquals(f, mSharedPre.getFloat("key", 0), 0);
    }

    @Test
    public void testBoolean() {
        boolean b = random.nextBoolean();

        mEditor.putBoolean("key", b);
        mEditor.commit();

        Assert.assertEquals(b, mSharedPre.getBoolean("key", true));
    }

    @Test
    public void testString() {
        mEditor.putString("key", "value");
        mEditor.commit();

        Assert.assertEquals("value", mSharedPre.getString("key", ""));
    }

    @Test
    public void testStringSet() {
        Assert.assertEquals(null, mSharedPre.getStringSet("set", null));

        Set<String> set = new HashSet<>();
        set.add("testA");

        mEditor.putStringSet("set", set);
        mEditor.commit();

        // 第一次结果
        Set<String> cacheSet = mSharedPre.getStringSet("set", null);

        // 不能是同一个对象
        Assert.assertFalse(set == cacheSet);
        // 内容相同
        Assert.assertEquals(set, cacheSet);

        // 原Set继续添加元素
        set.add("testB");

        // 不影响第一次结果
        Assert.assertNotEquals(set, cacheSet);

        mEditor.putStringSet("set", set);
        mEditor.commit();

        // 第二次结果
        Set<String> cacheSet2 = mSharedPre.getStringSet("set", null);

        Assert.assertFalse(cacheSet == cacheSet2);
        // 结果2与原数据相同
        Assert.assertEquals(set, cacheSet2);
    }

    @Test
    public void testContains() {
        mEditor.putString("string", "value");
        mEditor.putInt("int", 1);
        mEditor.putLong("long", 1L);
        mEditor.putFloat("float", 1.0f);
        mEditor.putBoolean("boolean", true);
        mEditor.putStringSet("set", new HashSet<String>());

        // 未commit，没有真正储存

        Assert.assertFalse(mSharedPre.contains("string"));
        Assert.assertFalse(mSharedPre.contains("int"));
        Assert.assertFalse(mSharedPre.contains("long"));
        Assert.assertFalse(mSharedPre.contains("float"));
        Assert.assertFalse(mSharedPre.contains("boolean"));
        Assert.assertFalse(mSharedPre.contains("set"));

        mEditor.commit();

        Assert.assertTrue(mSharedPre.contains("string"));
        Assert.assertTrue(mSharedPre.contains("int"));
        Assert.assertTrue(mSharedPre.contains("long"));
        Assert.assertTrue(mSharedPre.contains("float"));
        Assert.assertTrue(mSharedPre.contains("boolean"));
        Assert.assertTrue(mSharedPre.contains("set"));
    }

    /**
     * 测试改变值类型
     */
    @Test
    public void testChangeValueType() {

        mEditor.putInt("key", 1);
        mEditor.commit();

        int value = mSharedPre.getInt("key", 0);

        Assert.assertEquals(value, 1);

        mEditor.putBoolean("key", true);
        mEditor.commit();

        boolean b = mSharedPre.getBoolean("key", false);

        Assert.assertEquals(b, true);
    }

    @Test
    public void testListener() {

        OnSharedPreferenceChangeListener listener = mock(OnSharedPreferenceChangeListener.class);

        mSharedPre.registerOnSharedPreferenceChangeListener(listener);

        mEditor.putInt("key", 1);
        mEditor.commit();

        // 值从无到有，回调
        verify(listener).onSharedPreferenceChanged(eq(mSharedPre), eq("key"));

        // 改变值
        mEditor.putInt("key", 2);
        mEditor.commit();

        // 第二次回调
        verify(listener, times(2)).onSharedPreferenceChanged(eq(mSharedPre), eq("key"));

        // 改变值类型
        mEditor.putBoolean("key", true);
        mEditor.commit();

        // 第三次回调
        verify(listener, times(3)).onSharedPreferenceChanged(eq(mSharedPre), eq("key"));

        // 移除监听
        mSharedPre.unregisterOnSharedPreferenceChangeListener(listener);

        mEditor.putString("key", "...");
        mEditor.commit();

        // 不再回调（保持3次）
        verify(listener, times(3)).onSharedPreferenceChanged(eq(mSharedPre), eq("key"));
    }

    @Test
    public void testRemove() {
        mEditor.putInt("key", 1);
        mEditor.commit();

        Assert.assertTrue(mSharedPre.contains("key"));

        mEditor.remove("key");
        mEditor.putInt("key2", 2);
        mEditor.commit();

        Assert.assertFalse(mSharedPre.contains("key"));
        Assert.assertTrue(mSharedPre.contains("key2"));
    }

    @Test
    public void testClear() {
        mEditor.putInt("key", 1);
        mEditor.commit();

        Assert.assertTrue(mSharedPre.contains("key"));

        mEditor.clear();
        mEditor.putInt("key2", 2);

        Assert.assertTrue(mSharedPre.contains("key"));
        Assert.assertFalse(mSharedPre.contains("key2"));

        mEditor.commit();

        Assert.assertFalse(mSharedPre.contains("key"));
        Assert.assertTrue(mSharedPre.contains("key2"));
    }
}

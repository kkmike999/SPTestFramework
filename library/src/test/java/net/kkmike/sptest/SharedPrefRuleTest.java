package net.kkmike.sptest;

import android.content.Context;

import net.kkmike.sptest.SharedPrefRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by kkmike999 on 2017/5/21.
 */
public class SharedPrefRuleTest {

    Context mContext;

    @Rule
    public SharedPrefRule rule = new SharedPrefRule(new SharedPrefRule.ContextSetter() {
        @Override
        public void onContext(Context context) {
            mContext = context;
        }
    });

    @Test
    public void testContextNotNull() {
        Assert.assertNotNull(mContext);
    }

}

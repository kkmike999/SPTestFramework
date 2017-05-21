package net.kkmike.sptest;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Rule;

/**
 * Created by kkmike999 on 2017/5/20.
 */
public class SharedPrefCase {

    @Rule
    public SharedPrefRule rule;

    public SharedPrefCase() {
        rule = new SharedPrefRule();
    }

    public SharedPrefCase(SharedPrefRule.ContextSetter contextSetter) {
        rule = new SharedPrefRule(contextSetter);
    }

    public SharedPreferences getInstance(String name) {
        return SharedPreferencesHelper.getInstance(name);
    }

    public SharedPreferences newSharedPreferences() {
        return SharedPreferencesHelper.newInstance();
    }

    /**
     * 获取mock Context对象，仅能调用{@linkplain Context#getSharedPreferences(String, int)}
     *
     * @return
     */
    public Context getContext() {
        return rule.getContext();
    }
}

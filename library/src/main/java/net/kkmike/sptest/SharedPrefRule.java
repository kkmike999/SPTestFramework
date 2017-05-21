package net.kkmike.sptest;

import android.content.Context;
import android.support.annotation.Nullable;

import org.junit.rules.ExternalResource;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

/**
 * Created by kkmike999 on 2017/5/20.
 */
public class SharedPrefRule extends ExternalResource {

    Context       mContext;
    ContextSetter mContextSetter;

    public SharedPrefRule(@Nullable ContextSetter setter) {
        mContextSetter = setter;

        mContext = mock(Context.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String name = (String) invocation.getArguments()[0];
                return SharedPreferencesHelper.getInstance(name);
            }
        }).when(mContext).getSharedPreferences(anyString(), anyInt());
    }

    public SharedPrefRule() {
        this(null);
    }

    @Override
    protected void before() throws Throwable {
        if (mContextSetter != null) {
            mContextSetter.onContext(mContext);
        }
    }

    @Override
    protected void after() {
        SharedPreferencesHelper.clean();
    }

    public Context getContext() {
        return mContext;
    }

    public interface ContextSetter {
        void onContext(Context context);
    }
}

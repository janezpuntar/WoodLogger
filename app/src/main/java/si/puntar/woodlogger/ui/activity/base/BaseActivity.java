package si.puntar.woodlogger.ui.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected abstract void inject();

    @LayoutRes
    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        inject();
    }
}

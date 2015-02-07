package si.puntar.woodlogger.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

    }
}

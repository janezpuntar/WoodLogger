package si.puntar.woodlogger.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by Janez Puntar on 06/02/15.
 */
public abstract class BaseActivity extends ActionBarActivity {

    @Inject AppContainer appContainer;

    private ViewGroup viewGroup;

    protected abstract void inject();

    @LayoutRes
    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();

        viewGroup = appContainer.get(this);
        getLayoutInflater().inflate(getLayout(), viewGroup);
    }
}

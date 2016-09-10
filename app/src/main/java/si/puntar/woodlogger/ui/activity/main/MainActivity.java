package si.puntar.woodlogger.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.ui.activity.addMeasurement.MeasurementActivity;
import si.puntar.woodlogger.ui.activity.base.BaseActivity;
import si.puntar.woodlogger.ui.fragment.addMeasurement.AddMeasurementFragment;
import timber.log.Timber;


public class MainActivity extends BaseActivity implements MainView {

    @Inject MainPresenter presenter;

    @InjectView(R.id.tb_header) Toolbar toolbar;
    @InjectView(R.id.rv_previous_measurements) RecyclerView rvPreviousMeasurement;

    @Override
    protected void inject() {
        MainComponent mainComponent = DaggerMainComponent.builder()
                .appComponent(App.get(this)
                .getComponent())
                .mainModule(new MainModule(this)).build();
        mainComponent.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        rvPreviousMeasurement.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add_measurement:
                Intent addMeasureActivity = new Intent(this, MeasurementActivity.class);
                startActivity(addMeasureActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

package si.puntar.woodlogger.ui.activity.addMeasurement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.ui.activity.base.BaseActivity;
import si.puntar.woodlogger.ui.fragment.addMeasurement.AddMeasurementFragment;
import si.puntar.woodlogger.ui.widget.DividerItemDecoration;

/**
 * Created by Puntar on 2/19/15.
 */
public class MeasurementActivity extends BaseActivity implements MeasurementView,
        AddMeasurementFragment.OnAddMeasurementFragmentListener {

    @Inject
    MeasurementPresenter presenter;

    @InjectView(R.id.tb_header) Toolbar toolbar;
    @InjectView(R.id.rv_current_measurements)
    RecyclerView rvCurrentMeasurements;

    @InjectView(R.id.et_order_title)
    EditText etOrderTitle;
    @InjectView(R.id.et_order_description)
    EditText etOrderTitleDescription;

    private AddMeasurementFragment addMeasurementFragment;
    private CurrentMeasurementAdapter adapter;

    @Override
    protected void inject() {
        MeasurementComponent measurementComponent = Dagger_MeasurementComponent.builder()
                .appComponent(App.get(this).getAppComponent())
                .measurementModule(new MeasurementModule(this)).build();
        measurementComponent.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_manipulate_measurement;
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(R.string.add_measurement_activity_title);

        addMeasurementFragment = (AddMeasurementFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_add_measurement);

        addMeasurementFragment.setListener(this);

        rvCurrentMeasurements.setLayoutManager(new LinearLayoutManager(this));
        rvCurrentMeasurements.addItemDecoration(new DividerItemDecoration(this));
        rvCurrentMeasurements.setItemAnimator(new DefaultItemAnimator());

        adapter = new CurrentMeasurementAdapter(this);
        rvCurrentMeasurements.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_meas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_save_measurement:
                presenter.saveMeasurement(etOrderTitle.getText().toString(),
                        etOrderTitleDescription.getText().toString(),
                        adapter.getItems());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void saveMeasurement(Log log) {
        adapter.addItem(log);
        rvCurrentMeasurements.smoothScrollToPosition(0);
    }

    @Override
    public void successfullySaved() {
        finish();
    }

    @Override
    public void showMultipleAlerts(List<Integer> alerts) {
        StringBuilder sb = new StringBuilder();

        for (Integer stringId : alerts) {
            sb.append(getString(stringId)+"\n");
        }
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(@StringRes int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }
}

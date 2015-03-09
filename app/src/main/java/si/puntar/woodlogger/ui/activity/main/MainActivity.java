package si.puntar.woodlogger.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.ui.activity.addMeasurement.MeasurementActivity;
import si.puntar.woodlogger.ui.activity.base.BaseActivity;


public class MainActivity extends BaseActivity implements MainView {

    @Inject MainPresenter presenter;

    @InjectView(R.id.tb_header) Toolbar toolbar;

    @InjectView(R.id.rv_previous_measurements)
    RecyclerView rvPreviousMeasurement;

    private OrderAdapter adapter;

    @Override
    protected void inject() {
        MainComponent mainComponent = Dagger_MainComponent.builder()
                .appComponent(App.get(this)
                        .getAppComponent())
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

        adapter = new OrderAdapter(this);
        rvPreviousMeasurement.setLayoutManager(new LinearLayoutManager(this));
        rvPreviousMeasurement.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();

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

    @Override
    public void addOrders(List<Order> item) {
        adapter.addItem(item);
    }
}

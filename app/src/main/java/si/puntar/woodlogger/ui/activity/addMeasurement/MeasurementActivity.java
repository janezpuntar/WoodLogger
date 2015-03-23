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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.ui.activity.addMeasurement.swipeRemove.OnLogClickListener;
import si.puntar.woodlogger.ui.activity.addMeasurement.swipeRemove.RecyclerLogClickListener;
import si.puntar.woodlogger.ui.activity.base.BaseActivity;
import si.puntar.woodlogger.ui.fragment.addMeasurement.AddMeasurementFragment;
import si.puntar.woodlogger.ui.widget.DividerItemDecoration;
import si.puntar.woodlogger.ui.widget.SwipeDismissRecyclerViewTouchListener;

/**
 * Created by Puntar on 2/19/15.
 */
public class MeasurementActivity extends BaseActivity implements MeasurementView,
        AddMeasurementFragment.OnAddMeasurementFragmentListener {

    public static final String  ORDER_ID = "order_id";

    @Inject MeasurementPresenter presenter;

    @InjectView(R.id.tb_header) Toolbar toolbar;
    @InjectView(R.id.rv_current_measurements) RecyclerView rvCurrentMeasurements;
    @InjectView(R.id.et_order_title) EditText etOrderTitle;
    @InjectView(R.id.et_order_description) EditText etOrderTitleDescription;

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

        presenter.onCreate(getIntent().getExtras());

        addMeasurementFragment = (AddMeasurementFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_add_measurement);

        addMeasurementFragment.setListener(this);

        rvCurrentMeasurements.setLayoutManager(new LinearLayoutManager(this));
        rvCurrentMeasurements.addItemDecoration(new DividerItemDecoration(this));
        rvCurrentMeasurements.setItemAnimator(new DefaultItemAnimator());

        adapter = new CurrentMeasurementAdapter(this);
        rvCurrentMeasurements.setAdapter(adapter);

        SwipeDismissRecyclerViewTouchListener touchListener =
            new SwipeDismissRecyclerViewTouchListener(
                rvCurrentMeasurements,
                new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return true;
                    }

                    @Override
                    public void onDismiss(RecyclerView recyclerView, final int[] reverseSortedPositions) {

                        new MaterialDialog.Builder(MeasurementActivity.this)
                                .title(R.string.remove_questionmark)
                                .content(R.string.log_will_be_removed)
                                .positiveText(R.string.yes)
                                .negativeText(R.string.no)
                                .callback(new MaterialDialog.ButtonCallback() {
                                    @Override
                                    public void onPositive(MaterialDialog dialog) {
                                        for (int position : reverseSortedPositions) {
                                            presenter.removeLog(adapter.getItemId(position));
                                            adapter.removeItem(position);
                                        }
                                    }

                                    @Override
                                    public void onNegative(MaterialDialog dialog) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                });
        rvCurrentMeasurements.setOnTouchListener(touchListener);
        rvCurrentMeasurements.setOnScrollListener(touchListener.makeScrollListener());
//        rvCurrentMeasurements.addOnItemTouchListener(new RecyclerLogClickListener(this,
//                new OnLogClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        Toast.makeText(MeasurementActivity.this, "Clicked ", Toast.LENGTH_SHORT).show();
//                    }
//                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
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
    public void onBackPressed() {

        new MaterialDialog.Builder(MeasurementActivity.this)
                .title(R.string.save_questionmark)
                .content(R.string.save_before_exit)
                .positiveText(R.string.yes)
                .negativeText(R.string.no)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        presenter.saveMeasurement(etOrderTitle.getText().toString(),
                                etOrderTitleDescription.getText().toString(),
                                adapter.getItems());
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        MeasurementActivity.this.finish();
                    }
                }).show();
    }

    @Override
    public void saveMeasurement(Log log) {
        adapter.addItem(log);
        presenter.displayTotal(adapter.getTotalVolume());
        rvCurrentMeasurements.smoothScrollToPosition(0);
    }

    @Override
    public void setTitle(int title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setTitle(int title, double totalVolume) {
        getSupportActionBar().setTitle(getString(title, totalVolume));
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
    public void setOrder(Order order) {
        etOrderTitle.setText(order.getTitle());
        etOrderTitleDescription.setText(order.getDetails());
        adapter.addItems(order.getMeasuredLogs());
    }

    @Override
    public void logRemoved() {
        Toast.makeText(this, R.string.log_removed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAlert(@StringRes int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }
}

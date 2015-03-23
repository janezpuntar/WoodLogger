package si.puntar.woodlogger.ui.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.data.model.Order;
import si.puntar.woodlogger.ui.activity.addMeasurement.MeasurementActivity;
import si.puntar.woodlogger.ui.activity.base.BaseActivity;
import si.puntar.woodlogger.ui.activity.main.swipeDismiss.OnOrderClickListener;
import si.puntar.woodlogger.ui.activity.main.swipeDismiss.RecyclerOrderClickListener;
import si.puntar.woodlogger.ui.widget.CustomRecyclerView;
import si.puntar.woodlogger.ui.widget.SwipeDismissRecyclerViewTouchListener;


public class MainActivity extends BaseActivity implements MainView {

    @Inject MainPresenter presenter;

    @InjectView(R.id.tb_header) Toolbar toolbar;
    @InjectView(R.id.tv_empty_order_list)
    TextView tvEmptyOrderList;

    @InjectView(R.id.rv_previous_measurements)
    CustomRecyclerView rvPreviousMeasurement;

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
        rvPreviousMeasurement.setItemAnimator(new DefaultItemAnimator());

        SwipeDismissRecyclerViewTouchListener touchListener =
                new SwipeDismissRecyclerViewTouchListener(
                        rvPreviousMeasurement,
                        new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(RecyclerView recyclerView, final int[] reverseSortedPositions) {

                                new MaterialDialog.Builder(MainActivity.this)
                                        .title(R.string.remove_questionmark)
                                        .content(R.string.order_will_be_removed)
                                        .positiveText(R.string.yes)
                                        .negativeText(R.string.no)
                                        .callback(new MaterialDialog.ButtonCallback() {
                                            @Override
                                            public void onPositive(MaterialDialog dialog) {
                                                for (int position : reverseSortedPositions) {
                                                    presenter.removeOrder(adapter.getItemId(position));
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
        rvPreviousMeasurement.setOnTouchListener(touchListener);

        rvPreviousMeasurement.setOnScrollListener(touchListener.makeScrollListener());
        rvPreviousMeasurement.addOnItemTouchListener(new RecyclerOrderClickListener(this,
                new OnOrderClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent editOrder = new Intent(MainActivity.this, MeasurementActivity.class);
                        editOrder.putExtra(MeasurementActivity.ORDER_ID, adapter.getItemId(position));
                        startActivity(editOrder);
                    }
                }));
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

    @Override
    public void orderRemoved() {
        Toast.makeText(this, R.string.order_removed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEmptyOrderListHint(boolean visibility) {
        if (visibility) {
            tvEmptyOrderList.setVisibility(View.VISIBLE);
        } else {
            tvEmptyOrderList.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void onOrderItemClick(long orderId) {
//        Intent editOrder = new Intent(this, MeasurementActivity.class);
//        editOrder.putExtra(MeasurementActivity.ORDER_ID, orderId);
//        startActivity(editOrder);
//    }
}

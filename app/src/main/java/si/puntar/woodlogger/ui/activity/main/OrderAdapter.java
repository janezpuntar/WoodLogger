package si.puntar.woodlogger.ui.activity.main;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.model.Order;

/**
 * Created by Puntar on 3/6/15.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Order> lstOrder;
//    private OnOrderClickListener onOrderClickListener;

    public OrderAdapter(Context context) {
        this.context = context;
        lstOrder = new ArrayList<>(3);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.order_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(lstOrder.get(position));
    }

    @Override
    public int getItemCount() {
        return lstOrder.size();
    }

    public void addItem(List<Order> orders) {
        lstOrder.clear();
        lstOrder.addAll(orders);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        lstOrder.remove(position);
        notifyDataSetChanged();
    }



    @Override
    public long getItemId(int position) {
        return lstOrder.get(position).getOrderId();
    }

    //    public void setOnOrderClickListener(OnOrderClickListener onOrderClickListener) {
//        this.onOrderClickListener = onOrderClickListener;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.cv_order_row) CardView orderRow;
        @InjectView(R.id.tv_order_title) TextView tvTitle;
        @InjectView(R.id.tv_order_description) TextView tvDescription;
        @InjectView(R.id.tv_order_total_volume) TextView tvTotalVolume;
        @InjectView(R.id.tv_order_date_time) TextView tvMeasureDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }

        public void bind(final Order order) {
            tvTitle.setText(order.getTitle());
            tvDescription.setText(order.getDetails());
            tvTotalVolume.setText(context.getString(R.string.unit_volume, order.getTotalVolume()));
            tvMeasureDate.setText(SimpleDateFormat
                    .getDateTimeInstance()
                    .format(order.getDateMeasured()));

//            orderRow.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onOrderClickListener.onOrderItemClick(order.getOrderId());
//                }
//            });
        }
    }

//    public static interface OnOrderClickListener {
//        public void onOrderItemClick(long orderId);
//    }
}

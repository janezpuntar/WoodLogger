package si.puntar.woodlogger.ui.activity.addMeasurement;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.data.model.Log;

/**
 * Created by Puntar on 2/19/15.
 */
public class CurrentMeasurementAdapter extends RecyclerView.Adapter<CurrentMeasurementAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Log> lstLog;

    public CurrentMeasurementAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        lstLog = new ArrayList<>(3);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.current_meas_row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log log = lstLog.get(position);

        holder.tvLength.setText(context.getString(R.string.unit, log.getLength()));
        holder.tvDiameter.setText(context.getString(R.string.unit_diameter, log.getDiameter()));
        holder.tvVolume.setText(context.getString(R.string.unit_volume, log.getVolume()));
    }

    @Override
    public int getItemCount() {
        return lstLog.size();
    }

    public void addItem(Log log) {
        lstLog.add(0, log);
        notifyItemInserted(0);
    }

    public List<Log> getItems() {
        return lstLog;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_diameter)
        TextView tvDiameter;
        @InjectView(R.id.tv_length)
        TextView tvLength;
        @InjectView(R.id.tv_volume)
        TextView tvVolume;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}

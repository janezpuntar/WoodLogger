package si.puntar.woodlogger.ui.dialog.logLenght;

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
import si.puntar.woodlogger.data.model.LogLength;

/**
 * Created by Puntar on 2/23/15.
 */
public class StoredLengthAdapter extends RecyclerView.Adapter<StoredLengthAdapter.ViewHolder> {

    private List<LogLength> logLengths;
    private LayoutInflater inflater;

    public StoredLengthAdapter(Context context) {
        this.logLengths = new ArrayList<>();
        inflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_log_length, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LogLength logLength = logLengths.get(position);
        holder.tvLogLength.setText(String.valueOf(logLength.getLength()));
    }

    @Override
    public int getItemCount() {
        return logLengths.size();
    }

    public void addAll(List<LogLength> data) {
        logLengths.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.tv_log_length)
        TextView tvLogLength;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}

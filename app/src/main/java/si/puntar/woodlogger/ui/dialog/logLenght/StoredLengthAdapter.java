package si.puntar.woodlogger.ui.dialog.logLenght;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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

    private Context context;
    private List<LogLength> logLengths;
    private LayoutInflater inflater;
    private OnItemClickListener itemClickListener;

    public StoredLengthAdapter(Context context) {
        this.context = context;
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
        holder.bind(logLengths.get(position));
    }

    @Override
    public int getItemCount() {
        return logLengths.size();
    }

    public void addAll(List<LogLength> data) {
        logLengths.addAll(data);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LogLength logLength;

        @InjectView(R.id.tv_log_length)
        TextView tvLogLength;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);

            itemView.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                    itemClickListener.onItemClick(logLength);
                }
            });
        }

        public void bind(LogLength logLength) {
            this.logLength = logLength;
            tvLogLength.setText(context.getString(R.string.unit, logLength.getLength()));
        }
    }

    public static interface OnItemClickListener {
        public void onItemClick(LogLength entity);
    }
}

package si.puntar.woodlogger.ui.dialog.logLenght;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.data.model.LogLength;
import si.puntar.woodlogger.ui.ViewFunctions;
import si.puntar.woodlogger.ui.dialog.baseDialog.BaseDialog;
import si.puntar.woodlogger.ui.dialog.logLenght.StoredLengthAdapter.OnItemClickListener;
import si.puntar.woodlogger.ui.fragment.addMeasurement.AddMeasurementFragment;

/**
 * Created by Puntar on 2/23/15.
 */
public class LogLengthDialog extends BaseDialog
        implements LogLengthDialogView, OnItemClickListener {

    public static final String TAG = LogLengthDialog.class.getSimpleName();

    public static LogLengthDialog newInstance() {
        LogLengthDialog dialog = new LogLengthDialog();
        return dialog;
    }

    @Inject
    LogLengthDialogPresenter presenter;

    @InjectView(R.id.rv_stored_lengths)
    RecyclerView rvStoredLengths;

    private StoredLengthAdapter adapter;
    private OnItemClickListener logLengthItemClick;

    @Override
    protected void inject() {
        LogLengthDialogComponent lldc = Dagger_LogLengthDialogComponent.builder()
                .appComponent(App.get(getActivity()).getAppComponent())
                .logLengthDialogModule(new LogLengthDialogModule(this)).build();

        lldc.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_log_lengths;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvStoredLengths.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StoredLengthAdapter(getActivity());
        rvStoredLengths.setAdapter(adapter);
        adapter.setItemClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    public void setLogLengthItemClick(OnItemClickListener logLengthItemClick) {
        this.logLengthItemClick = logLengthItemClick;
    }

    @Override
    public void setData(List<LogLength> data) {
        adapter.addAll(data);
    }

    @Override
    public void showAlert(@StringRes int stringId) {
        Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT).show();
    }

    @Override public void onItemClick(LogLength entity) {
        logLengthItemClick.onItemClick(entity);
        dismiss();
    }
}

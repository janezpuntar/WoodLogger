package si.puntar.woodlogger.ui.fragment.addMeasurement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.InjectView;
import si.puntar.woodlogger.R;
import si.puntar.woodlogger.app.App;
import si.puntar.woodlogger.data.model.Log;
import si.puntar.woodlogger.data.model.LogLength;
//import si.puntar.woodlogger.ui.dialog.logLenght.LogLengthDialog;
import si.puntar.woodlogger.ui.dialog.logLenght.LogLengthDialog;
import si.puntar.woodlogger.ui.dialog.logLenght.StoredLengthAdapter.OnItemClickListener;
import si.puntar.woodlogger.ui.fragment.baseFragment.BaseFragment;

/**
 * Created by Puntar on 2/7/15.
 */
public class AddMeasurementFragment extends BaseFragment implements
        AddMeasurementView,
        EditText.OnEditorActionListener,
        View.OnClickListener, DialogChooseOptionCallback, OnItemClickListener {

    private static final int LOG_LENGTH_DIALOG_RQ = 2333;

    @Inject
    AddMeasurementPresenter presenter;

    @Inject
    InputMethodManager inputMethodManager;

    @InjectView(R.id.btn_change_length)
    Button btnChangeLength;
    @InjectView(R.id.et_log_diameter)
    EditText etLogDiameter;
    @InjectView(R.id.btn_save_data)
    Button btnSaveData;

    private OnAddMeasurementFragmentListener listener;

    @Override
    protected void inject() {
        AddMeasurementComponent addMeasComponent = Dagger_AddMeasurementComponent.builder()
                .appComponent(App.get(getActivity()).getAppComponent())
                .addMeasurementModule(new AddMeasurementModule(this)).build();

        addMeasComponent.inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_add_measurement;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnChangeLength.setOnClickListener(this);
        etLogDiameter.setOnEditorActionListener(this);
        btnSaveData.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showKeyBoard();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        showKeyBoard();
    }

    @Override
    public void onPause() {
        super.onPause();
        hideKeyBoard();
    }

    private void showKeyBoard() {
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void hideKeyBoard() {
        inputMethodManager.hideSoftInputFromWindow(etLogDiameter.getWindowToken(), 0);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        switch (v.getId()) {
            case R.id.et_log_diameter:
                presenter.verifyData(etLogDiameter.getText().toString());
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_length:
                hideKeyBoard();

                LogLengthDialog dialog = LogLengthDialog.newInstance();
                dialog.setTargetFragment(this, LOG_LENGTH_DIALOG_RQ);
                dialog.setLogLengthItemClick(this);
                dialog.show(getFragmentManager(), LogLengthDialog.TAG);

                break;
            case R.id.btn_save_data:
                presenter.verifyData(etLogDiameter.getText().toString());
                break;
        }
    }

    public void setListener(OnAddMeasurementFragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void publishLog(Log log) {
        listener.saveMeasurement(log);
        etLogDiameter.setText("");
    }

    @Override public void changeSelectedLogLength(LogLength data) {
        btnChangeLength.setText(getString(R.string.unit, data.getLength()));
        presenter.setCurrentLogLength(data);
    }

    @Override
    public void selectedOption(LogLength logLength) {
        presenter.setCurrentLogLength(logLength);
    }

    @Override public void onItemClick(LogLength entity) {

        changeSelectedLogLength(entity);
        showKeyBoard();
    }

    @Override
    public void showAlert(@StringRes int stringId) {
        Toast.makeText(getActivity(), stringId, Toast.LENGTH_SHORT).show();
    }

    public interface OnAddMeasurementFragmentListener {
        void saveMeasurement(Log log);
    }

}

package si.puntar.woodlogger.ui.activity.main;

/**
 * Created by Puntar on 2/17/15.
 */
public interface MainPresenter {
    void onResume();

    void onPause();

    void removeOrder(long orderId);
}

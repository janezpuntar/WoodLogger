package si.puntar.woodlogger.data;

import rx.Observer;

/**
 * Created by Puntar on 2/23/15.
 */
public abstract class EndObserver<T> implements Observer<T> {

    public abstract void onEnd();

    @Override
    public void onCompleted() {
        onEnd();
    }

    @Override
    public void onError(Throwable e) {
        onEnd();
    }

    @Override
    public void onNext(T t) {

    }
}

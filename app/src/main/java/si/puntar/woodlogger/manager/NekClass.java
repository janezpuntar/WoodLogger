package si.puntar.woodlogger.manager;

import javax.inject.Singleton;

/**
 * Created by Puntar on 3/1/15.
 */
@Singleton
public class NekClass {

    private int nekoStevilo;

    public NekClass() {
        this.nekoStevilo = 3434;
    }

    public int getNekoStevilo() {
        return nekoStevilo;
    }

    public void setNekoStevilo(int nekoStevilo) {
        this.nekoStevilo = nekoStevilo;
    }
}

package si.puntar.woodlogger.app;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Puntar on 2/26/15.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkScope {
}

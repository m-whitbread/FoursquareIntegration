package uk.co.markomeara.whitbread;

import android.support.multidex.MultiDexApplication;
import uk.co.markomeara.whitbread.di.Injector;

public class Application extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Injector.init();
    }
}
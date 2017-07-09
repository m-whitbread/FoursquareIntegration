package uk.co.markomeara.whitbread.di;

import android.support.annotation.VisibleForTesting;

public class Injector {
    private static AppComponent component;

    private Injector() {
        throw new AssertionError();
    }

    public static void init() {
        if (component == null) {
            component = DaggerAppComponent.builder().appModule(new AppModule()).build();
        }
    }

    public static AppComponent get() {
        return component;
    }

    @VisibleForTesting
    public static void setComponent(AppComponent appComponent) {
        component = appComponent;
    }
}
package com.randomappsinc.pokemonlocations_pokemongo.Utils;

import android.app.Application;
import android.content.Context;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.IoniconsModule;

/**
 * Created by alexanderchiou on 7/14/16.
 */
public final class MyApplication extends Application {
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new IoniconsModule());
        instance = getApplicationContext();
    }

    public static Context getAppContext() {
        return instance;
    }
}

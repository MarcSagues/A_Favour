package cat.udl.tidic.a_favour;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import cat.udl.tidic.a_favour.preferences.PreferencesProvider;



public class App extends Application
{

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesProvider.init(this);
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }
}

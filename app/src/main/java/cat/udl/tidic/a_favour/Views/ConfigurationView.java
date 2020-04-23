package cat.udl.tidic.a_favour.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import cat.udl.tidic.a_favour.LenguageManager;
import cat.udl.tidic.a_favour.LenguagesArrayAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class ConfigurationView extends AppCompatActivity
{
    static String SHAREDNOT = "shNOT";
    static String SHAREDMESS = "shMES";

    Switch enableMessNotifications;
    Switch enableAppNotifications;
    ImageView back_arrow;
    Spinner lengauges;
    SharedPreferences shared;
    LenguagesArrayAdapter dAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        shared = PreferencesProvider.providePreferences();
        getAlldata();
        setOnclick();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setValues();
    }

    void getAlldata()
    {
        enableAppNotifications = findViewById(R.id.app_not);
        enableMessNotifications = findViewById(R.id.message_not);
        back_arrow = findViewById(R.id.back_arrow);
        lengauges = findViewById(R.id.lenguages);
        List<String> lenguages_Array = Arrays.asList(getResources().getStringArray(R.array.lenguage_options));
        dAdapter = new LenguagesArrayAdapter(this,this, R.layout.lengauges, R.id.lenguage, lenguages_Array);
        lengauges.setAdapter(dAdapter);
    }

    void setValues()
    {
        enableAppNotifications.setChecked(shared.getBoolean(SHAREDNOT,true));
        enableMessNotifications.setChecked(shared.getBoolean(SHAREDMESS,true));
    }

    void setOnclick()
    {
        back_arrow.setOnClickListener(v -> onBackPressed());

        enableAppNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                shared.edit().putBoolean(SHAREDNOT, isChecked).apply());

        enableMessNotifications.setOnCheckedChangeListener((buttonView, isChecked) ->
                shared.edit().putBoolean(SHAREDMESS, isChecked).apply());
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, MainPage.class);
        startActivityForResult(i, 1);
        finish();
    }

    /*
    public static boolean getAppNotifications()
    {
        SharedPreferences shared = PreferencesProvider.providePreferences();
        return shared.getBoolean(SHAREDNOT,true);
    }

    public static boolean getMessageNotifications()
    {
        SharedPreferences shared = PreferencesProvider.providePreferences();
        return shared.getBoolean(SHAREDMESS,true);
    }
    */

    @SuppressWarnings("deprecation")
    public void changeLengauge(int pos)
    {

        SharedPreferences shared = PreferencesProvider.providePreferences();
        String lang = LenguageManager.lang[pos];
        shared.edit().putString("lenguage", LenguageManager.lang[pos]).apply();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Resources res = getBaseContext().getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
        Intent i = getIntent();
        finish();
        startActivity(i);
    }

    public String getLengugeName(String leng)
    {
        if (leng.equals("en"))
        {
            return getResources().getString(R.string.english);
        }
        else if (leng.equals("es"))
        {
            return getResources().getString(R.string.spanish);
        }
        else
        {
            return "";
        }
    }


}
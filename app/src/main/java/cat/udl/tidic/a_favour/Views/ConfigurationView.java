package cat.udl.tidic.a_favour.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cat.udl.tidic.a_favour.App;
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

        lengauges.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ConfigurationView.super.onBackPressed();
            }
        });

        enableAppNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                shared.edit().putBoolean(SHAREDNOT, isChecked).apply();
            }
        });

        enableMessNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                shared.edit().putBoolean(SHAREDMESS, isChecked).apply();
            }
        });
    }

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

    public void changeLengauge(int pos)
    {

        SharedPreferences shared = PreferencesProvider.providePreferences();
        String lang = LenguageManager.lang[pos];
        shared.edit().putString("lenguage", LenguageManager.lang[pos]).apply();

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //this.recreate();
    }


}
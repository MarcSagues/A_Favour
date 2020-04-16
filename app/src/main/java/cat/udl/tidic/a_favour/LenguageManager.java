package cat.udl.tidic.a_favour;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class LenguageManager
{

    public static String[] lang = {"en","es"};

    public static int getLenguageImage(int position)
    {

        if (position == 0)
        {
            Context c = App.getAppContext();
            return c.getResources().getIdentifier("english","drawable",c.getPackageName());
        }
        else if (position == 1)
        {
            Context c = App.getAppContext();
            return c.getResources().getIdentifier("spanish","drawable",c.getPackageName());
        }
        else
        {
            return 0;
        }
    }

    public static String getLengugeName(String leng)
    {
        if (leng.equals("en"))
        {
            Context c = App.getAppContext();
            return c.getResources().getString(R.string.english);
        }
        else if (leng.equals("es"))
        {
            Context c = App.getAppContext();
            return c.getResources().getString(R.string.spanish);
        }
        else
        {
            return "";
        }
    }

    public static int getLenguageImage(String leng)
    {

        if (leng.equals("en"))
        {
            Context c = App.getAppContext();
            return c.getResources().getIdentifier("english","drawable",c.getPackageName());
        }
        else if (leng.equals("es"))
        {
            Context c = App.getAppContext();
            return c.getResources().getIdentifier("spanish","drawable",c.getPackageName());
        }
        else
        {
            return 0;
        }
    }
}

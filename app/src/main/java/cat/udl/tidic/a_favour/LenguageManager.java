package cat.udl.tidic.a_favour;

import android.content.Context;

public class LenguageManager
{

    public static String[] lang = {"en","es"};

    static int getLenguageImage(int position)
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

    static int getLenguageImage(String leng)
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

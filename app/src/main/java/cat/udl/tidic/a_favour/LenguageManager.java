package cat.udl.tidic.a_favour;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LenguageManager
{
    public static String[] array_idiomes = {"English","Spanish"};

    public static int getLenguageImage(int position)
    {
        Context c = App.getAppContext();
        return c.getResources().getIdentifier(array_idiomes[position].toLowerCase(),"drawable",c.getPackageName());
    }

    public static int getLenguageImagebyString(String name)
    {
        Context c = App.getAppContext();
        int image = 0;

        for(int i =0; i< array_idiomes.length; i++)
        {
            if (array_idiomes[i].equals(name))
            {
                 image = c.getResources().getIdentifier(array_idiomes[i].toLowerCase(),"drawable",c.getPackageName());
            }
        }
        return image;
    }
}

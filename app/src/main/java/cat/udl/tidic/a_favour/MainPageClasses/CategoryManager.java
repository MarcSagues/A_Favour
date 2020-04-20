package cat.udl.tidic.a_favour.MainPageClasses;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import cat.udl.tidic.a_favour.App;

@SuppressLint("Registered")
public class  CategoryManager extends Application
{
    //public static int TOTAL_CATEGORIES = CATEGORIES.values().length;

    //IMPORTANT
    //Aquest enum defineix totes les categories
    public enum CATEGORIES {favourxfavour, daytodaythings, computing, reparation, others}

    public static int getImageId(String catName)
    {
        //La imatge de la categoria i el seu enum s'han de dir IGUAL (sense majúscules)
        Context c = App.getAppContext();
        return c.getResources().getIdentifier(catName,"drawable",c.getPackageName());
    }

}

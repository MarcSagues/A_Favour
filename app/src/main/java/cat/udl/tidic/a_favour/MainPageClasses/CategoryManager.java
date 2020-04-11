package cat.udl.tidic.a_favour.MainPageClasses;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.R;

public class  CategoryManager extends Application
{
    public static int TOTAL_CATEGORIES = CATEGORIES.values().length;

    public static enum CATEGORIES {favourxfavour, daytodaythings, computing, reparation, others}

    public static int getImageId(String catName)
    {
        Context c = App.getAppContext();
        return c.getResources().getIdentifier(catName,"drawable",c.getPackageName());
    }

}

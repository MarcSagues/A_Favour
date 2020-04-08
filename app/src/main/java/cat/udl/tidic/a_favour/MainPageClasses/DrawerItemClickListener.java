package cat.udl.tidic.a_favour.MainPageClasses;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cat.udl.tidic.a_favour.Views.MainPage;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private MainPage p;
    public  DrawerItemClickListener(MainPage p)
    {
        this.p = p;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (position == 0)
        {
            Log.d("Open profile", "a");
            p.goToProfile();
        }
        else if (position == 1)
        {
            Log.d("Log out", "a");
        }
    }
}
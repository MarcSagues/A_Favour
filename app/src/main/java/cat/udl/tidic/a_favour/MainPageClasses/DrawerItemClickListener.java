package cat.udl.tidic.a_favour.MainPageClasses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.core.app.ActivityCompat;

import cat.udl.tidic.a_favour.ProfileClasses.BlankFragment;
import cat.udl.tidic.a_favour.Views.MainPage;
import cat.udl.tidic.a_favour.Views.UploadFavour;

import static androidx.core.content.ContextCompat.startActivity;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private MainPage p;
    public  DrawerItemClickListener(MainPage p)
    {
        this.p = p;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        DataModel d = (DataModel) parent.getItemAtPosition(position);
        Log.d(d.name, "thename");

        if (position == 0)
        {
            Log.d("Open profile", "a");
            if(p != null) {p.goToProfile();}
        }
        else if (position == 1)
        {
            Log.d("Log out", "a");
            p.goToEditPage(d);
        }
    }


}
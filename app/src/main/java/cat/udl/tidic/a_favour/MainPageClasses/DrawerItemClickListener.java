package cat.udl.tidic.a_favour.MainPageClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.core.app.ActivityCompat;

import cat.udl.tidic.a_favour.ProfileClasses.BlankFragment;
import cat.udl.tidic.a_favour.Views.MainPage;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.Views.UploadFavour;

import static androidx.core.content.ContextCompat.startActivity;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    Context c;
    public  DrawerItemClickListener(Context c)
    {
        this.c = c;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        DataModel d = (DataModel) parent.getItemAtPosition(position);
        if (d instanceof DataModel.MenuList)
        {
            if (position == 0) {
                Log.d("Open profile", "a");
                {
                    goToProfile();
                }
            } else if (position == 1) {
                Log.d("Log out", "a");
            }
        }
        else if (d instanceof DataModel.Favour)
        {
            goToEditFavour();
        }
    }

    private void goToProfile()
    {
        Intent intent = new Intent (c, ProfileView.class);
        startActivity(c,intent,Bundle.EMPTY);
    }

    private void goToEditFavour()
    {
        Intent intent = new Intent (c, UploadFavour.class);
        Bundle b= new Bundle();
        b.putBoolean("upload", false);
        startActivity(c,intent,b);
    }

}
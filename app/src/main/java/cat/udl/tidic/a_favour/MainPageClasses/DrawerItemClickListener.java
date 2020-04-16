package cat.udl.tidic.a_favour.MainPageClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import cat.udl.tidic.a_favour.ProfileClasses.BlankFragment;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.AnunciView;
import cat.udl.tidic.a_favour.Views.ConfigurationView;
import cat.udl.tidic.a_favour.Views.HelpView;
import cat.udl.tidic.a_favour.Views.LoginView;
import cat.udl.tidic.a_favour.Views.MainPage;
import cat.udl.tidic.a_favour.Views.MessagesView;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.Views.UploadFavour;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

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
            switch (position)
            {
                case 0:
                    Log.d("Open profile", "clicked");
                    goTo(ProfileView.class);
                    break;
                case 1:
                    Log.d("Messages", "clicked");
                    goTo(MessagesView.class);
                    break;
                case 2:
                    Log.d("Configuration", "clicked");
                    goTo(ConfigurationView.class);
                    break;
                case 3:
                    Log.d("Help", "clicked");
                    goTo(HelpView.class);
                    break;
                case 4:
                    Log.d("Log out", "clicked");
                    ShowDialog();
                    break;
            }

        }
        else if (d instanceof DataModel.Favour)
        {
            goToSeeAnunci();
        }
    }

    private void goTo(Class classe)
    {
        Intent intent = new Intent (c, classe);
        startActivity(c,intent,Bundle.EMPTY);
    }
    private void goToConfiguration()
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

    private void goToSeeAnunci()
    {

        boolean isMyfavour = c.getClass().equals(ProfileView.class);
        Log.d(String.valueOf(isMyfavour), " Is my favour");
        Intent intent = new Intent (c, AnunciView.class);
        Bundle b= new Bundle();
        b.putBoolean("myfavour", isMyfavour);
        intent.putExtras(b);
        startActivity(c,intent,b);
    }

    private void ShowDialog()
    {
        //Si falla la connexió s'haura de posar un layout de "error"
        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        builder.setMessage(R.string.dialogMessage).setTitle(R.string.dialogTitle);

        builder.setPositiveButton(R.string.retry, (dialog, id) ->
        {

        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) ->
        {
            dialog.cancel();

        });

        builder.show();
    }

}
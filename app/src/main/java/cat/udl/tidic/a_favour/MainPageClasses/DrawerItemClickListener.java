package cat.udl.tidic.a_favour.MainPageClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import java.io.Serializable;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.AnunciView;

import cat.udl.tidic.a_favour.Views.ChatView;
import cat.udl.tidic.a_favour.Views.ConfigurationView;
import cat.udl.tidic.a_favour.Views.HelpView;
import cat.udl.tidic.a_favour.Views.MessagesView;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.models.MainClassViewModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;


import static androidx.core.content.ContextCompat.startActivity;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private Context c;
    private MainClassViewModel mc;
    public  DrawerItemClickListener(Context c)
    {
        this.c = c;
    }
    public  DrawerItemClickListener(Context c, MainClassViewModel mc)
    {
        this.c = c;
        this.mc = mc;
    }
    //Aquesta classe afegeix un "OnClcickListener" a cada item de la llista.
    //Exemple : Tinc la llista recyclerView
    //Per a fer que els items de la llista es puguin clickar ...
    //... haig de fer : recyclerView.setOnItemClickListener(new DrawerItemClickListener(this));
    //on "this" és el context de la classe

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        DataModel d = (DataModel) parent.getItemAtPosition(position);
        //Si el item que s'ha clickat és de la classe MenuList(el menu de la pàgina principal...)
        if (d instanceof DataModel.MenuList)
        {
            switch (position)
            {
                case 0:
                    Log.d("Open profile", "clicked");
                    goToProfile();
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
        //Si el item que s'ha clickat és un favor...
        else if (d instanceof DataModel.Favour)
        {
            goToSeeAnunci((DataModel.Favour)d);
        }
        else
        {
           goToChat();
        }
    }

    private void goTo(Class classe)
    {
        Intent intent = new Intent (c, classe);
        startActivity(c,intent,Bundle.EMPTY);
    }


    private void goToChat()
    {
        Intent intent = new Intent (c, ChatView.class);
        Bundle b= new Bundle();
        b.putBoolean("myprofile", true);
        startActivity(c,intent,b);
    }
    private void goToProfile()
    {
        Intent intent = new Intent (c, ProfileView.class);
        Bundle b= new Bundle();
        b.putBoolean("myprofile", true);
        startActivity(c,intent,b);
    }

    public void goToSeeAnunci(DataModel.Favour d)
    {
        SharedPreferences shp = PreferencesProvider.providePreferences();
        boolean isMyfavour = d.owner_id == shp.getInt("id",-1);
        Intent intent = new Intent (c, AnunciView.class);
        Bundle b= new Bundle();
        b.putBoolean("myfavour", isMyfavour);
        b.putInt("user_id", d.getOwner_id());
        intent.putExtras(b);
        intent.putExtra("favour", (Serializable) d);
        startActivity(c,intent,b);
        if (isMyfavour)
        {
            ((Activity) c).finish();
        }
    }

    private void ShowDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(R.string.alertLogoutD).setTitle(R.string.logOut);
        builder.setPositiveButton(R.string.no, (dialog, id) -> dialog.cancel());
        builder.setNegativeButton(R.string.yes, (dialog, id) -> mc.logOutAPI());
        builder.show();
    }

}
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

import java.io.Serializable;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.AnunciView;
import cat.udl.tidic.a_favour.Views.ConfigurationView;
import cat.udl.tidic.a_favour.Views.HelpView;
import cat.udl.tidic.a_favour.Views.MessagesView;
import cat.udl.tidic.a_favour.Views.ProfileView;


import static androidx.core.content.ContextCompat.startActivity;

public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private Context c;
    public  DrawerItemClickListener(Context c)
    {
        this.c = c;
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
            goToSeeAnunci(d);
        }
        else
        {
            Log.d("No type", "mafren");
        }
    }

    private void goTo(Class classe)
    {
        Intent intent = new Intent (c, classe);
        startActivity(c,intent,Bundle.EMPTY);
    }

    private void goToProfile()
    {
        Intent intent = new Intent (c, ProfileView.class);
        Bundle b= new Bundle();
        b.putBoolean("myprofile", true);
        startActivity(c,intent,b);
    }

    private void goToSeeAnunci(DataModel d)
    {
        boolean isMyfavour = c.getClass().equals(ProfileView.class);
        Log.d(String.valueOf(isMyfavour), " Is my favour");
        Log.d("!!!!!!!!!!!!!!!!!!", d.toString());
        Intent intent = new Intent (c, AnunciView.class);
        Bundle b= new Bundle();
        b.putBoolean("myfavour", isMyfavour);
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
        //Si falla la connexió s'haura de posar un layout de "error"
        //Aixó simplement mostra un dialog d'error
        AlertDialog.Builder builder = new AlertDialog.Builder(c);

        builder.setMessage(R.string.alertLogoutD).setTitle(R.string.logOut);

        builder.setPositiveButton(R.string.no, (dialog, id) ->
        {

        });
        builder.setNegativeButton(R.string.yes, (dialog, id) -> dialog.cancel());

        builder.show();
    }

}
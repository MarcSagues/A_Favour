package cat.udl.tidic.a_favour.MainPageClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cat.udl.tidic.a_favour.Views.ChatView;
import cat.udl.tidic.a_favour.models.MainClassViewModel;


import static androidx.core.content.ContextCompat.startActivity;

public class MessagesItemClickListener implements ListView.OnItemClickListener {

    private Context c;
    private MainClassViewModel mc;
    public MessagesItemClickListener(Context c)
    {
        this.c = c;
    }
    public MessagesItemClickListener(Context c, MainClassViewModel mc)
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
        //Message d = (Message) parent.getItemAtPosition(position);
        goToChat();
    }

    private void goToChat()
    {
        Intent intent = new Intent (c, ChatView.class);
        Bundle b= new Bundle();
        startActivity(c,intent,b);
    }
}
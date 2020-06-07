package cat.udl.tidic.a_favour.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.AnunciView;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import cat.udl.tidic.a_favour.view.FavoursActivity;
import cat.udl.tidic.a_favour.view.FavoursListActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.content.ContextCompat.startActivity;


public class FavourAdapter extends ListAdapter<Favour, FavourAdapter.FavourHolder> {

    private final static String TAG ="FavourAdapter";
    private Location currentLocation;
    private FavourCommonHolder favourHolder;
    private Class classe;


    public FavourAdapter(@NonNull DiffUtil.ItemCallback<Favour> diffCallback, Class classe) {
        super(diffCallback);
        this.classe = classe;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @NonNull
    @Override
    public FavourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.favours_list,
                null, false);
        return new FavourHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull FavourHolder holder, int position) {
        final Favour currentFavour = (Favour) getItem(position);
        Log.d(TAG, "onBindViewHolder() -> currentFavour: " + currentFavour);
        favourHolder.bindHolder(currentFavour, currentLocation);
        favourHolder.setFavour(currentFavour);
    }


    class FavourHolder extends RecyclerView.ViewHolder
    {
        public FavourHolder(@NonNull View itemView) {
            super(itemView);
            favourHolder = new FavourCommonHolder(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    final Favour currentFavour = (Favour) getItem(getAdapterPosition());
                    //Si vinc del Menu principal, vaig acap a veure el anunci
                    if (classe.equals(FavoursActivity.class))
                    {
                        goToSeeAnunci(currentFavour);
                    }
                }
            });
        }
    }

    public void goToSeeAnunci(Favour d)
    {
        Context c = App.getAppContext();
        SharedPreferences shp = PreferencesProvider.providePreferences();
        boolean isMyfavour = d.getOwner_id() == shp.getInt("id",-1);
        Intent intent = new Intent (c, AnunciView.class);
        Bundle b= new Bundle();
        b.putBoolean("myfavour", isMyfavour);
        b.putInt("user_id", d.getOwner_id());
        intent.putExtras(b);
        intent.putExtra("favour", (Serializable) d);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(c,intent,b);
        if (isMyfavour)
        {
            ((Activity) c).finish();
        }
    }
}

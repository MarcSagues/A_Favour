package cat.udl.tidic.a_favour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Favour;


public class MapCustomInfoWindowsAdapter implements GoogleMap.InfoWindowAdapter {

    private final View customView;
    private List<Favour> favours;
    private FavourCommonHolder favourHolder;


    public MapCustomInfoWindowsAdapter(List<Favour> favours, Context context) {
        this.favours = favours;
        customView = LayoutInflater
                .from(context).inflate(R.layout.favours_list, null);
        favourHolder = new FavourCommonHolder(customView);
    }


    @Override
    public View getInfoContents(final Marker marker) {
        int position = Integer.parseInt(marker.getSnippet());
        favourHolder.bindHolder(favours.get(position), null);
        return customView;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

}

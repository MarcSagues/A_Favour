package cat.udl.tidic.a_favour.adapters;

import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Favour;


public class FavourAdapter extends ListAdapter<Favour, FavourAdapter.FavourHolder> {

    private final static String TAG ="FavourAdapter";
    private Location currentLocation;
    private FavourCommonHolder favourHolder;


    public FavourAdapter(@NonNull DiffUtil.ItemCallback<Favour> diffCallback) {
        super(diffCallback);
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
    }


    class FavourHolder extends RecyclerView.ViewHolder {


        public FavourHolder(@NonNull View itemView) {
            super(itemView);
            favourHolder = new FavourCommonHolder(itemView);
        }
    }
}

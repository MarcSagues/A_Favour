package cat.udl.tidic.a_favour.adapters;

import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;


public class FavourCommonHolder {

    private final String TAG = "FavourCommonHolder";

    private ImageView imageViewIcon;
    private TextView textViewName;
    private TextView textViewfavourTypeColour;
    private TextView amount;
    private TextView distanceTo;
    private Favour favour;


    public FavourCommonHolder(@NonNull View itemView)
    {
          imageViewIcon = itemView.findViewById(R.id.iv_image);
          textViewName = itemView.findViewById(R.id.tv_text);
          textViewfavourTypeColour = itemView.findViewById(R.id.favourTypeColour);
          amount = itemView.findViewById(R.id.favourCategoryName);
          distanceTo = itemView.findViewById(R.id.distanceTo);
    }

    public void setFavour(Favour f)
    {
        this.favour = f;
    }

    public Favour getFavour()
    {
        return this.favour;
    }

    public void bindHolder(Favour f, Location currentLocation) {

        Log.d(TAG, "bindHolder() -> Favour: " + f);

        this.imageViewIcon.setImageResource(CategoryEnum.getIcon(f.getCategory()));
        this.textViewName.setText(f.getName());

        this.textViewfavourTypeColour.setBackground(ContextCompat.getDrawable(
                this.textViewfavourTypeColour.getContext(),
                FavourTypeEnum.getColourResource(f.getType())));

        String amount = f.getAmount() + "€";
        this.amount.setText(amount);

        Log.d(TAG, "onBindViewHolder() -> cLocation: " + currentLocation);
        this.distanceTo.setText(f.getDistanceTo(currentLocation) + " km");

//        Log.d(TAG, "onBindViewHolder() -> cLocation: " + currentLocation);
//        if (currentLocation != null && f.getLatitutde() > 0  && f.getLongitude() > 0 ) {
//            float[] distance = new float[1];
//            Location.distanceBetween(currentLocation.getLatitude(), currentLocation.getLongitude(),
//                    f.getLatitutde(), f.getLongitude(), distance);
//
//            Log.d(TAG, "onBindViewHolder() -> distance: " + distance[0]);
//            this.distanceTo.setText((int) (distance[0] / 1000) + " km");
//        }

    }

}



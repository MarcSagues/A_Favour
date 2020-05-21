package cat.udl.tidic.a_favour.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.adapters.FavourAdapter;
import cat.udl.tidic.a_favour.adapters.FavourDiffCallback;
import cat.udl.tidic.a_favour.adapters.MapCustomInfoWindowsAdapter;
import cat.udl.tidic.a_favour.models.Favour;


public class FavoursMapActivity extends FavoursActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    protected void initView() {
        super.initView(R.layout.favours_activity_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);

        mapFragment.getMapAsync(this);

        favoursViewModel.getResponseFavourData().observe(this, new Observer<List<Favour>>() {
            @Override
            public void onChanged(List<Favour> favours) {
                mMap.clear();
                insertMarkers(favours);
                progressBar.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }



    private void insertMarkers(List<Favour> favours) {
        final LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (int i = 0; i < favours.size(); i++) {
            final LatLng position = new LatLng(favours.get(i).getLatitutde(),
                    favours.get(i).getLongitude());
            final MarkerOptions options = new MarkerOptions().position(position)
                    .snippet(String.valueOf(i));
                   // .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(favours.get(i))));

            mMap.addMarker(options);
            builder.include(position);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
        mMap.setInfoWindowAdapter(new MapCustomInfoWindowsAdapter(favours, getApplicationContext()));
    }


}

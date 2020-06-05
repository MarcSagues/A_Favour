package cat.udl.tidic.a_favour.view;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.UploadFavour;
import cat.udl.tidic.a_favour.adapters.FavourAdapter;
import cat.udl.tidic.a_favour.adapters.FavourCategorySpinnerAdapter;
import cat.udl.tidic.a_favour.adapters.FavourDiffCallback;
import cat.udl.tidic.a_favour.adapters.FavourTypeSpinnerAdapter;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;


public class FavoursListActivity extends FavoursActivity {

    private RecyclerView favoursListView;
    private FavourAdapter favourAdapter;
    private Button uploadfavour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }



    protected void initView() {
        super.initView(R.layout.favours_activity_list);


        favoursListView = findViewById(R.id.rv_recycler_view);
        favoursListView.setLayoutManager(new LinearLayoutManager(this));
        uploadfavour = findViewById(R.id.upload_afavour);
        //favoursListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        favoursViewModel.getResponseFavourData().observe(this, new Observer<List<Favour>>() {
            @Override
            public void onChanged(List<Favour> favours) {
                progressBar.setVisibility(View.GONE);
                //Log.d(TAG, favours.toString());
                favourAdapter = new FavourAdapter(new FavourDiffCallback());
                favourAdapter.setCurrentLocation(currentLocation.getValue());
                favoursListView.setAdapter(favourAdapter);
                favourAdapter.submitList(favours);
            }
        });

        this.currentLocation.observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                Log.d(TAG, "Current location: " + currentLocation);
                if (location != null) {
                    favourAdapter = new FavourAdapter(new FavourDiffCallback());
                    favoursListView.setAdapter(favourAdapter);
                    favourAdapter.setCurrentLocation(location);
                    favourAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void onResume()
    {
        super.onResume();
        initView();
    }

}

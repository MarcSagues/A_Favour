package cat.udl.tidic.a_favour.view;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.ConfigurationView;
import cat.udl.tidic.a_favour.Views.HelpView;
import cat.udl.tidic.a_favour.Views.MessagesView;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.adapters.FavourTypeSpinnerAdapter;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;
import cat.udl.tidic.a_favour.viewmodels.FavoursViewModel;


public class FavoursActivity extends LocationActivity {

    final protected String TAG = "FavoursActivity";
    protected FavoursViewModel favoursViewModel;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FavourTypeSpinnerAdapter favourTypeSpinnerAdapter;
    private Spinner typeFilterSpinner;
    protected LinearLayout progressBar;

    private CategoryEnum selectedCategory = null;
    private FavourTypeEnum selectedType = null;
    private Activity currentActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        favoursViewModel = new FavoursViewModel();
        currentActivity = this;

    }

    protected void initView(int layout_resource) {
        setContentView(layout_resource);
        progressBar = findViewById(R.id.linearLayoutProgressBar);
        createSpinnerType();
        setToolbar();
        drawerLayout = findViewById(R.id.drawer_layout);
        createMenuList();
    }


    private void setToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menulist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageButton = new ImageButton(this);
        imageButton.setImageResource(R.drawable.ic_map);
        imageButton.setBackgroundColor(ContextCompat.getColor(
                getApplicationContext(), R.color.AfavourColor));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentActivity.getClass() == FavoursListActivity.class){
                    goTo(FavoursMapActivity.class);
                }else{
                    goTo(FavoursListActivity.class); }
                }
        });

        Toolbar.LayoutParams l3=new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT);
        l3.gravity=Gravity.END;
        l3.rightMargin=32;
        imageButton.setLayoutParams(l3);
        toolbar.addView(imageButton);




        ImageView sortDistance = new ImageButton(this);
        sortDistance.setImageResource(R.drawable.ic_sort);
        sortDistance.setBackgroundColor(ContextCompat.getColor(
                getApplicationContext(), R.color.AfavourColor));

        sortDistance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSortingDialog();
                }
            });

        l3.gravity = Gravity.END;
        l3.rightMargin = 64;
        sortDistance.setLayoutParams(l3);
        toolbar.addView(sortDistance);
        }


    private void showSortingDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Sort");
        String[] items = {"Ordenar por precio ascendente","Ordenar por precio descendente","Ordenar por distancia ascendente", "Ordenar por distancia descendente"};
        int checkedItem = -1;
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        favoursViewModel.sortByAscendingPrice();
                        break;
                    case 1:
                        favoursViewModel.sortByDescendingPrice();
                        break;
                    case 2:
                        favoursViewModel.sortByAscendingDistance(currentLocation.getValue());
                        break;
                    case 3:
                        favoursViewModel.sortByDescendingDistance(currentLocation.getValue());
                        break;
                }
                dialog.dismiss();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                openOptions(true);
                return true;
            default:
                openOptions(false);
                return false;
        }
    }


    public void openOptions(boolean open)
    {
        if (open){drawerLayout.openDrawer(GravityCompat.START);}
        else{drawerLayout.closeDrawer(GravityCompat.END);}
    }


    private void createMenuList() {
        navigationView = findViewById(R.id.left_drawer);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.itFavourxfavour:
                        filterByCategory(CategoryEnum.favourxfavour);
                        break;
                    case R.id.itDaytodaythings:
                        filterByCategory(CategoryEnum.daytodaythings);
                        break;
                    case R.id.itComputing:
                        filterByCategory(CategoryEnum.computing);
                        break;
                    case R.id.itReparation:
                        filterByCategory(CategoryEnum.reparation);
                        break;
                    case R.id.itOthers:
                        filterByCategory(CategoryEnum.others);
                        break;
                    case R.id.itProfile:
                        goTo(ProfileView.class);
                        break;
                    case R.id.itMessages:
                        goTo(MessagesView.class);
                        break;
                    case R.id.itConfig:
                        goTo(ConfigurationView.class);
                        break;
                    case R.id.itHelp:
                        goTo(HelpView.class);
                        break;
                }
                return true;
            }
        });
    }


    public void filterByCategory(CategoryEnum category){
        selectedCategory = category;
        Log.d(TAG, "selected item: " + selectedCategory.getName());
        if(selectedCategory == CategoryEnum.all) {
            selectedCategory = null;
        }
        refreshList();
    }




    public void createSpinnerType(){
        typeFilterSpinner = findViewById(R.id.filterSpinnerType);

        favourTypeSpinnerAdapter = new FavourTypeSpinnerAdapter(this,
                R.layout.spinner_favour_type_item,
                FavourTypeEnum.values());

        typeFilterSpinner.setAdapter(favourTypeSpinnerAdapter);

        typeFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedType = (FavourTypeEnum) typeFilterSpinner.getSelectedItem();
                Log.d(TAG, "selected item: " + selectedType.getName());
                if(selectedType == FavourTypeEnum.all) {
                    selectedType = null;
                }

                refreshList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



    protected void refreshList(){
        progressBar.setVisibility(View.VISIBLE);
        favoursViewModel.getFavours(null, selectedType, selectedCategory);
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}

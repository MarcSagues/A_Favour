package cat.udl.tidic.a_favour.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.MainClassViewModel;
import cat.udl.tidic.a_favour.models.UserModel;

public class MainPage extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout drawerLayout;
    private ListView llista;
    private ListView recyclerView;
    private Button uploadFavour;
    MainClassViewModel mainClassViewModel;
    DrawerItemCustomAdapter adapter_event;
    TabLayout tabs;
    View googleMap;
    DataModel.Favour[] mapFavours;
    Spinner filterSpinner;
    Spinner filterSpinnerCategory;
    MutableLiveData<UserModel> userModel = new MutableLiveData<UserModel>();
    DataModel dataModel;



    public MainPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mainClassViewModel = new MainClassViewModel(this, this);
        getAllActivityData();
        addListeners();
        setUpToolbar();
        createMenuList();
        createSpinner();
        createSpinnerCategory();
        setScrollListener();
        setUpObserver();
        openOptions(false);


    }

    @Override
    protected void onStop()
    {
        super.onStop();
        openOptions(false);
    }

    private void setUpObserver()
    {
        if (!FORTESTING.dev)
        {
            mainClassViewModel.getAllFavours().observe(this, this::onGetFavoursData);
        }
        else
        {
            DataModel.Favour[] eventList = FORTESTING.getExampleList();
            DrawerItemCustomAdapter adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList);
            recyclerView.setAdapter(adapter_event);
            recyclerView.setOnItemClickListener(new DrawerItemClickListener(this,mainClassViewModel));
        }
    }

    private void onGetFavoursData(List<DataModel.Favour> all_f)
    {
        DataModel.Favour[] eventList = all_f.toArray(new DataModel.Favour[0]);
        mapFavours = eventList;

        //Si el adaptador es nul, vol dir que no hi havien favors previament
       // if (adapter_event == null)
        //{
            //Posem els favors que ens retorna la crida i ja esta
            adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList, this);
        //}
        //En cas contrari, vol dir que hem carregat més favors
       // else
        //{
            //Concateno l'array de favors que ja teniem amb els que ens retorna la crida a al API
         //   DataModel.Favour[] concatenateArray = (DataModel.Favour[]) ArrayUtils.appendToArray(adapter_event.getData(),eventList);
         //   adapter_event.setData(concatenateArray);
       // }
        recyclerView.setAdapter(adapter_event);
        recyclerView.setOnItemClickListener(new DrawerItemClickListener(this,mainClassViewModel));

    }

    public void onGetFavoursArray(DataModel.Favour[] eventList)
    {
        mapFavours = eventList;
        System.out.println("EVENT LIST ======="+ Arrays.toString(eventList));

        //Si el adaptador es nul, vol dir que no hi havien favors previament
        // if (adapter_event == null)
        //{
        //Posem els favors que ens retorna la crida i ja esta
        //adapter_event.clear();
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList, this);

        //adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList, this);
        //}
        //En cas contrari, vol dir que hem carregat més favors
        // else
        //{
        //Concateno l'array de favors que ja teniem amb els que ens retorna la crida a al API
        //   DataModel.Favour[] concatenateArray = (DataModel.Favour[]) ArrayUtils.appendToArray(adapter_event.getData(),eventList);
        //   adapter_event.setData(concatenateArray);
        // }
        recyclerView.invalidate();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnItemClickListener(new DrawerItemClickListener(this,mainClassViewModel));

    }

    private void getAllActivityData()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.rv_recycler_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llista = findViewById(R.id.left_drawer);
        uploadFavour = findViewById(R.id.upload_afavour);
        tabs = findViewById(R.id.tabmain);
        googleMap = findViewById(R.id.googleMap);
        googleMap.setVisibility(View.GONE);
    }

    private void OnClickTab(boolean list)
    {
        //Si s'ha premut el botó de llista
        if (list)
        {
            recyclerView.setVisibility(View.VISIBLE);
            uploadFavour.setVisibility(View.VISIBLE);
            googleMap.setVisibility(View.GONE);
        }
        else
        {
            generateMap();
            recyclerView.setVisibility(View.GONE);
            uploadFavour.setVisibility(View.GONE);
            googleMap.setVisibility(View.VISIBLE);
        }
    }

    private void addListeners()
    {
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                if (tab.getText().equals(getString(R.string.list)))
                {
                    OnClickTab(true);
                }
                else
                {
                    OnClickTab(false);
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
                if (tab.getText().equals(getString(R.string.list)))
                {
                    OnClickTab(true);
                }
                else
                {
                    OnClickTab(false);
                }

            }
        });
    }

    public void goToUploadPage(View v)
    {
        openOptions(false);
        Intent intent = new Intent(v.getContext(), UploadFavour.class);
        Bundle b = new Bundle();
        b.putBoolean("upload", true); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivityForResult(intent, 0);
    }

    private void setUpToolbar()
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menulist);
    }

    private void createMenuList()
    {
        DataModel.MenuList[] drawerItem = new DataModel.MenuList[5];
        drawerItem[0] = new DataModel.MenuList(R.drawable.example_person, getResources().getString(R.string.goProfile));
        drawerItem[1] = new DataModel.MenuList(R.drawable.log_out, getResources().getString(R.string.messages));
        drawerItem[2] = new DataModel.MenuList(R.drawable.log_out, getResources().getString(R.string.config));
        drawerItem[3] = new DataModel.MenuList(R.drawable.log_out, getResources().getString(R.string.help));
        drawerItem[4] = new DataModel.MenuList(R.drawable.log_out, getResources().getString(R.string.logOut));
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        llista.setOnItemClickListener(new DrawerItemClickListener(this,mainClassViewModel));
        llista.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.drawable.menulist) {
            openOptions(true);
        } else {
            openOptions(true);
        }
        return true;
    }

    @SuppressLint("RtlHardcoded")
    public void openOptions(boolean open)
    {
        if (open){drawerLayout.openDrawer(Gravity.LEFT);}
        else{drawerLayout.closeDrawer(Gravity.LEFT);}
    }


    private void setScrollListener()
    {
        recyclerView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            private int mLastFirstVisibleItem;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (mLastFirstVisibleItem == 0)
                {
                    uploadFavour.animate().alpha(1.0f).setDuration(500).start();
                    uploadFavour.setClickable(true);
                }
                else
                {
                    uploadFavour.animate().alpha(0.0f).setDuration(500).start();
                    uploadFavour.setClickable(false);
                }
                mLastFirstVisibleItem=firstVisibleItem;
            }
        });
    }

    public void createSpinner(){

        filterSpinner = (Spinner) findViewById(R.id.filterSpinner);


        ArrayList<String> elements = new ArrayList<String>();
        elements.add("Distance");
        elements.add("Category"); //seleccionar categoria
        elements.add("Amount");
        //posar asc o desc
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, elements);

        filterSpinner.setAdapter(adp);
        Log.d("sortttt fora","");
        System.out.println("FORAAAAAAAAAAAAAAAA");
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSpinner = filterSpinner.getSelectedItem().toString();

                Log.d("sortttt","");
                System.out.println("DINS filter");
                if (selectedSpinner.equals("Category")){
                    setSpinnerVisible(true);
                } else{
                    setSpinnerVisible(false);
                    mainClassViewModel.orderList(selectedSpinner, 0);
                    //onGetFavoursData(listOfFavours);
//                    onGetFavoursData(Arrays.asList(listOfFavours));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Not sortttt","");
                System.out.println("Nothing");
            }
        });


    }

    public void createSpinnerCategory(){
        filterSpinnerCategory = findViewById(R.id.filterSpinnerCategory);
        ArrayList<String> elements = new ArrayList<String>();
        elements.add("Day to day things");
        elements.add("Computing");
        elements.add("Reparations"); //seleccionar categoria
        elements.add("Others");
        //posar asc o desc
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, elements);

        filterSpinnerCategory.setAdapter(adp);
        filterSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedSpinnerText = filterSpinnerCategory.getSelectedItem().toString();
                System.out.println("SELECCIONAR CATEGORIA"+selectedSpinnerText);
                mainClassViewModel.orderListCategory(selectedSpinnerText, 0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        filterSpinnerCategory.setVisibility(View.GONE);
        ;

    }


    public void LoadMore(int listnumber)
    {
       // mainClassViewModel.getFavours(listnumber);
    }

    public void setSpinnerVisible(Boolean visible) {
        if (visible){
            filterSpinnerCategory.setVisibility(View.VISIBLE);
        } else{
            filterSpinnerCategory.setVisibility(View.GONE);
        }


    }

    @Override
    public void onMapReady(GoogleMap map)
    {
        setUserLocation(map);
        setAllFavours(map);
    }

    //Es podria fer millor
    private void setAllFavours(GoogleMap map)
    {
        int i = 0;
        if (FORTESTING.dev)
        {
            for (DataModel.Favour mapFavour : FORTESTING.getExampleList())
            {
                addFavourOnMap(map, mapFavour,i);
                System.out.println(mapFavour.name + "-------------------------");
                i++;
            }
        }
        else {

            for (DataModel.Favour mapFavour : mapFavours)
            {
                addFavourOnMap(map, mapFavour,i);
                System.out.println(mapFavour.name + "-------------------------");
                i++;
            }
        }
    }

    private void addFavourOnMap(GoogleMap map, DataModel.Favour fav, int pos)
    {
        LatLng favourposition =new LatLng(fav.getLat(), fav.getLong_());
        /*map.addCircle(new CircleOptions()
                .center(favourposition)
                .radius(20)
                .strokeWidth(3f)
                .strokeColor(ContextCompat.getColor(getBaseContext(),R.color.AfavourColor))
                .fillColor(ContextCompat.getColor(getBaseContext(),R.color.MapFillColor))
        );*/

        map.addMarker(new MarkerOptions().position(favourposition)
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons(fav.getCategoria(),100,100))))
                .setTag(pos);

    }


    public Bitmap resizeMapIcons(String iconName,int width, int height){
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(),getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }

    private void generateMap()
    {
        SupportMapFragment mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMap));
        assert mMap != null;
        mMap.getMapAsync(this);
    }

    //TODO : Esta hardcoded
    private void setUserLocation(GoogleMap map)
    {
        //Falta agafar el valor real de la ubicació de l'usuari
        Context context = this;
        LatLng igualada1 = new LatLng(41.591677, 1.614331);
        map.moveCamera(CameraUpdateFactory.newLatLng(igualada1));
        map.setMinZoomPreference(16);
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                Log.d("Click Clock", "Cluck");
                DrawerItemClickListener dw = new DrawerItemClickListener(context);
                if (FORTESTING.dev)
                {
                    dw.goToSeeAnunci(FORTESTING.getExampleList()[(int) marker.getTag()]);
                }
                else {
                    dw.goToSeeAnunci(mapFavours[(int) marker.getTag()]);
                }
                return false;
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


package cat.udl.tidic.a_favour.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.MainClassViewModel;

public class MainPage extends AppCompatActivity
{
    private DrawerLayout drawerLayout;
    private ListView llista;
    private ListView recyclerView;
    private Button uploadFavour;
    private Spinner filterSpinner;
    private Spinner filterSpinnerCategory;
    MainClassViewModel mainClassViewModel;
    DrawerItemCustomAdapter adapter_event;
    DataModel.Favour[] listOfFavours;


    public MainPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mainClassViewModel = new MainClassViewModel(this);
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

    private void getAllActivityData()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.rv_recycler_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llista = findViewById(R.id.left_drawer);
        uploadFavour = findViewById(R.id.upload_afavour);
    }

    private void addListeners() {}

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
                    listOfFavours = mainClassViewModel.orderList(selectedSpinner, 0);
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
}


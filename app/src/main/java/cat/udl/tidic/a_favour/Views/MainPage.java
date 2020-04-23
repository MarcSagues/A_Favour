package cat.udl.tidic.a_favour.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import java.lang.reflect.Method;
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
    MainClassViewModel mainClassViewModel;
    RelativeLayout loadingpanel;

    public MainPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        //getLoadingPanel();
        getAllActivityData();
        addListeners();
        setUpToolbar();
        createMenuList();
        setScrollListener();
        mainClassViewModel = new MainClassViewModel(this);
        setUpObserver();
        openOptions(false);

    }

    @Override
    protected void onStop() {
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
            recyclerView.setOnItemClickListener(new DrawerItemClickListener(this));
        }
    }

    private void onGetFavoursData(List<DataModel.Favour> all_f)
    {
        DataModel.Favour[] eventList = all_f.toArray(new DataModel.Favour[0]);
        DrawerItemCustomAdapter adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList);
        recyclerView.setAdapter(adapter_event);
        recyclerView.setOnItemClickListener(new DrawerItemClickListener(this));

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

    /*
    public void goToEditPage(DataModel d)
    {
        openOptions(false);
        Intent intent = new Intent(this, UploadFavour.class);
        Bundle b = new Bundle();
        b.putBoolean("upload", false); //Your id
        //b.putParcelable("data", d);
        intent.putExtras(b); //Put your id to your next Intent
        startActivityForResult(intent, 0);
    }*/

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
        llista.setOnItemClickListener(new DrawerItemClickListener(this));
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


    /*public void goToProfile()
    {
        openOptions(false);
        Intent intent = new Intent (this, ProfileView.class);
        startActivityForResult(intent, 0);
    }*/

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


    /*
    @Override
    public void getLoadingPanel()
    {
        loadingpanel = findViewById(R.id.loadingPanel);
    }

    @Override
    public void enableLoadinggPanel(boolean enable)
    {
        if (enable){loadingpanel.setVisibility(View.VISIBLE);}
        else{loadingpanel.setVisibility(View.GONE);}
    }

    @Override
    public void generatAlertDialog()
    {
        //Si falla la connexió s'haura de posar un layout de "error"
        enableLoadinggPanel(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialogMessage).setTitle(R.string.dialogTitle);

        builder.setPositiveButton(R.string.retry, (dialog, id) ->
        {
           mainClassViewModel.getFavours(this);
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) ->
        {
            dialog.cancel();
        });

        builder.show();
    }
    */
}

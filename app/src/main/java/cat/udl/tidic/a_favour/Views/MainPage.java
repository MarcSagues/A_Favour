package cat.udl.tidic.a_favour.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.Objects;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;

public class MainPage  extends AppCompatActivity
{
    private DrawerLayout drawerLayout;
    private ListView llista;
    private ListView recyclerView;
    private Button uploadFavour;

    public MainPage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getAllActivityData();
        addListeners();
        setUpToolbar();
        createMenuList();
        getAllEventList();
        setScrollListener();
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
        Intent intent = new Intent (v.getContext(), UploadFavour.class);
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
        DataModel[] drawerItem = new DataModel[2];
        drawerItem[0] = new DataModel(R.drawable.example_person, getResources().getString(R.string.goProfile),null,-1);
        drawerItem[1] = new DataModel(R.drawable.log_out, getResources().getString(R.string.logOut),null,-1);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        llista.setOnItemClickListener(new DrawerItemClickListener(this));
        llista.setAdapter(adapter);
        llista.getScrollX();

        DataModel[] eventList = new DataModel[5];
        eventList[0] = new DataModel(R.drawable.handshacke, "Necessito ajuda per pujar la compra a casa",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc molestie vitae dolor id faucibus. Fusce eu venenatis risus. Fusce malesuada, ipsum at hendrerit dignissim, mauris ligula accumsan elit, eget facilisis diam quam eget sapien. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam mollis varius velit, vel ornare purus imperdiet et. Sed tempus turpis sed ex pellentesque, id dignissim mauris condimentum. Ut posuere quam quis nisi vestibulum semper. Vestibulum nec aliquet metus. Quisque sit amet velit in lorem bibendum vestibulum a et lectus."
        ,25);
        eventList[1] = new DataModel(R.drawable.handshacke, "Test2","test description 2",2.5f);
        eventList[2] = eventList[0];
        eventList[3] = eventList[0];
        eventList[4] = eventList[0];
        DrawerItemCustomAdapter adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, eventList);
        recyclerView.setAdapter(adapter_event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.drawable.menulist) {
            Log.d("nani", "noni");
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

    public void getAllEventList()
    {

    }

    public void goToProfile()
    {
        openOptions(false);
        Intent intent = new Intent (this, ProfileView.class);
        startActivityForResult(intent, 0);
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

}

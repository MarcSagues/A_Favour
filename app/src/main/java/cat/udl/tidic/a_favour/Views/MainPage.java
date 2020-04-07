package cat.udl.tidic.a_favour.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getAllActivityData();
        addListeners();
        setUpToolbar();
        createMenuList();
    }

    private void getAllActivityData()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        llista = findViewById(R.id.left_drawer);
    }

    private void addListeners() {}

    private void setUpToolbar()
    {
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menulist);
    }

    private void createMenuList()
    {
        DataModel[] drawerItem = new DataModel[2];
        drawerItem[0] = new DataModel(R.drawable.example_person, getResources().getString(R.string.goProfile));
        drawerItem[1] = new DataModel(R.drawable.log_out, getResources().getString(R.string.logOut));
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        llista.setOnItemClickListener(new DrawerItemClickListener(this));
        llista.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.drawable.menulist) {
            Log.d("nani", "noni");
            openOptions();
        } else {
            openOptions();
        }
        return true;
    }

    @SuppressLint("RtlHardcoded")
    public void openOptions()
    {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    public void goToProfile()
    {
        Intent intent = new Intent (this, ProfileView.class);
        startActivityForResult(intent, 0);
    }

}

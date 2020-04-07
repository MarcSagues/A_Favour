package cat.udl.tidic.a_favour.Views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import cat.udl.tidic.a_favour.DataModel;
import cat.udl.tidic.a_favour.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;

public class MainPage  extends AppCompatActivity
{
    private Button goToProfile;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ListView llista;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getAllActivityData();
        addListeners();
    }

    private void getAllActivityData()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menulist);
        llista = findViewById(R.id.left_drawer);
        DataModel[] drawerItem = new DataModel[2];
        drawerItem[0] = new DataModel(R.drawable.example_person, "Go to profile");
        drawerItem[1] = new DataModel(R.drawable.log_out, "Log out");
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        llista.setOnItemClickListener(new DrawerItemClickListener(this));
        llista.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.drawable.menulist:
                Log.d("nani", "noni");
                openOptions();
                break;

            default:
                openOptions();

        }
        return true;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        MainPage p;
        public  DrawerItemClickListener(MainPage p)
        {
            this.p = p;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            if (position == 0)
            {
                Log.d("Open profile", "a");
                p.goToProfile();
            }
            else if (position == 1)
            {
                Log.d("Log out", "a");
            }
        }
    }

        private void openOptions()
    {
        drawerLayout.openDrawer(Gravity.LEFT);
    }

    private void addListeners()
    {
        /*
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });*/
    }

    void goToProfile()
    {
        Intent intent = new Intent (this, ProfileView.class);
        startActivityForResult(intent, 0);
    }

}

package cat.udl.tidic.a_favour.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.LoginViewModel;
import cat.udl.tidic.a_favour.models.UserModel;

import static androidx.core.content.ContextCompat.startActivity;

public class AnunciView extends AppCompatActivity implements OnMapReadyCallback {

    ListView anunci;
    ListView valoracio;
    DataModel.Favour[] favour;
    DataModel.Opinion[] userOpinion;
    ImageView back;
    ImageView edit;
    Boolean isMyFavour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veure_anunci);
        isMyFavourvoid();
        getAllData();
        setAnunci();
        setValoracio();
        generateMap();
        preparePage();
        setClickListeners();
    }

    private void isMyFavourvoid() {
        Bundle b = getIntent().getExtras();
        if (b != null) {
            Log.d("YYY La variable is my favour és :", String.valueOf(b.getBoolean("myfavour")));
            isMyFavour = b.getBoolean("myfavour");
        } else {
            Log.d("YYY La variable is my favour és :", "NO HI HA");
            isMyFavour = false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void preparePage() {
        if (isMyFavour) {
            edit.setImageResource(R.drawable.pencil);
            valoracio.setVisibility(View.GONE);
        } else {
            edit.setImageResource(R.drawable.heart);
            setTag();
            valoracio.setVisibility(View.VISIBLE);
        }
    }

    private void setClickListeners() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMyFavour) {
                    goToEditFavour();
                } else {
                    addFvaourites();
                }
            }
        });
    }


    private void setTag()
    {
        setImageandTag(favour[0].isFavourite() ? R.drawable.hearthfull : R.drawable.heart);
    }

    private void setImageandTag(int tag)
    {
        edit.setImageResource(tag);
        edit.setTag(tag);
    }

    private void addFvaourites()
    {
        int currentImageTag = (int) edit.getTag();
        if (currentImageTag == R.drawable.heart)
        {
            //Posar a favoritos en la base de dades
        }
        else
        {
            //Treure de favoritos en la base de dades
        }
        setImageandTag(currentImageTag == R.drawable.heart ? R.drawable.hearthfull : R.drawable.heart);
    }
    private void goToEditFavour()
    {
        Intent intent = new Intent (this, UploadFavour.class);
        Bundle b= new Bundle();
        b.putBoolean("upload", false);
        startActivity(intent,b);
    }

    private void getAllData()
    {
        anunci = findViewById(R.id.anunci);
        valoracio = findViewById(R.id.valoracio);
        back = findViewById(R.id.back_arrow);
        edit = findViewById(R.id.edit);
        favour = new DataModel.Favour[1];
        favour[0] = new DataModel.Favour("Necessito ajuda per a noseque","Aixo és una descripció una mica més llarga per a veure com qeudaria hahahah molt be lore ipsum ole ole",2, CategoryManager.CATEGORIES.computing.name(),1,"asd");
        DrawerItemCustomAdapter favour_adapter = new DrawerItemCustomAdapter(this, R.layout.favour_list_profile, favour);
        anunci.setAdapter(favour_adapter);

        userOpinion = new DataModel.Opinion[1];
        userOpinion[0] = new DataModel.Opinion(R.drawable.example_person, "Username","",2.4f);
        DrawerItemCustomAdapter userOpinion_adapter = new DrawerItemCustomAdapter(this, R.layout.user_opinion, userOpinion);
        valoracio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d("Carregant el profile", "");
                Intent intent = new Intent (getApplicationContext(), ProfileView.class);
                Bundle b= new Bundle();
                b.putBoolean("myprofile", false);
                intent.putExtras(b);
                startActivity(intent,b);
            }
        });
        valoracio.setAdapter(userOpinion_adapter);

    }

    private void setAnunci()
    {

    }

    private void setValoracio()
    {

    }

    private void generateMap()
    {
        SupportMapFragment mMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
        mMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(41.591677, 1.614331);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.addCircle(new CircleOptions()
                .center(sydney)
                .radius(120)
                .strokeWidth(3f)
                .strokeColor(getResources().getColor(R.color.AfavourColor))
                .fillColor(getResources().getColor(R.color.MapFillColor))
        );
        googleMap.setMinZoomPreference(16);
    }
}
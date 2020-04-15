package cat.udl.tidic.a_favour.Views;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class AnunciView extends AppCompatActivity implements OnMapReadyCallback {

    ListView anunci;
    ListView valoracio;
    DataModel.Favour[] favour;
    DataModel.Opinion[] userOpinion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veure_anunci);
        getAllData();
        setAnunci();
        setValoracio();
        generateMap();
    }

    private void getAllData()
    {
        anunci = findViewById(R.id.anunci);
        valoracio = findViewById(R.id.valoracio);
        favour = new DataModel.Favour[1];
        favour[0] = new DataModel.Favour("Necessito ajuda per a noseque","Aixo és una descripció una mica més llarga per a veure com qeudaria hahahah molt be lore ipsum ole ole",2, CategoryManager.CATEGORIES.computing.name(),1,"asd");
        DrawerItemCustomAdapter favour_adapter = new DrawerItemCustomAdapter(this, R.layout.favour_list_profile, favour);
        anunci.setAdapter(favour_adapter);



        userOpinion = new DataModel.Opinion[1];
        userOpinion[0] = new DataModel.Opinion(R.drawable.example_person, "Username","",2.4f);
        DrawerItemCustomAdapter userOpinion_adapter = new DrawerItemCustomAdapter(this, R.layout.user_opinion, userOpinion);
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
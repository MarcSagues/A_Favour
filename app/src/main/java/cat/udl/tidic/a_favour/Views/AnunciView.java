package cat.udl.tidic.a_favour.Views;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;

public class AnunciView extends AppCompatActivity implements OnMapReadyCallback
{
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

    private void isMyFavourvoid()
    {
        //Mira si estem en un favor que és nostre o no
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

    private void preparePage()
    {
        if (isMyFavour)
        {
            //Si és el meu favor, poso l'opció d'editar-lo
            edit.setImageResource(R.drawable.pencil);
            //Trec la valoració de l'usuari, perque sóc jo mateix
            valoracio.setVisibility(View.GONE);
        }
        else {
            //Si NO és el meu favor, trec l'opció de poguer editar-lo i afegeixo un cor
            edit.setImageResource(R.drawable.heart);
            setTag();
            //Poso la valaoració de l'usuari de l'anunci
            valoracio.setVisibility(View.VISIBLE);
        }
    }

    private void setClickListeners() {

        back.setOnClickListener(v -> onBackPressed());

        edit.setOnClickListener(v ->
        {
            //Si es el meu favor, vaig a la pàgina d'edita favor
            if (isMyFavour) { goToEditFavour(); }
            //Sino, doncs l'afageixo a favoritos
            else { addFvaourites();}
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

        /*
        if (currentImageTag == R.drawable.heart)
        {
            //Posar a favoritos en la base de dades
        }
        else
        {
            //Treure de favoritos en la base de dades
        }*/
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
        valoracio.setOnItemClickListener((parent, view, position, id) -> {
            Log.d("Carregant el profile", "");
            Intent intent = new Intent (getApplicationContext(), ProfileView.class);
            Bundle b= new Bundle();
            b.putBoolean("myprofile", false);
            intent.putExtras(b);
            startActivity(intent,b);
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
        assert mMap != null;
        mMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng igualada1 = new LatLng(41.591677, 1.614331);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(igualada1));
        googleMap.addCircle(new CircleOptions()
                .center(igualada1)
                .radius(120)
                .strokeWidth(3f)
                .strokeColor(ContextCompat.getColor(getBaseContext(),R.color.AfavourColor))
                .fillColor(ContextCompat.getColor(getBaseContext(),R.color.MapFillColor))
        );
        googleMap.setMinZoomPreference(16);
        LatLng puntoblanco = new LatLng(41.586645, 1.615023);

        float[] res = new float[1];
        Location.distanceBetween(igualada1.latitude,igualada1.longitude, puntoblanco.latitude, puntoblanco.longitude, res);
        Log.d(String.valueOf(res[0]), "Metros de distancia");
    }
}
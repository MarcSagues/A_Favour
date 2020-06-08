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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.OpinionsAdapter;
import cat.udl.tidic.a_favour.ProfileClasses.RecyclerViewManager;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.adapters.FavourAdapter;
import cat.udl.tidic.a_favour.adapters.FavourDiffCallback;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.Opinions;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.models.UploadFavourModel;
import cat.udl.tidic.a_favour.models.UserModel;

public class AnunciView extends AppCompatActivity implements OnMapReadyCallback
{
    RecyclerView anunci;
    ListView valoracio;
    Opinions[] userOpinion;
    ImageView back;
    ImageView eliminar;
    ImageView edit;
    Boolean isMyFavour;
    Favour currentFavour;
    ProfileViewModel pview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veure_anunci);
        pview = new ProfileViewModel(this);
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
        if (b != null)
        {
            Log.d("YYY La variable is my favour és :", String.valueOf(b.getBoolean("myfavour")));
            isMyFavour = b.getBoolean("myfavour");
            currentFavour = (Favour) getIntent().getSerializableExtra("favour");
            assert currentFavour != null;
            pview.getUserProfile().observe(this, this::onGetUserData);
            pview.getAnotherUser(String.valueOf(currentFavour.getOwner_id()));

        } else {
            Log.d("YYY La variable is my favour és :", "NO HI HA");
            isMyFavour = false;
        }
    }

    private void onGetUserData(UserModel userModel)
    {
        if (userModel != null) {
            userOpinion = new Opinions[1];
            userOpinion[0] = new Opinions(R.drawable.example_person, userModel.getUsername(), "", userModel.getStars());
            OpinionsAdapter userOpinion_adapter = new OpinionsAdapter(this, R.layout.user_opinion, userOpinion);
            valoracio.setOnItemClickListener((parent, view, position, id) ->
            {
                Log.d("Carregant el profile", "");
                Intent intent = new Intent(getApplicationContext(), ProfileView.class);
                Bundle b = new Bundle();
                b.putBoolean("myprofile", false);
                intent.putExtras(b);
                intent.putExtra("favour", (Serializable) currentFavour);
                startActivity(intent, b);
            });
            valoracio.setAdapter(userOpinion_adapter);
        }
    }

    @Override
    public void onBackPressed() {
        if (isMyFavour)
        {
            Intent i = new Intent(this, ProfileView.class);
            startActivityForResult(i, 1);
            finish();
        }
        else { super.onBackPressed();}
    }

    private void preparePage()
    {
        if (isMyFavour)
        {
            //Si és el meu favor, poso l'opció d'editar-lo
            edit.setImageResource(R.drawable.pencil);
            //Trec la valoració de l'usuari, perque sóc jo mateix
            valoracio.setVisibility(View.GONE);
            eliminar.setVisibility(View.VISIBLE);
        }
        else {
            //Si NO és el meu favor, trec l'opció de poguer editar-lo i afegeixo un cor
            edit.setImageResource(R.drawable.heart);
            setTag();
            //Poso la valaoració de l'usuari de l'anunci
            valoracio.setVisibility(View.VISIBLE);
            eliminar.setVisibility(View.GONE);
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

        AnunciView a = this;
        eliminar.setOnClickListener(v -> {
            UploadFavourModel vm = new UploadFavourModel(a);
            vm.eliminarFavor(currentFavour.getId(), a);
        });

    }


    private void setTag()
    {
        setImageandTag(currentFavour.isFavourite() ? R.drawable.hearthfull : R.drawable.heart);
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
        intent.putExtra("favour", currentFavour);
        startActivity(intent,b);
        finish();
    }

    private void getAllData()
    {
        eliminar = findViewById(R.id.eliminar);
        anunci = findViewById(R.id.rv_recycler_view);
        anunci.setLayoutManager(new LinearLayoutManager(this));
        valoracio = findViewById(R.id.valoracio);
        back = findViewById(R.id.back_arrow);
        edit = findViewById(R.id.edit);
        Favour[] favour = new Favour[1];
        favour[0] = currentFavour;

        FavourAdapter favour_adapter = new FavourAdapter(new FavourDiffCallback(), AnunciView.class);
        anunci.setAdapter(favour_adapter);
        favour_adapter.submitList(Arrays.asList(favour));
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
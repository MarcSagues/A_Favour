package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.LoginViewModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class LoginView extends AppCompatActivity {

    private EditText userTxt;
    private EditText pwdTxt;
    private Button registerBtn;
    private Button loginBtn;
    private LoginViewModel loginViewModel;
    private float latitud;
    private float longitud;
    LocationManager locationManager;
    Criteria criteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLenguage();
        iniComponents();

        if (FORTESTING.dev) {
            openMainPage();
        }
        checkLogin();
        setButtonListeners();
    }

    @SuppressWarnings("deprecation")
    private void getLenguage() {
        SharedPreferences shared = PreferencesProvider.providePreferences();
        String lang = shared.getString("lenguage", "en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources res = getBaseContext().getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    public void iniComponents() {
        loginViewModel = new LoginViewModel();
        userTxt = findViewById(R.id.user_txt);
        pwdTxt = findViewById(R.id.pwd_txt);
        registerBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);
        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        //TODO
        //String a = getColoredSpanned("A","#ff3b5b");
        //title.setText(Html.fromHtml(a + " Favour"));
    }

    public void checkLogin() {
        //Comprobar si hi ha token guardat
        if (PreferencesProvider.existToken("token") && PreferencesProvider.existToken("id")) {
            if (FORTESTING.checkLogin) {
                openMainPage();
            } else {
                Log.d("No és fa el CHECK LOGIN", "perque FORTESTING.checklogin és false");
            }
        }
    }

    public void setButtonListeners() {
        registerBtn.setOnClickListener(this::clickOnRegister);

        loginBtn.setOnClickListener(this::clickOnLogin);
    }

    public void openMainPage() {
        Intent intent = new Intent(getApplicationContext(), MainPage.class);
        startActivity(intent);

        System.out.println("--------------FORA DEL IF--------------------");
        locationStart();
        finish();
    }

    private void locationStart() {
        assert locationManager != null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(Objects.requireNonNull(locationManager
                .getBestProvider(criteria, false)));
        location = getLastKnownLocation();
        if(location!= null){
            latitud = 0;
            longitud = 0;
            latitud = (float) location.getLatitude();
            longitud = (float) location.getLongitude();
            SharedPreferences mPreferences = PreferencesProvider.providePreferences();
            System.out.println("LONGITUD --------"+longitud+"\nLATITUD --------"+latitud);
            mPreferences.edit().putFloat("longitud", longitud).apply();
            mPreferences.edit().putFloat("latitud", latitud).apply();
        }

    }

    private Location getLastKnownLocation() {
        Location l=null;
        LocationManager mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
                l = mLocationManager.getLastKnownLocation(provider);
            }
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }


    public void clickOnRegister(View v)
    {
        Intent intent = new Intent (v.getContext(), RegisterView.class);
        startActivityForResult(intent, 0);
    }

    public void clickOnLogin(View v)
    {
        String username = userTxt.getText().toString();
        String password = pwdTxt.getText().toString();
        loginViewModel.checkCorrectLogin(username, password, this);
    }

    public void sendMessage(String message)
    {
        Toast.makeText(LoginView.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }


}

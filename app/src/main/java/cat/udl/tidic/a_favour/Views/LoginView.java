package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import java.util.Locale;

import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.LenguageManager;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.LoginViewModel;

import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class LoginView extends AppCompatActivity {

    private EditText userTxt;
    private EditText pwdTxt;
    private Button registerBtn;
    private Button loginBtn;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLenguage();
        iniComponents();

        if (FORTESTING.dev)
        {
            openMainPage();
        }
        checkLogin();
        setButtonListeners();
    }

    private void getLenguage()
    {
        SharedPreferences shared = PreferencesProvider.providePreferences();
        String lang = shared.getString("lenguage","en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources res = getBaseContext().getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        res.updateConfiguration(config, res.getDisplayMetrics());
        Intent i = getIntent();
    }
    public void iniComponents()
    {
        loginViewModel = new LoginViewModel();
        userTxt = findViewById(R.id.user_txt);
        pwdTxt = findViewById(R.id.pwd_txt);
        registerBtn = findViewById(R.id.register_btn);
        loginBtn = findViewById(R.id.login_btn);

        //TODO
        //String a = getColoredSpanned("A","#ff3b5b");
        //title.setText(Html.fromHtml(a + " Favour"));
    }

    public void checkLogin()
    {
        //Comprobar si hi ha token guardat
        if (PreferencesProvider.existToken("token")) { openMainPage();}
    }

    public void setButtonListeners()
    {
        registerBtn.setOnClickListener(this::clickOnRegister);

        loginBtn.setOnClickListener(this::clickOnLogin);
    }

    public void openMainPage()
    {
        Intent intent = new Intent (getApplicationContext(), MainPage.class);
        startActivity(intent);
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

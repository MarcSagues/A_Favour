package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText user_txt;
    private EditText pwd_txt;
    private Button register_btn;
    private Button login_btn;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniComponents();

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnRegister(v);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnLogin(v);
            }
        });

    }

    public void iniComponents(){
        user_txt = findViewById(R.id.user_txt);
        pwd_txt = findViewById(R.id.pwd_txt);
        register_btn = findViewById(R.id.register_btn);
        login_btn = findViewById(R.id.login_btn);

        this.mPreferences = PreferencesProvider.providePreferences();

    }

    public void clickOnLogin(View v) {


        String username = user_txt.getText().toString();
        String password = pwd_txt.getText().toString();
        String token_decoded = username + ":" + password;
        byte[] bytes = token_decoded.getBytes(StandardCharsets.UTF_8);
        String _token = Base64.encodeToString(bytes, Base64.DEFAULT);
        mPreferences.edit().putString("token", _token).apply();
        Toast.makeText(getApplicationContext(),
                "Token obtained properly", Toast.LENGTH_SHORT).show();

    }

    public void clickOnRegister(View v){
        Intent intent = new Intent (v.getContext(), Register.class);
        startActivityForResult(intent, 0);
    }
        public void sendMessage(String message){
            Toast.makeText(Login.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
        }


}

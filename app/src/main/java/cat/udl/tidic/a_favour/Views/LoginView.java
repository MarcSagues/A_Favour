package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class LoginView extends AppCompatActivity {

    private EditText user_txt;
    private EditText pwd_txt;
    private Button register_btn;
    private Button login_btn;
    private TextView title;
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
        title = findViewById(R.id.title);
        String a = getColoredSpanned("A","#ff3b5b");
        title.setText(Html.fromHtml(a + " Favour"));
    }

    public void clickOnLogin(View v) {


        String username = user_txt.getText().toString();
        String password = pwd_txt.getText().toString();

        ProfileViewModel profileViewModel = new ProfileViewModel();
        profileViewModel.setUser(username, password);

        Intent intent = new Intent (getApplicationContext(), ProfileView.class);
        startActivity(intent);




    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
    public void clickOnRegister(View v){
        Intent intent = new Intent (v.getContext(), RegisterView.class);
        startActivityForResult(intent, 0);
    }
    public void sendMessage(String message){
        Toast.makeText(LoginView.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }


}

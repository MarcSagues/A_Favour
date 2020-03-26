package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterView extends AppCompatActivity {

    private EditText pwd_txt;
    private EditText user_txt;
    private EditText confirm_pwd_txt;
    private Button register_btn;
    private Button facebook_btn;
    private Button gmail_btn;
    private UserServices userService;
    private EditText email_txt;
    private EditText phone_txt;
    private TextView join_txt;
    private TextView go_to_login_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniComponents();
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickOnRegister(v);
            }
        });
        go_to_login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin(v);
            }
        });

    }



    public void ClickOnRegister(View v) {

        String password1 = pwd_txt.getText().toString();
        String password2 = confirm_pwd_txt.getText().toString();
        String user = user_txt.getText().toString();
        String email = email_txt.getText().toString();
        String phone = phone_txt.getText().toString();

        ProfileViewModel profileViewModel = new ProfileViewModel();
        profileViewModel.registerUser(user, password1, password2, email, phone, RegisterView.this);




    }

    public void goToLogin(View v) {


        Intent intent = new Intent (getApplicationContext(), LoginView.class);
        startActivity(intent);




    }


    public void iniComponents(){
        user_txt = findViewById(R.id.user_txt);
        pwd_txt = findViewById(R.id.pwd_txt);
        confirm_pwd_txt = findViewById(R.id.confirm_pwd_txt);
        register_btn = findViewById(R.id.register_btn);
        facebook_btn = findViewById(R.id.facebook_btn);
        gmail_btn = findViewById(R.id.gmail_btn);
        email_txt = findViewById(R.id.email_txt);
        phone_txt = findViewById(R.id.mobile_txt);
        join_txt = findViewById(R.id.join_txt);
        String join = getColoredSpanned("Join", "#000000");
        String a = getColoredSpanned("A", "#FF3B5B");
        String favour = getColoredSpanned("Favour", "#000000");
        join_txt.setText(Html.fromHtml(join+" "+a+" "+favour));
        go_to_login_txt = findViewById(R.id.go_to_login_txt);

    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    public void sendMessage(String message){
        Toast.makeText(RegisterView.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }

}

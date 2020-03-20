package cat.udl.tidic.a_favour;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private EditText pwd_txt;
    private EditText user_txt;
    private EditText confirm_pwd_txt;
    private Button register_btn;
    private Button facebook_btn;
    private Button gmail_btn;
    private UserServices userService;
    private EditText email_txt;
    private EditText phone_txt;

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

    }



    public void ClickOnRegister(View v) {

        String valor1 = pwd_txt.getText().toString();
        String valor2 = confirm_pwd_txt.getText().toString();


        if (valor1.equals(valor2)){ //comprovem que les contrasenyes siguin iguals

            // Course API requires passwords in sha-256 in passlib format so:
            String p = pwd_txt.getText().toString();
            String salt = "16";
            String encode_hash = Utils.encode(p,salt,29000);
            System.out.println("PASSWORD_ENCRYPTED " + encode_hash);


            JsonObject user_json = new JsonObject();
            user_json.addProperty("username", user_txt.getText().toString());
            user_json.addProperty("email", email_txt.getText().toString());
            user_json.addProperty("phone", phone_txt.getText().toString());
            user_json.addProperty("password", encode_hash);

            Call<Void> call = userService.registerUser3(user_json);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200){
                        Toast.makeText(Register.this,"User registered", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            Toast.makeText(Register.this, Objects.requireNonNull(response.errorBody()).string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });

        } else {
            sendMessage("Las contraseñas no coinciden");
        }


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
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
    }

    public void sendMessage(String message){
        Toast.makeText(Register.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }

}

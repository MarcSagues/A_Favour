package cat.udl.tidic.a_favour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cat.udl.tidic.a_favour.Views.ProfileView;

public class Login extends AppCompatActivity {

    private EditText user_txt;
    private EditText pwd_txt;
    private Button register_btn;
    private Button login_btn;

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
    }

    public void clickOnLogin(View v){
        //comprovar base de dades
        Intent intent = new Intent (v.getContext(), ProfileView.class);
        startActivityForResult(intent, 0);
    }

    public void clickOnRegister(View v){
        Intent intent = new Intent (v.getContext(), Register.class);
        startActivityForResult(intent, 0);
    }


}

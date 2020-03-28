package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

public class RegisterView extends AppCompatActivity {

    private EditText pwdTxt;
    private EditText userTxt;
    private EditText confirmPwdTxt;
    private Button register_btn;
    private Button facebookBtn;
    private Button gmailBtn;
    private UserServices userService;
    private EditText emailTxt;
    private EditText phoneTxt;
    private TextView joinTxt;
    private TextView goToLoginTxt;

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
        goToLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin(v);
            }
        });

    }

    public void ClickOnRegister(View v) {

        String password1 = pwdTxt.getText().toString();
        String password2 = confirmPwdTxt.getText().toString();
        String user = userTxt.getText().toString();
        String email = emailTxt.getText().toString();
        String phone = phoneTxt.getText().toString();

        ProfileViewModel profileViewModel = new ProfileViewModel();
        profileViewModel.registerUser(user, password1, password2, email, phone, RegisterView.this);
    }

    public void goToLogin(View v) {

        Intent intent = new Intent (getApplicationContext(), LoginView.class);
        startActivity(intent);
    }


    public void iniComponents()
    {
        userTxt = findViewById(R.id.user_txt);
        pwdTxt = findViewById(R.id.pwd_txt);
        confirmPwdTxt = findViewById(R.id.confirm_pwd_txt);
        register_btn = findViewById(R.id.register_btn);
        facebookBtn = findViewById(R.id.facebook_btn);
        gmailBtn = findViewById(R.id.gmail_btn);
        emailTxt = findViewById(R.id.email_txt);
        phoneTxt = findViewById(R.id.mobile_txt);
        joinTxt = findViewById(R.id.join_txt);
        String join = getColoredSpanned("Join", "#000000");
        String a = getColoredSpanned("A", "#FF3B5B");
        String favour = getColoredSpanned("Favour", "#000000");
        joinTxt.setText(Html.fromHtml(join+" "+a+" "+favour));
        goToLoginTxt = findViewById(R.id.go_to_login_txt);
    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    public void sendMessage(String message){
        Toast.makeText(RegisterView.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }

}

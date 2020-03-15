package cat.udl.tidic.a_favour;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText pwd_txt;
    private  EditText user_txt;
    EditText confirm_pwd_txt;
    Button register_btn;
    Button facebook_btn;
    Button gmail_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniComponents();

    }

    public void ClickOnRegister(){
        if (!pwd_txt.getText().equals(confirm_pwd_txt.getText())){ //comprovem que les contrasenyes siguin iguals
            sendMessage("Las contraseñas no coinciden");
        }
        // comprovar base de dades
    }


    public void iniComponents(){
        user_txt = findViewById(R.id.user_txt);
        pwd_txt = findViewById(R.id.pwd_txt);
        confirm_pwd_txt = findViewById(R.id.confirm_pwd_txt);
        register_btn = findViewById(R.id.register_btn);
        facebook_btn = findViewById(R.id.facebook_btn);
        gmail_btn = findViewById(R.id.gmail_btn);
    }

    public void sendMessage(String message){
        Toast.makeText(Register.this,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }

}

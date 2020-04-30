package cat.udl.tidic.a_favour.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonObject;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.models.EditProfileViewModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileView extends AppCompatActivity {


    private EditText email;
    private EditText user;
    private EditText pwd;
    private EditText confirm_pwd;
    private EditText phone_number;
    private Button update;
    private EditProfileViewModel editProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_view);
        email = findViewById(R.id.email_txt3);
        user = findViewById(R.id.user_txt3);
        pwd = findViewById(R.id.pwd_txt3);
        confirm_pwd = findViewById(R.id.confirm_pwd_txt3);
        phone_number = findViewById(R.id.mobile_txt3);
        update = findViewById(R.id.update_btn3);
        editProfileViewModel = new EditProfileViewModel();
        update.setOnClickListener(this::updateUser);

    }


    public void backArrowAction(View v)
    {
        onBackPressed();
    }

    public void updateUser(View v){
        editProfileViewModel.updateUser(user.getText().toString(), pwd.getText().toString(), confirm_pwd.getText().toString(), email.getText().toString(), phone_number.getText().toString(), this);
    }

}



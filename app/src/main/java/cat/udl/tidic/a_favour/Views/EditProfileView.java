package cat.udl.tidic.a_favour.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.Intent;
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
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.models.UserModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileView extends AppCompatActivity {


    private EditText email;
    private EditText username;
    private EditText pwd;
    private EditText confirm_pwd;
    private EditText phone_number;
    private Button update;
    private EditProfileViewModel editProfileViewModel;
    private ProfileViewModel profileViewModel;
    private MutableLiveData<UserModel> user = new MutableLiveData<>();
    public LiveData<UserModel> getUserProfile(){ return user; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_view);
        email = findViewById(R.id.email_txt3);
        username = findViewById(R.id.user_txt3);
        pwd = findViewById(R.id.pwd_txt3);
        confirm_pwd = findViewById(R.id.confirm_pwd_txt3);
        phone_number = findViewById(R.id.mobile_txt3);
        update = findViewById(R.id.update_btn3);
        editProfileViewModel = new EditProfileViewModel(this);
        update.setOnClickListener(this::updateUser);
        profileViewModel = new ProfileViewModel(this);
        profileViewModel.getUser();
        profileViewModel.getUserProfile().observe(this, this::onGetUserData);

    }

    private void onGetUserData(UserModel userModel) {

        Log.d("EditProfile-onGetUserData", ""+userModel.getUsername());
        if (userModel == null){
            return;
        }
        username.setText(userModel.getUsername());
        email.setText(userModel.getEmail());
        phone_number.setText(userModel.getPhone());

    }

    public void openProfile(){
        Intent intent = new Intent (getApplicationContext(), ProfileView.class);
        startActivity(intent);
        finish();
    }



    public void backArrowAction(View v)
    {
        onBackPressed();
    }

    public void updateUser(View v){
        editProfileViewModel.updateUser(username.getText().toString(), pwd.getText().toString(), confirm_pwd.getText().toString(), email.getText().toString(), phone_number.getText().toString(), this);
    }

}



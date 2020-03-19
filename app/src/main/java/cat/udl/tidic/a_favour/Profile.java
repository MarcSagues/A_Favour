package cat.udl.tidic.a_favour;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    UserModel u = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    /*  Log.d("Proifile","asdasdasda");

        UserModel user = new UserModel();
        user.setUsername("Peptio787");
        user.setSurname("Delospalotes");
        user.setName("Pep");
        user.setPassword("1234");
        user.setGenere("M");
        user.setEmail("hemene@gmail.com");

        UserServices userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);

        Call<Void> call_post = userService.registerUser(user);
        call_post.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("MainActivity", String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });

        Call<UserModel> usr_profile = userService.getUserProfile();
        Log.d("Profile",usr_profile + "asdasdasda");

        //String token = tokenEditText.getText().toString();
        /*
        String token = "656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf";
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", token);

        UserServices userServices = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        Call<UserModel> call = userService.getUserProfile();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                u = response.body();
                try {
                    //resultTextView.setText(u.toString());
                } catch (Exception e) {
                    Log.e("MainActivity", e.getMessage());
                }
            }


            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

                Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });

*/
    }
}

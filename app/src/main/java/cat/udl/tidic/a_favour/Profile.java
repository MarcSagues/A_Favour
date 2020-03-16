package cat.udl.tidic.a_favour;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity
{
    UserModel u = new UserModel();
    UserServices userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);

    //Layout elements
    TextView user_name;
    ImageView[] stars;
    ImageView profil_image;
    TextView favours_info;
    TextView user_location;
    Button favours_btn;
    Button favourites_btn;
    Button opinions_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getAllActivityData();
        getUserData();
    }

    private void getAllActivityData()
    {
        user_name = findViewById(R.id.name);

        stars = new ImageView[5];
        stars[0] = findViewById(R.id.star1);
        stars[1] = findViewById(R.id.star2);
        stars[2] = findViewById(R.id.star3);
        stars[3] = findViewById(R.id.star4);
        stars[4] = findViewById(R.id.star5);

        profil_image = findViewById(R.id.profile_image);
        favours_info = findViewById(R.id.favours_info);
        user_location = findViewById(R.id.user_location);

        favours_btn = findViewById(R.id.favours_btn);
        favourites_btn = findViewById(R.id.favourites_btn);
        opinions_btn = findViewById(R.id.opinions_btn);

        //Falta crear tot lo relacionat amb els anuncis que ha publicat

    }

    private void getUserData()
    {
        String token = "656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf";
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", token);

        UserServices userServices = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<UserModel> call = userService.getUserProfile(map);

        call.enqueue(new Callback<UserModel>()
        {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response)
            {
                u = response.body();
                try
                {
                    Log.e("MainActivity", u.toString());
                    updateUI(u);
                }
                catch (Exception e) { Log.e("MainActivity", e.getMessage() + "ERROR"); }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) { Toast.makeText(Profile.this, t.getMessage(), Toast.LENGTH_SHORT).show(); }
        });
    }

    private void updateUI(UserModel u)
    {
        user_name.setText(u.getUsername());

        //Get UserProfile Image
        //profil_image.setImageResource();

        //L'average stars l'agafarem de la base de dades
        //float average_stars = u.getStars;
        float average_stars = 4.2f;
        float roundedStars = Math.round(average_stars * 2) / 2.0f;
        udpateStars(roundedStars);

        //int favours_done = u.getFavoutsDone();
        //int time_helped = u.getTimesHelped();
        int favoursDone = 2;
        int timesHelped = 5;
        favours_info.setText( Integer.toString(favoursDone) + " favours done, " + Integer.toString(timesHelped) + " times helped");

        //String location = u.getLocation();
        String location = "08123, Barcelona";
        user_location.setText(location);
    }

    private void udpateStars(float roundedStars)
    {
        int fullStars = (int) roundedStars;
        for (int i =0; i < 5 ; i++)
        {
            if (i < fullStars) { stars[i].setImageResource(R.drawable.star_full); }
            else
            {
                if ((float)fullStars - roundedStars != 0.0f) { stars[i].setImageResource(R.drawable.star_half); }
                else { stars[i].setImageResource(R.drawable.star_empty); }
            }
        }
    }
}

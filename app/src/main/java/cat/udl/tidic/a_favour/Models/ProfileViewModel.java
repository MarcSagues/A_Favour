package cat.udl.tidic.a_favour.Models;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import cat.udl.tidic.a_favour.Models.UserModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Views.ProfileView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel
{
    private UserModel user = new UserModel();
    private UserServices userService;
    private ProfileView view;


    public ProfileViewModel(ProfileView view)
    {
        //Aqui li passo la view perque sino el Profile s'actualitza abans de que aquesta classe pugui obtindre les dades
        // del usuari, ja que la petició GET tarda una mica

        this.view = view;
        getUser();
        userService =  RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
    }
   void getUser()
   {

       //Hardcoded
       String token = "656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf";
       Map<String, String> map = new HashMap<>();
       map.put("Authorization", token);

       userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
       Call<UserModel> call = userService.getUserProfile(map);

       call.enqueue(new Callback<UserModel>()
       {
           @Override
           public void onResponse(Call<UserModel> call, Response<UserModel> response)
           {

               try
               {
                   user = response.body();
                   setUser(user);
                   view.updateUI();
               }
               catch (Exception e) { Log.e("ProfileViewModel", e.getMessage() + "ERROR");}
           }

           @Override
           public void onFailure(Call<UserModel> call, Throwable t)
           {
               view.setErrorLayout();
               Log.e("ProfileViewModel",  t.getMessage());
               //Toast.makeText(ProfileViewModel.this, t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
   }

   public void setUser(UserModel user)
   {
       this.user = user;
   }

   public String getUsername()
   {
       return this.user.getUsername();
   }

    public float getStars()
    {
        return this.user.getStars();
    }

    public ImageView[] refreshStars(ImageView[] stars)
    {
        float roundedStars = Math.round(getStars() * 2) / 2.0f;
        int fullStars = (int) roundedStars;
        float decimalpart =  roundedStars - (int) roundedStars;

        for (int i =0; i < stars.length ; i++)
        {
            if (i < fullStars) { stars[i].setImageResource(R.drawable.star_full); }
            else if ( i == fullStars)
            {
                if (decimalpart != 0.0f) { stars[i].setImageResource(R.drawable.star_half); }
            }
            else { stars[i].setImageResource(R.drawable.star_empty); }
        }

        return stars;
    }

    public String updateFavoursInfo()
    {
        int favoursDone = this.user.getFavoursDone();
        int timesHelped = this.user.getTimesHelped();
        return Integer.toString(favoursDone) + " favours done, " + Integer.toString(timesHelped) + " times helped";
    }

    public String  getLocation()
    {
        String location = this.user.getLocation() == null ? "No location" : this.user.getLocation();
        return location;
    }
}

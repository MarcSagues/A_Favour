package cat.udl.tidic.a_favour.models;

import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.User;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Views.ProfileView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel
{
    //private UserModel user = new UserModel();
    private UserServices userService;


    public MutableLiveData<UserModel> user = new MutableLiveData<>();
    public LiveData<UserModel> getUserProfile(){ return user; }

    public ProfileViewModel()
    {
        getUser();
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
                   user.setValue(response.body());
               }
               catch (Exception e) { Log.e("ProfileViewModel", e.getMessage() + "ERROR");}
           }

           @Override
           public void onFailure(Call<UserModel> call, Throwable t)
           {
               //view.setErrorLayout();
               Log.e("ProfileViewModel",  t.getMessage());
               //Toast.makeText(ProfileViewModel.this, t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
   }

   public String getUsername()
   {
       return this.user.getValue().getUsername();
   }

   public String getPassword(){
        return this.user.getValue().getPassword();
   }

    public float getStars_()
    {
        return this.user.getValue().getStars();
    }

    public ImageView[] getStars(ImageView[] stars)
    {
        float roundedStars = Math.round(getStars_() * 2) / 2.0f;
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

    public String getFavoursInfo()
    {
        int favoursDone = this.user.getValue().getFavoursDone();
        int timesHelped = this.user.getValue().getTimesHelped();
        return Integer.toString(favoursDone) + " favours done, " + Integer.toString(timesHelped) + " times helped";
    }

    public String  getLocation()
    {
        String location = this.user.getValue().getLocation() == null ? "No location" : this.user.getValue().getLocation();
        return location;
    }

    public void showLocationBtn()
    {
        Log.d("Profile", "S'ha premut l'opció SHOW LOCATION");
    }
    public void backArrowBtn()
    {
        Log.d("Profile", "S'ha premut l'opció DE TIRAR ENRRERE");
    }
    public void favoursBtn() { Log.d("Profile", "S'ha premut l'opció DE FAVOURS"); }
    public void favouritesBtn() { Log.d("Profile", "S'ha premut l'opció FAVOURITES"); }
    public void opinionsBtn()
    {
        Log.d("Profile", "S'ha premut l'opció OPINIONS");
    }

}

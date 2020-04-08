package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cat.udl.tidic.a_favour.ProfileClasses.MyAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel
{
    //private UserModel user = new UserModel();
    private UserServices userService;
    private SharedPreferences mPreferences;


    private MutableLiveData<UserModel> user = new MutableLiveData<>();
    public LiveData<UserModel> getUserProfile(){ return user; }

   public ProfileViewModel()
   {
       userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        mPreferences = PreferencesProvider.providePreferences();
       String token = mPreferences.getString("token", "");
        Log.d("Token:", token);
        getUser();
   }

   public void getUser()
   {
       Map<String, String> map = new HashMap<>();
       Log.d("IS TOKEN EMPTY? TOKEN = ", mPreferences.getString("token", ""));
       map.put("Authorization", mPreferences.getString("token", ""));

       //Esta hardcodejat perque falta fer la gestió d'errors al agafar els valors
       //De moment mostra les dades de l'usuari 1
       //map.put("Authorization", "656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf");

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

               user.setValue(null);
               Log.e("ProfileViewModel", Objects.requireNonNull(t.getMessage()));
               //Toast.makeText(ProfileViewModel.this, t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
   }

   public String getUsername()
   {
       return Objects.requireNonNull(this.user.getValue()).getUsername();
   }

   public String getPassword(){
        return Objects.requireNonNull(this.user.getValue()).getPassword();
   }

    public float getStars()
    {
        return Objects.requireNonNull(this.user.getValue()).getStars();
    }

    public String getFavoursDone()
    {
        int favoursDone = Objects.requireNonNull(this.user.getValue()).getFavoursDone();
        return Integer.toString(favoursDone);
    }

    public String getTimesHelped()
    {
        int timesHelped = Objects.requireNonNull(this.user.getValue()).getTimesHelped();
        return Integer.toString(timesHelped);
    }

    public String  getLocation()
    {
        return Objects.requireNonNull(this.user.getValue()).getLocation() == null ? "No location" : this.user.getValue().getLocation();
    }

    public String[] getTitles()
    {
        try
        {
            return new String[]{"test 0","test one", "test two", "test three", "test four", "test five", "test six", "test seven", "test eight", "test nine"};
        }
        catch(Exception e)
        {
            System.out.println("Impossible agafar els titols de l'usuari");
            return  null;
        }
    }

    public String[] getDesc()
    {
        try
        {
            return new String[]{"desc 0 ","desc one", "desc two", "desc 2", "desc 3", "desc 5", "desc 6", "desc 7", "desc 8", "desc 9"};
        }
        catch(Exception e)
        {
            System.out.println("Impossible agafar les descripcions de l'usuari");
            return  null;
        }
    }

    public ImageView[] getImage()
    {
        try
        {
            ImageView image = new ImageView(null);
            image.setImageResource(R.drawable.handshacke);
            image.setTag(R.drawable.handshacke);
            return new ImageView[]{image,image,image,image,image,image,image,image,image};
        }
        catch(Exception e)
        {
            System.out.println("Impossible agafar la imatge de l'usuari");
            return  null;
        }
    }

    public float[] getOpinionStars()
    {
        try
        {
            return new float[]{0.5f, 5, 3.5f, 4,5,5,5,5,5,8};
        }
        catch(Exception e)
        {
            System.out.println("Impossible agafar les estrelles de les opinions");
            return  null;
        }
    }

    public void showLocationBtn()
    {
        Log.d("Profile", "S'ha premut l'opció SHOW LOCATION");
    }



    public float[] getAmount()
    {
        return new float[]{0.5f, 5, 3.5f, 4,5,5,5,5,5,8};
    }
}

package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
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
    public enum LISTOFTYPE { Favours, Favourites, Opinions}


    private MutableLiveData<UserModel> user = new MutableLiveData<>();
    public LiveData<UserModel> getUserProfile(){ return user; }
    private MutableLiveData<List<DataModel.Favour>> myFavours = new MutableLiveData<>();
    public LiveData<List<DataModel.Favour>> getMyFavours_(){ return myFavours; }

   public ProfileViewModel()
   {
       userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        mPreferences = PreferencesProvider.providePreferences();
       String token = mPreferences.getString("token", "");
        Log.d("Token:", token);
        getUser();
   }

   public DataModel[] getListOf(LISTOFTYPE type, boolean myprofile)
   {
       if (myprofile)
       {
           if (type.equals(LISTOFTYPE.Favours)) { return getMyFavours(); }
           if (type.equals(LISTOFTYPE.Favourites)) { return getMyFavourites(); }
           else if (type.equals(LISTOFTYPE.Opinions)) { return getMyOpinions(); }
       }
       else
       {
           if (type.equals(LISTOFTYPE.Favours)) { return getMyFavours(); }
           if (type.equals(LISTOFTYPE.Favourites)) { return getMyFavourites(); }
           else if (type.equals(LISTOFTYPE.Opinions)) { return getMyOpinions(); }
       }

       return null;
   }

    private DataModel.Opinion[] getMyOpinions()
    {
        return FORTESTING.getExampleListOPINION();
    }

    private DataModel.Favour[] getMyFavours()
    {
       return FORTESTING.getExampleList();
    }

    private DataModel.Favour[] getMyFavourites()
    {
        return FORTESTING.getExampleList();
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
       //noinspection NullableProblems
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

    public void getMyFavoursVoid(String userID)
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<List<DataModel.Favour>> call = userService.getFavours(userID,token);
        //noinspection NullableProblems
        call.enqueue(new Callback<List<DataModel.Favour>>()
        {
            @Override
            public void onResponse(Call<List<DataModel.Favour>> call, Response<List<DataModel.Favour>> response)
            {
                try
                {

                    List<DataModel.Favour> response_ = response.body();

                    assert response_ != null;
                    for (int i = 0; i < response_.size(); i++)
                    {
                        response_.get(i).setIcon();
                    }
                    myFavours.setValue(response_);
                }
                catch (Exception e) { Log.d("Salta el catch -------", e.getMessage() + "ERROR");}
            }

            @Override
            public void onFailure(Call<List<DataModel.Favour>> call, Throwable t)
            {


                Log.e("---------------", Objects.requireNonNull(t.getMessage()));
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

    public void showLocationBtn()
    {
        Log.d("Profile", "S'ha premut l'opció SHOW LOCATION");
    }
}

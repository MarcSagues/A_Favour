package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClassViewModel {
    //private UserModel user = new UserModel();
    private UserServices userService;
    private SharedPreferences mPreferences;


    private MutableLiveData<UserModel> user = new MutableLiveData<>();
    public LiveData<UserModel> getUserProfile(){ return user; }

    public MainClassViewModel()
    {
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        mPreferences = PreferencesProvider.providePreferences();
        String token = mPreferences.getString("all_favours", "");
        Log.d("Token:", token);
        getFavours();
    }

    public void getFavours()
    {
        Map<String, String> map = new HashMap<>();
        Log.d("IS TOKEN EMPTY? TOKEN = ", mPreferences.getString("token", ""));
        map.put("Authorization", mPreferences.getString("all_favours", ""));

        //Esta hardcodejat perque falta fer la gestió d'errors al agafar els valors
        //De moment mostra les dades de l'usuari 1
        //map.put("Authorization", "656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf");

        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<UserModel> call = userService.getFavours(map);

        call.enqueue(new Callback<UserModel>()
        {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response)
            {
                try
                {
                    //user.setValue(response.body());
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

}

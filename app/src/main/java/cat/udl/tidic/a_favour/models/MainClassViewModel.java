package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClassViewModel
{
    //private UserModel user = new UserModel();
    private UserServices userService;
    private SharedPreferences mPreferences;
    private MutableLiveData<List<DataModel.Favour>> allFavours = new MutableLiveData<>();
    public LiveData<List<DataModel.Favour>> getAllFavours(){ return allFavours; }


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
        map.put("Authorization", mPreferences.getString("favours", ""));

        //Esta hardcodejat perque falta fer la gestió d'errors al agafar els valors
        //De moment mostra les dades de l'usuari 1
        //map.put("Authorization", "656e50e154865a5dc469b80437ed2f963b8f58c8857b66c9bf");

        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        Call<List<DataModel.Favour>> call = userService.getFavours(map);

        call.enqueue(new Callback<List<DataModel.Favour>>()
        {
            @Override
            public void onResponse(Call<List<DataModel.Favour>> call, Response<List<DataModel.Favour>> response)
            {
                try
                {
                   List<DataModel.Favour> response_ = (List<DataModel.Favour>) response.body();
                    for (int i =0; i < response_.size(); i++)
                    {
                        response_.get(i).setIcon();
                    }
                    allFavours.setValue(response_);
                }
                catch (Exception e) { Log.d("------------------", e.getMessage() + "ERROR");}
            }

            @Override
            public void onFailure(Call<List<DataModel.Favour>> call, Throwable t)
            {


                Log.e("---------------", Objects.requireNonNull(t.getMessage()));
                //Toast.makeText(ProfileViewModel.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
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

    private void getFavours()
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<List<DataModel.Favour>> call = userService.getFavours(null,token);
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
                    allFavours.setValue(response_);
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

}

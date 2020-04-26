package cat.udl.tidic.a_favour.models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.Objects;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Views.LoadingPanel;
import cat.udl.tidic.a_favour.Views.LoginView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainClassViewModel
{
    //private UserModel user = new UserModel();
    private UserServices userService;
    private MutableLiveData<List<DataModel.Favour>> allFavours = new MutableLiveData<>();
    public LiveData<List<DataModel.Favour>> getAllFavours(){ return allFavours; }
    private Context c;


    public MainClassViewModel(Context c)
    {
        this.c = c;
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        SharedPreferences mPreferences = PreferencesProvider.providePreferences();
        String token = mPreferences.getString("all_favours", "");
        Log.d("Token:", token);
        getFavours();
    }

    public static void  logOut()
    {
        Context c = App.getAppContext();
        //UserServices userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        SharedPreferences sp = PreferencesProvider.providePreferences();
        sp.edit().remove("token").apply();
        sp.edit().remove("id").apply();
        Intent intent = new Intent(c, LoginView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        c.startActivity(intent);

    }

    private void getFavours()
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<List<DataModel.Favour>> call = userService.getFavours(null,token);
        LoadingPanel.enableLoading(c,true);
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
                    LoadingPanel.enableLoading(c,false);
                }
                catch (Exception e) { Log.d("Salta el catch -------", e.getMessage() + "ERROR");}
            }

            @Override
            public void onFailure(Call<List<DataModel.Favour>> call, Throwable t)
            {
                try { LoadingPanel.setErrorDialog(c,() -> { getFavours();return null; });}
                catch (Exception e) { e.printStackTrace();}
                Log.e("---------------", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}

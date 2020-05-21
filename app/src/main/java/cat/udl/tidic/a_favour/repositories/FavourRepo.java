package cat.udl.tidic.a_favour.repositories;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.dao.FavourDAO;
import cat.udl.tidic.a_favour.dao.FavourDAOImpl;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavourRepo {

    private String TAG = "FavourRepo";
    private SharedPreferences mPreferences;

    private FavourDAO favourDao;
    private MutableLiveData<List<Favour>> mDataResponseFavour;
    private MutableLiveData<String> mMsgResponseFavour;

    public FavourRepo() {
        this.favourDao = new FavourDAOImpl();
        this.mDataResponseFavour = new MutableLiveData<>();
        this.mMsgResponseFavour = new MutableLiveData<>();
        this.mPreferences = PreferencesProvider.providePreferences();
    }


    public void getFavours(String user_id, FavourTypeEnum type, CategoryEnum category){

        String token = mPreferences.getString("token","");
        favourDao.getFavours(user_id,token, type, category).enqueue(new Callback<List<Favour>>() {


            @Override
            public void onResponse(Call<List<Favour>> call, Response<List<Favour>> response) {

                int code = response.code();
                Log.d(TAG,  "getFavours() -> ha rebut del backend un codi:  " + code);

                if (code == 200 ){
                    List<Favour> favours = response.body();
                    Log.d(TAG,  "getFavours() -> ha rebut una llista de mida: "+ favours.size());
                    mDataResponseFavour.setValue(favours);
                    mMsgResponseFavour.setValue(null);
                }
                else{
                    try {
                        String error_msg = "Error: " + response.errorBody().string();
                        Log.d(TAG,  "createTokenUser() -> ha rebut l'error:  " + error_msg);
                        mDataResponseFavour.setValue(null);
                        mMsgResponseFavour.setValue(error_msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Favour>> call, Throwable t) {
                String error_msg = "Error: " + t.getMessage().toString();
                Log.d(TAG,  "getFavours() onFailure() -> ha rebut el missatge:  " + error_msg);
                mMsgResponseFavour.setValue(error_msg);
                mDataResponseFavour.setValue(null);
            }
        });
    }

    public MutableLiveData<List<Favour>> getmDataResponseFavour() {
        return mDataResponseFavour;
    }
    public MutableLiveData<String> getmMsgResponseFavour() {
        return mMsgResponseFavour;
    }
}

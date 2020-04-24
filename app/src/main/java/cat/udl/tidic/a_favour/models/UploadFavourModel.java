package cat.udl.tidic.a_favour.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.Views.AnunciView;
import cat.udl.tidic.a_favour.Views.LoadingPanel;
import cat.udl.tidic.a_favour.Views.RegisterView;
import cat.udl.tidic.a_favour.Views.UploadFavour;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFavourModel
{
    private UserServices userService;
    private  Context c;
    public UploadFavourModel(Context c)
    {
        this.c = c;
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
    }

    public void editFavour(DataModel.Favour fav, UploadFavour upFav)
    {
        JsonObject user_json = new JsonObject();
            user_json.addProperty("name", fav.name);
            user_json.addProperty("description", fav.description);
            user_json.addProperty("category", fav.category);
            user_json.addProperty("amount", fav.amount);

            String token = PreferencesProvider.providePreferences().getString("token","");
        Call<Void> call = userService.setFavours(token,fav.id,user_json);
        LoadingPanel.enableLoading(c,true);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    response.body();
                    LoadingPanel.sendMessage(c.getString(R.string.favorUpdated));
                    upFav.onSucces();
                }
                else
                {
                    try
                    {
                        assert response.errorBody() != null;
                        LoadingPanel.sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    LoadingPanel.enableLoading(c,false);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                //sendMessage("Error en actualitzar el Favor");
                try { LoadingPanel.setErrorDialog(c,() -> { editFavour(fav,upFav);return null; });}
                catch (Exception e) { e.printStackTrace();}
            }
        });
    }


    public void eliminarFavor(int id, AnunciView anclass)
    {
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<Void> call = userService.deleteFavour(token,id);
        //noinspection NullableProblems
        LoadingPanel.enableLoading(c,true);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    response.body();
                    LoadingPanel.sendMessage(c.getString(R.string.favourDeleted));
                    anclass.onBackPressed();
                }
                else
                {
                    try
                    { //Atrapar error usuari existent / correu existent
                        assert response.errorBody() != null;
                        LoadingPanel.sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                LoadingPanel.enableLoading(c,false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                //LoadingPanel.sendMessage("Error en eliminar el Favor");
                try { LoadingPanel.setErrorDialog(c,() -> { eliminarFavor(id,anclass);return null; });}
                catch (Exception e) { e.printStackTrace();}
            }
        });
    }

    public void postFavour(DataModel.Favour currentFavourData, UploadFavour upFav)
    {
        String token = PreferencesProvider.providePreferences().getString("token","");
        JsonObject user_json = new JsonObject();
        user_json.addProperty("username", currentFavourData.user);
        user_json.addProperty("name", currentFavourData.name);
        user_json.addProperty("desc", currentFavourData.description);
        user_json.addProperty("category", currentFavourData.category);
        user_json.addProperty("amount", currentFavourData.amount);
        Call<Void> call = userService.postFavour(token,user_json);
        //noinspection NullableProblems
        LoadingPanel.enableLoading(c,true);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    response.body();
                    LoadingPanel.sendMessage(c.getString(R.string.favorUploaded));
                    upFav.onSucces();
                }
                else
                {
                    try
                    { //Atrapar error usuari existent / correu existent
                        assert response.errorBody() != null;
                        LoadingPanel.sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

                LoadingPanel.enableLoading(c,false);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                //sendMessage("Error en pujar el Favor");
                try { LoadingPanel.setErrorDialog(c,() -> { postFavour(currentFavourData,upFav);return null; });}
                catch (Exception e) { e.printStackTrace();}
            }
        });
    }
}


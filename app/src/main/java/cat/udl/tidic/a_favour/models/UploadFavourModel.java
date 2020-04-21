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
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.Views.RegisterView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFavourModel
{
    private UserServices userService;

    public UploadFavourModel()
    {
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
    }

    public void editFavour(DataModel.Favour fav)
    {
        JsonObject user_json = new JsonObject();
            user_json.addProperty("name", fav.name);
            user_json.addProperty("description", fav.description);
            user_json.addProperty("category", fav.category);
            user_json.addProperty("amount", fav.amount);

            String token = PreferencesProvider.providePreferences().getString("token","");
        Call<Void> call = userService.setFavours(token,fav.id,user_json);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    response.body();
                    sendMessage("Favour Updatet! :D");
                }
                else
                {
                    try
                    { //Atrapar error usuari existent / correu existent
                        assert response.errorBody() != null;
                        sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                sendMessage("Error en actualitzar el Favor");
            }
        });
    }

    private void sendMessage(String message)
    {
        Context c = App.getAppContext();
        Toast.makeText(c , message, Toast.LENGTH_SHORT).show();
    }

    public void eliminarFavor(int id)
    {
        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<Void> call = userService.deleteFavour(token,id);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    response.body();
                    sendMessage("Favour Deleted! :D");
                }
                else
                {
                    try
                    { //Atrapar error usuari existent / correu existent
                        assert response.errorBody() != null;
                        sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                sendMessage("Error en eliminar el Favor");
            }
        });
    }

    public void postFavour(DataModel.Favour currentFavourData)
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
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    response.body();
                    sendMessage("Favour UPLOADED! :D");
                }
                else
                {
                    try
                    { //Atrapar error usuari existent / correu existent
                        assert response.errorBody() != null;
                        sendMessage(Objects.requireNonNull(response.errorBody().string()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                sendMessage("Error en eliminar el Favor");
            }
        });
    }
}


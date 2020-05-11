package cat.udl.tidic.a_favour.models;
/*
import android.content.Context;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Objects;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Views.LoadingPanel;
import cat.udl.tidic.a_favour.Views.UploadFavour;
import cat.udl.tidic.a_favour.Views.UploadOpinion;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadOpinionModel {
    private UserServices userService;
    private Context c;
    public UploadOpinionModel(Context c)
    {
        this.c = c;
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
    }

    public void editOpinion(DataModel.Opinion opi, UploadOpinion upOpi)
    {
        JsonObject user_json = new JsonObject();
        user_json.addProperty("Description", opi.description);
        user_json.addProperty("description", opi.mark);


        String token = PreferencesProvider.providePreferences().getString("token","");
        Call<Void> call = userService.setOpinion(token,opi.id,user_json);
        LoadingPanel.enableLoading(c,true);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    LoadingPanel.sendMessage(c.getString(R.string.opinionUpdated));
                    upOpi.onSucces();
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


        });
    }

    public void postOpinion(DataModel.Opinion currentOpinionData, UploadOpinion upOpi)
    {
        String token = PreferencesProvider.providePreferences().getString("token","");
        JsonObject user_json = new JsonObject();
        Object opi;
        UserModel user;
        user_json.addProperty("Description", currentOpinionData.description);
        user_json.addProperty("Mark", currentOpinionData.mark);
        Call<Void> call = userService.postOpinion(token,user.getId);
        LoadingPanel.enableLoading(c,true);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if (response.code() == 200)
                {
                    LoadingPanel.sendMessage(c.getString(R.string.opinionUploaded));
                    upOpi.onSucces();
                }
                else
                {
                    try
                    { //Quan el comentari ja esta fet error
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
                //sendMessage("Error en pujar la opinio");
                try { LoadingPanel.setErrorDialog(c,() -> { postOpinion(currentOpinionData,upOpi);return null; });}
                catch (Exception e) { e.printStackTrace();}
            }
        });
    }
}

*/
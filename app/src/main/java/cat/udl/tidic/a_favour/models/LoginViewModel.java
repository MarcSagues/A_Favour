package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Views.LoginView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel
{
    private UserServices userService;
    private SharedPreferences mPreferences;


    public LoginViewModel()
    {
        mPreferences = PreferencesProvider.providePreferences();
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
    }

    public void checkCorrectLogin(String username, String password, LoginView loginView)
    {

        String tokenDecoded = username + ":" + password;
        byte[] bytes = tokenDecoded.getBytes(StandardCharsets.UTF_8);
        String tokenAux = Base64.encodeToString(bytes, Base64.DEFAULT);
        //mPreferences.edit().putString("token", tokenAux).apply();
        tokenAux = ("Authentication: " + tokenAux).trim();
        Call<ResponseBody> call = userService.createToken(tokenAux);
        //noinspection NullableProblems
        call.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.body() != null){
                    try {
                        String token = response.body().string().split(":")[1];
                        token = token.substring(2, token.length()-2);
                        setToken(token);
                        Log.d("Login completed", token);
                        getUser(loginView);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    try {
                        assert response.errorBody() != null;
                        Log.d("Login error", response.errorBody().string());
                        loginView.sendMessage("Error on Login");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Login failed", Objects.requireNonNull(t.getMessage()));
                loginView.sendMessage("Error on Login");
            }
        });
    }

    private void getUser(LoginView loginView)
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
                    UserModel user;
                    user = response.body();
                    assert user != null;
                    mPreferences.edit().putInt("id", user.getId()).apply();
                    Log.d("PUT THE ID " + user.getId(), "ON PREFERENCES");
                    loginView.openMainPage();
                }
                catch (Exception e) { Log.e("ProfileViewModel", e.getMessage() + "ERROR");}
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t)
            {
                Log.e("ProfileViewModel", Objects.requireNonNull(t.getMessage()));
                //Toast.makeText(ProfileViewModel.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setToken(String token)
    {
        mPreferences.edit().putString("token", token).apply();
    }
}

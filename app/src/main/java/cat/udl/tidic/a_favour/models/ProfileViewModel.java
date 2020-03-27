package cat.udl.tidic.a_favour.models;

import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NavUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.Views.RegisterView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel
{
    //private UserModel user = new UserModel();
    private UserServices userService;
    private SharedPreferences mPreferences;
    private String token;


    public MutableLiveData<UserModel> user = new MutableLiveData<>();
    public LiveData<UserModel> getUserProfile(){ return user; }

    public ProfileViewModel()
    {
        userService = RetrofitClientInstance.
                getRetrofitInstance().create(UserServices.class);
        mPreferences = PreferencesProvider.providePreferences();
        token = mPreferences.getString("token", "");
        Log.d("Token:", token);
        getUser();
    }

   public void getUser()
   {
       Map<String, String> map = new HashMap<>();
       map.put("Authorization", token);

       userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
       Call<UserModel> call = userService.getUserProfile(map);

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
               Log.e("ProfileViewModel",  t.getMessage());
               //Toast.makeText(ProfileViewModel.this, t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });
   }


   public void setUser(String username, String password){

       String tokenDecoded = username + ":" + password;
       byte[] bytes = tokenDecoded.getBytes(StandardCharsets.UTF_8);
       String tokenAux = Base64.encodeToString(bytes, Base64.DEFAULT);
       //mPreferences.edit().putString("token", tokenAux).apply();
       tokenAux = ("Authentication: " + tokenAux).trim();
       Call<ResponseBody> call = userService.createToken(tokenAux);
       call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if (response.body() != null){
                                try {
                                    String token = response.body().string().split(":")[1];
                                    token = token.substring(2, token.length()-2);
                                    setToken(token);
                                    Log.d("Login completed", token);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else{
                                try {
                                    Log.d("Login completed", response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("Login failed", t.getMessage());
                        }
                    });
   }

   public void setToken (String token){

        mPreferences.edit().putString("token", token).apply();
   }

   public String getUsername()
   {
       return this.user.getValue().getUsername();
   }

   public String getPassword(){
        return this.user.getValue().getPassword();
   }

    public float getStars()
    {
        return this.user.getValue().getStars();
    }

    public String getFavoursInfo()
    {
        int favoursDone = this.user.getValue().getFavoursDone();
        int timesHelped = this.user.getValue().getTimesHelped();
        return Integer.toString(favoursDone) + " favours done, " + Integer.toString(timesHelped) + " times helped";
    }

    public String  getLocation()
    {
        String location = this.user.getValue().getLocation() == null ? "No location" : this.user.getValue().getLocation();
        return location;
    }

    public void showLocationBtn()
    {
        Log.d("Profile", "S'ha premut l'opció SHOW LOCATION");
    }
    public void backArrowBtn()
    {
        Log.d("Profile", "S'ha premut l'opció DE TIRAR ENRRERE");
    }
    public void favoursBtn() { Log.d("Profile", "S'ha premut l'opció DE FAVOURS"); }
    public void favouritesBtn() { Log.d("Profile", "S'ha premut l'opció FAVOURITES"); }
    public void opinionsBtn()
    {
        Log.d("Profile", "S'ha premut l'opció OPINIONS");
    }

    public void registerUser(String user, String password1, String password2, String email, String phone, RegisterView extra){
        if (!password1.equals(password2)){ //comprovem que les contrasenyes siguin iguals

            sendMessage("Las contraseñas no coinciden", extra);

        } else if(password1.length() < 5){

            sendMessage("La contraseña tiene que tener mínimo 5 caracteres", extra);

        } else {
            // Course API requires passwords in sha-256 in passlib format so:
            String p = password1;
            String salt = "16";
            String encodeHash = Utils.encode(p,salt,29000);
            System.out.println("PASSWORD_ENCRYPTED " + encodeHash);

            JsonObject user_json = new JsonObject();
            user_json.addProperty("username", user);
            user_json.addProperty("email", email);
            user_json.addProperty("phone", phone);
            user_json.addProperty("password", encodeHash);

            Call<Void> call = userService.registerUser3(user_json);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 200){
                        sendMessage("Usuario registrado", extra);
                    }else{
                        try { //Atrapar error usuari existent / correu existent
                            sendMessage(Objects.requireNonNull(response.errorBody().string()), extra);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    sendMessage("Error", extra);
                }
            });
        }
    }

    public void sendMessage(String message, RegisterView extra)
    {
        Toast.makeText(extra,message, Toast.LENGTH_SHORT).show(); //enviem missatge a la pantalla
    }
}

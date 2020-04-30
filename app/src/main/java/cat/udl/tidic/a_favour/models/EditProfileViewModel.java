package cat.udl.tidic.a_favour.models;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.JsonObject;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.Views.EditProfileView;
import cat.udl.tidic.a_favour.Views.LoadingPanel;
import cat.udl.tidic.a_favour.Views.MainPage;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.Views.RegisterView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileViewModel {
    private static final int PASSWORDLENGTH = 5;
    private Context c;
    private UserServices userService;



    public EditProfileViewModel(Context c){
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        this.c = c;
    }



    public void updateUser(String user, String password1, String password2, String email, String phone, EditProfileView editProfileView)
    {
        String message = null;
        //Primer comprovem que tot estigui ple
        if (email.equals("") || phone.equals("") || user.equals(""))
        {
            message = c.getString(R.string.errorfilled);
        } else {
            if (!email.contains("@")) {
                message = c.getString(R.string.validEmail);
            }
        }

        //En cas de que tot estigui ple...
        if (!password1.equals("") || !password2.equals("")) {
            if (!password1.equals("") && !password2.equals("")){

                if (password1.length() < PASSWORDLENGTH) {
                    message = c.getString(R.string.fivechar);
                }
                if (!password1.equals(password2)) {
                    message = c.getString(R.string.dontmach);
                }
                if (!password1.matches(".*\\d.*")) {
                    message = c.getString(R.string.containnumber);
                }
            } else {
                message = c.getString(R.string.errorfilled);
            }
        }

        //Si hi ha algut algun error, el printem per pantall i no es fa la crida
        if (message != null) { LoadingPanel.sendMessage(message);return; }

        Log.d("Es fa la crida del register", "...............");

        // Course API requires passwords in sha-256 in passlib format so:
        int iterations = RetrofitClientInstance.getIterations();
        String salt = RetrofitClientInstance.getSalt();
        String encodeHash = Utils.encode(password1,salt,iterations);
        System.out.println("PASSWORD_ENCRYPTED " + encodeHash);

        String token = PreferencesProvider.providePreferences().getString("token","");
        JsonObject user_json = new JsonObject();
        user_json.addProperty("username", user);
        user_json.addProperty("email", email);
        user_json.addProperty("phone", phone);
        user_json.addProperty("password", encodeHash);





        Call<Void> call = userService.editProfile(token,user_json);
        Log.d("tokenn",""+token);
        //noinspection NullableProblems
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                Log.d("Update_user", ""+response.code());
                if (response.code() == 200)
                {
                    editProfileView.openProfile();
                    LoadingPanel.sendMessage(c.getString(R.string.updateProfile));


                }
                else
                {
                    LoadingPanel.sendMessage(c.getString(R.string.errorUpdate));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                LoadingPanel.sendMessage(c.getString(R.string.errorUpdate));
            }
        });
    }
}

package cat.udl.tidic.a_favour.models;

import android.content.Context;
import android.util.Log;
import com.google.gson.JsonObject;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.RetrofitClientInstance;
import cat.udl.tidic.a_favour.UserServices;
import cat.udl.tidic.a_favour.Utils;
import cat.udl.tidic.a_favour.Views.LoadingPanel;
import cat.udl.tidic.a_favour.Views.RegisterView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel
{
    private static final int PASSWORDLENGTH = 5;
    private Context c;
    private UserServices userService;

    public RegisterViewModel(Context c)
    {
        userService = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
        this.c = c;
    }

    public void registerUser(String user, String password1, String password2, String email, String phone, RegisterView extra)
    {
        String message = null;
        //Primer comprovem que tot estigui ple
        if (password1.equals("") || password2.equals("") || email.equals("") || phone.equals("") || user.equals(""))
        {
            message = c.getString(R.string.errorfilled);
        }
        //En cas de que tot estigui ple...
        else {
            if (password1.length() < PASSWORDLENGTH) { message = c.getString(R.string.fivechar); }
            if (!password1.equals(password2)) { message = c.getString(R.string.dontmach); }
            if (!email.contains("@")) { message = c.getString(R.string.validEmail);}
        }

        //Si hi ha algut algun error, el printem per pantall i no es fa la crida
        if (message != null) { LoadingPanel.sendMessage(message);return; }

        Log.d("Es fa la crida del register", "...............");

            // Course API requires passwords in sha-256 in passlib format so:
            String salt = "16";
            String encodeHash = Utils.encode(password1,salt,29000);
            System.out.println("PASSWORD_ENCRYPTED " + encodeHash);

            JsonObject user_json = new JsonObject();
            user_json.addProperty("username", user);
            user_json.addProperty("email", email);
            user_json.addProperty("phone", phone);
            user_json.addProperty("password", encodeHash);

            Call<Void> call = userService.registerUser3(user_json);
                //noinspection NullableProblems
                call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if (response.code() == 200)
                    {
                        LoadingPanel.sendMessage(c.getString(R.string.Registergood));
                        extra.goToLogin(null);
                    }
                    else
                    {
                        LoadingPanel.sendMessage(c.getString(R.string.alredyExists));
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t)
                {
                    LoadingPanel.sendMessage(c.getString(R.string.errorRegister));
                }
            });
        }
}

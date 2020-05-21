package cat.udl.tidic.a_favour.viewmodels;


import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class MainActivityViewModel {


    public MainActivityViewModel() {
    }

    public Boolean isCurrentLogIn(){
        String token = PreferencesProvider.providePreferences().getString("token", "");
        return !token.equals("");
    }

}



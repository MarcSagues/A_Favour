package cat.udl.tidic.a_favour.view;

import android.os.Bundle;
import android.util.Log;

import cat.udl.tidic.a_favour.Views.CustomActivty;
import cat.udl.tidic.a_favour.Views.LoginView;
import cat.udl.tidic.a_favour.viewmodels.MainActivityViewModel;


public class MainActivity extends CustomActivty {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityViewModel mainActivityViewModel = new MainActivityViewModel();

        if (mainActivityViewModel.isCurrentLogIn()) {
            Log.d(TAG, "onCreate() -> El usuario ya tiene token, por lo tanto entro." );
            goTo(AllChatsView.class);
        }
        else {
            Log.d(TAG, "onCreate() -> El usuario no tiene token, por lo tanto ir a login." );
            goTo(AllChatsView.class);
        }
    }

}
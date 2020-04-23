package cat.udl.tidic.a_favour.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import cat.udl.tidic.a_favour.R;

public class EditProfileView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_view);
    }

    public void backArrowAction(View v)
    {
        onBackPressed();
    }

}

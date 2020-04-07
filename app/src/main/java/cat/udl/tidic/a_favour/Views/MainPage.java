package cat.udl.tidic.a_favour.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.tidic.a_favour.R;

public class MainPage  extends AppCompatActivity
{
    private Button goToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        getAllActivityData();
        addListeners();

    }
    private void getAllActivityData()
    {
        goToProfile = findViewById(R.id.goProfile);

    }
    private void addListeners()
    {
        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfile();
            }
        });
    }

    void goToProfile()
    {
        Intent intent = new Intent (this, ProfileView.class);
        startActivityForResult(intent, 0);
    }

}

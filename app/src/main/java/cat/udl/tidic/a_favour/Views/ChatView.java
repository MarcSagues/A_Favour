package cat.udl.tidic.a_favour.Views;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

public class ChatView extends AppCompatActivity
{
    public Button sendMessage;
    private EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xat);
        setOnClickListeners();
    }

    private void setOnClickListeners()
    {

    }

    public void backArrowAction(View v)
    {
        onBackPressed();
    }
}

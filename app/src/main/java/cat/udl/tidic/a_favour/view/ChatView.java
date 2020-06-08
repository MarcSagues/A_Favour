package cat.udl.tidic.a_favour.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Chat;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

public class ChatView extends AppCompatActivity
{
    public Button sendMessage;
    private EditText input;
    private Chat chat;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xat);
        getChat();
        setOnClickListeners();
    }

    private void getChat()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            chat = (Chat) getIntent().getSerializableExtra("chat");
            assert chat != null;
            Log.d("ChatView", chat.getLastMessage());
        }
    }

    private void setOnClickListeners()
    {

    }

    public void backArrowAction(View v)
    {
        onBackPressed();
    }
}

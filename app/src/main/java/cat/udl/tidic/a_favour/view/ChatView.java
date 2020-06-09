package cat.udl.tidic.a_favour.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.adapters.FavourAdapter;
import cat.udl.tidic.a_favour.adapters.FavourDiffCallback;
import cat.udl.tidic.a_favour.adapters.MessageDiffCallback;
import cat.udl.tidic.a_favour.adapters.MessageListAdapter;
import cat.udl.tidic.a_favour.models.Chat;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.Message;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

public class ChatView extends AppCompatActivity
{
    public Button sendMessage;
    private EditText input;
    private RecyclerView favourRV;
    private Button sendButton;
    private FavourAdapter favourAdapter;
    private RecyclerView messagesRV;
    private MessageListAdapter messageListAdapter;
    private Chat chat;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xat);
        getChat();
        getAllActivityData();
        setOnClickListeners();
        inflateFavour();
        infalteMessages();
    }

    private void getChat()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            chat = (Chat) getIntent().getSerializableExtra("chat");
            assert chat != null;
        }
    }

    private void getAllActivityData()
    {
        favourRV = findViewById(R.id.favour_rv);
        input = findViewById(R.id.txtMensaje);
        sendButton = findViewById(R.id.btnEnviar);
        messagesRV = findViewById(R.id.messages_rv);
    }

    private void setOnClickListeners()
    {

    }

    private void inflateFavour()
    {
        favourRV.setLayoutManager(new LinearLayoutManager(this));
        favourAdapter = new FavourAdapter(new FavourDiffCallback(), FavoursActivity.class);
        favourRV.setAdapter(favourAdapter);
        List<Favour> singleFavour= new ArrayList<Favour>();
        singleFavour.add(chat.getFavour());
        favourAdapter.submitList(singleFavour);
    }

    private void infalteMessages()
    {
        messagesRV.setLayoutManager(new LinearLayoutManager(this));
        messageListAdapter = new MessageListAdapter(new MessageDiffCallback());
        messagesRV.setAdapter(messageListAdapter);
        messageListAdapter.submitList(chat.getMessages());



    }


    public void backArrowAction(View v)
    {
        onBackPressed();
    }
}

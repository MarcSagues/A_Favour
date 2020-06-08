package cat.udl.tidic.a_favour.Views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.MessagesItemClickListener;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.adapters.MessageAdapter;
import cat.udl.tidic.a_favour.models.Chats;

public class MessagesView extends AppCompatActivity
{
    private ListView messageList;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getFirebaseInstance();
        getAllActivityData();
        setListAdapter();
        setListeners();
    }

    private void getFirebaseInstance()
    {

    }

    private void getAllActivityData()
    {
        messageList = findViewById(R.id.rv_recycler_view);
        backArrow = findViewById(R.id.back_arrow);
    }

    private void setListAdapter()
    {
        Chats[] allMessagesArray;
        if (FORTESTING.dev) {allMessagesArray = FORTESTING.getMessageList();}
        else { allMessagesArray = FORTESTING.getMessageList(); }

        MessageAdapter adapter_event = new MessageAdapter(this, R.layout.message_list, allMessagesArray);
        messageList.setAdapter(adapter_event);
        messageList.setOnItemClickListener(new MessagesItemClickListener(this));

    }

    void setListeners()
    {
        backArrow.setOnClickListener(v -> MessagesView.super.onBackPressed());
    }
}

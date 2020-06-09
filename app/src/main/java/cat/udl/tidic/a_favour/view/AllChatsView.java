package cat.udl.tidic.a_favour.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.MessagesItemClickListener;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.adapters.AllChatsAdapter;
import cat.udl.tidic.a_favour.models.AllChats;
import cat.udl.tidic.a_favour.models.Chat;

public class AllChatsView extends AppCompatActivity
{
    private ListView messageList;
    private ImageView backArrow;
    private AllChats allChats;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getAllActivityData();
        getAllChats();
        setChatsListAdapter();
        setListeners();
    }

    private void getAllChats()
    {
        //if (FORTESTING.dev)
        //{
            allChats= new AllChats(FORTESTING.getChats());
        //}
    }


    private void getAllActivityData()
    {
        messageList = findViewById(R.id.rv_recycler_view);
        backArrow = findViewById(R.id.back_arrow);
    }

    private void setChatsListAdapter()
    {
        Chat[] arrayChats = new Chat[allChats.getallChats().size()];
        arrayChats = allChats.getallChats().toArray(arrayChats);

        AllChatsAdapter adapter_event = new AllChatsAdapter(this, R.layout.message_list, arrayChats);
        messageList.setAdapter(adapter_event);
        messageList.setOnItemClickListener(new MessagesItemClickListener(this));

    }

    void setListeners()
    {
        backArrow.setOnClickListener(v -> AllChatsView.super.onBackPressed());
    }
}

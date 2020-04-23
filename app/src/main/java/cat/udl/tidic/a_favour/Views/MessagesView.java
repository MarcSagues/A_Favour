package cat.udl.tidic.a_favour.Views;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;

public class MessagesView extends AppCompatActivity
{
    private ListView messageList;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getAllActivityData();
        setListAdapter();
        setListeners();
    }

    private void getAllActivityData()
    {
        messageList = findViewById(R.id.rv_recycler_view);
        backArrow = findViewById(R.id.back_arrow);
    }

    private void setListAdapter()
    {
        DataModel.Message[] allMessagesArray;
        if (FORTESTING.dev) {allMessagesArray = FORTESTING.getMessageList();}
        //Insertat dintre l'else la crida a l'API
        else { allMessagesArray = FORTESTING.getMessageList(); }

        DrawerItemCustomAdapter adapter_event = new DrawerItemCustomAdapter(this, R.layout.message_list, allMessagesArray);
        messageList.setAdapter(adapter_event);
    }

    void setListeners()
    {
        backArrow.setOnClickListener(v -> MessagesView.super.onBackPressed());
    }
}

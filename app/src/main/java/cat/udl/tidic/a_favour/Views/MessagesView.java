package cat.udl.tidic.a_favour.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.OpinionsAdapter;
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
        DataModel.Message[] allMessagesArray;
        if (FORTESTING.dev) {allMessagesArray = FORTESTING.getMessageList();}
        //Insertat dintre l'else la crida a l'API
        else { allMessagesArray = FORTESTING.getMessageList(); }

        //OpinionsAdapter adapter_event = new OpinionsAdapter(this, R.layout.message_list, allMessagesArray);
        //messageList.setAdapter(adapter_event);
        messageList.setOnItemClickListener(new DrawerItemClickListener(this));

    }

    void setListeners()
    {
        backArrow.setOnClickListener(v -> MessagesView.super.onBackPressed());
    }
}

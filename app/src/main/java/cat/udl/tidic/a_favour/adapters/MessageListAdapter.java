package cat.udl.tidic.a_favour.adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Message;



public class MessageListAdapter extends ListAdapter<Message, MessageListAdapter.MessageHolder> {

    private final static String TAG ="FavourAdapter";
    private Message currentLocation;
    private MessageCommonHolder messageCommonHolder;


    public MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback)
    {
        super(diffCallback);
    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message1,
                null, false);
        return new MessageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position)
    {
        final Message currentFavour = (Message) getItem(position);
        Log.d(TAG, "onBindViewHolder() -> currentFavour: " + currentFavour);
        //messageCommonHolder.bindHolder(currentFavour, currentLocation);
        //messageCommonHolder.setFavour(currentFavour);
    }

    class MessageHolder extends RecyclerView.ViewHolder
    {
        public MessageHolder(@NonNull View itemView)
        {
            super(itemView);
            messageCommonHolder = new MessageCommonHolder(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                }
            });
        }
    }


}

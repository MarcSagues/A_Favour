package cat.udl.tidic.a_favour.adapters;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.AnunciView;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.Views.UploadFavour;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.Message;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.models.UserModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import cat.udl.tidic.a_favour.view.FavoursActivity;
import cat.udl.tidic.a_favour.view.FavoursListActivity;

import static androidx.core.content.ContextCompat.startActivity;


public class MessageListAdapter extends ListAdapter<Message, MessageListAdapter.MessageHolder> {

    private final static String TAG ="MessageListAdapter";
    private MessageCommonHolder messageCommonHolder;
    private List<Message> listMensaje = new ArrayList<>();
    private Context c;

    public MessageListAdapter(@NonNull DiffUtil.ItemCallback<Message> diffCallback)
    {
        super(diffCallback);
    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mensajes_receptor,
                null, false);
        return new MessageHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull MessageListAdapter.MessageHolder holder, int position) {
        final Message currentFavour = (Message) getItem(position);
        Log.d(TAG, "onBindViewHolder() -> currentFavour: " + currentFavour);
        messageCommonHolder.bindHolder(currentFavour);

    }


    class MessageHolder extends RecyclerView.ViewHolder
    {
        public MessageHolder(@NonNull View itemView) {
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

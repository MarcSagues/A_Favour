package cat.udl.tidic.a_favour.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cat.udl.tidic.a_favour.ImageHelper;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Chat;

public class MessageAdapter extends ArrayAdapter<Chat>
{
    private Context mContext;
    private int layoutResourceId;
    private Chat[] data;


    public Chat[] getData()
    {
        return this.data;
    }

    public void setData(Chat[] data)
    {
        this.data = data;
    }
    public MessageAdapter(Context mContext, int layoutResourceId, Chat[] data)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItem;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);
        inflateMessagePreview(listItem,position);
        return listItem;
    }

    private void inflateMessagePreview(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.userImage);
        TextView otherUsername = listItem.findViewById(R.id.otherUserName);
        TextView titleofFavour = listItem.findViewById(R.id.favourTitle);
        TextView lastMessage = listItem.findViewById(R.id.lastMessage);
        TextView lastMessageDate = listItem.findViewById(R.id.dateLastMessage);

        Chat folder = (Chat) data[position];
        imageViewIcon.setImageResource(R.drawable.example_person);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        otherUsername.setText(folder.other_user.getUsername());
        titleofFavour.setText(folder.favour.getName());
        lastMessage.setText(folder.getLastMessage());
        lastMessageDate.setText(folder.getLastMessageDate());
    }
}
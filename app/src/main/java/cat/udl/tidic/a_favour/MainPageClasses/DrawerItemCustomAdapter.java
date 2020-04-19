package cat.udl.tidic.a_favour.MainPageClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cat.udl.tidic.a_favour.ImageHelper;
import cat.udl.tidic.a_favour.R;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    private Context mContext;
    private int layoutResourceId;
    private DataModel[] data;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data) {
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
        setLayoutMenu(position);
        listItem = inflater.inflate(layoutResourceId, parent, false);

        if (data instanceof DataModel.Favour[])
        {
            inflateFavour(listItem,position);
        }
        else if (data instanceof DataModel.MenuList[])
        {
            inflateMenuList(listItem,position);
        }
        else if (data instanceof  DataModel.Message[])
        {
            inflateMessagePreview(listItem,position);
        }
        else
        {
            inflateOpinions(listItem,position);
        }

        return listItem;
    }

    private void inflateMessagePreview(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.userImage);
        TextView otherUsername = listItem.findViewById(R.id.otherUserName);
        TextView titleofFavour = listItem.findViewById(R.id.favourTitle);
        TextView lastMessage = listItem.findViewById(R.id.lastMessage);
        TextView lastMessageDate = listItem.findViewById(R.id.dateLastMessage);

        DataModel.Message folder = (DataModel.Message) data[position];
        imageViewIcon.setImageResource(R.drawable.example_person);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        otherUsername.setText(folder.otherUsername);
        titleofFavour.setText(folder.favourName);
        lastMessage.setText(folder.lastMessage);
        lastMessageDate.setText(folder.lastMessageDate);
    }

    private void inflateFavour(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        TextView amount = listItem.findViewById(R.id.tv_amount);

        DataModel.Favour folder = (DataModel.Favour) data[position];

        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        if(textViewDesc !=null){textViewDesc.setText(folder.description);}
        amount.setText(String.format("%s", folder.getAmount()));
    }

    private void inflateMenuList(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        DataModel.MenuList folder = (DataModel.MenuList) data[position];
        if(imageViewIcon!=null)
        {
            imageViewIcon.setImageResource(folder.icon);
            imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));
        }
        textViewName.setText(folder.name);
    }

    private void inflateOpinions(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        RatingBar stars = listItem.findViewById(R.id.stars);

        DataModel.Opinion folder = (DataModel.Opinion) data[position];

        imageViewIcon.setImageResource(folder.icon);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        textViewName.setText(folder.name);
        if(!folder.description.equals("")){textViewDesc.setText(folder.description);}
        stars.setRating(folder.starRating);
    }

    private void setLayoutMenu(int position)
    {
        if(position == 0 && data instanceof DataModel.MenuList[])
        {
            layoutResourceId = R.layout.list_view_item_profile;
        }
        else if (position != 0 && data instanceof DataModel.MenuList[])
        {
            layoutResourceId = R.layout.list_view_item_row ;
        }
    }
}
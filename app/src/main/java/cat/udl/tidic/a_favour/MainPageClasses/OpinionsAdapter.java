package cat.udl.tidic.a_favour.MainPageClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.util.Log;
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
import cat.udl.tidic.a_favour.Views.MainPage;
import cat.udl.tidic.a_favour.models.Opinions;

public class OpinionsAdapter extends ArrayAdapter<Opinions>
{
    private Context mContext;
    private int layoutResourceId;
    private Opinions[] data;
    private int nextnilst;
    private MainPage mainPage;


    public Opinions[] getData()
    {
        return this.data;
    }

    public void setData(Opinions[] data)
    {
        this.data = data;
    }
    public OpinionsAdapter(Context mContext, int layoutResourceId, Opinions[] data)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    public OpinionsAdapter(Context mContext, int layoutResourceId, Opinions[] data, MainPage mainPage)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.nextnilst = 0;
        this.mainPage = mainPage;
    }

    public void setData(Context mContext, int layoutResourceId, Opinions[] data, MainPage mainPage)
    {
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.nextnilst = 0;
        this.mainPage = mainPage;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItem;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);
        inflateOpinions(listItem,position);
        return listItem;
    }

    private void inflateMessagePreview(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.userImage);
        TextView otherUsername = listItem.findViewById(R.id.otherUserName);
        TextView titleofFavour = listItem.findViewById(R.id.favourTitle);
        TextView lastMessage = listItem.findViewById(R.id.lastMessage);
        TextView lastMessageDate = listItem.findViewById(R.id.dateLastMessage);

        //DataModel.Message folder = (DataModel.Message) data[position];
        imageViewIcon.setImageResource(R.drawable.example_person);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        //otherUsername.setText(folder.otherUsername);
        //titleofFavour.setText(folder.favourName);
        //lastMessage.setText(folder.lastMessage);
        //lastMessageDate.setText(folder.lastMessageDate);
    }


    private void inflateOpinions(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        RatingBar stars = listItem.findViewById(R.id.stars);

        Opinions folder = (Opinions) data[position];

        imageViewIcon.setImageResource(folder.icon);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        textViewName.setText(folder.getName());
        if(!folder.getDescription().equals("")){textViewDesc.setText(folder.getDescription());}
        stars.setRating(folder.getStarRating());
    }
}
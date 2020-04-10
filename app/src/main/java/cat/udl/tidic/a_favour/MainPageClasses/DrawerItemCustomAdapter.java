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
        else
        {
            inflateOpinions(listItem,position);
        }

        return listItem;
    }

    void inflateFavour(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        TextView amount = listItem.findViewById(R.id.tv_amount);

        DataModel.Favour folder = (DataModel.Favour) data[position];

        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        textViewDesc.setText(folder.description);
        amount.setText("" + folder.getAmount());
    }

    void inflateMenuList(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        DataModel.MenuList folder = (DataModel.MenuList) data[position];
        if(imageViewIcon!=null)imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
    }

    void inflateOpinions(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        RatingBar stars = listItem.findViewById(R.id.stars);

        DataModel.Opinion folder = (DataModel.Opinion) data[position];

        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        textViewDesc.setText(folder.description);
        stars.setRating(folder.starRating);
    }

    void setLayoutMenu(int position)
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
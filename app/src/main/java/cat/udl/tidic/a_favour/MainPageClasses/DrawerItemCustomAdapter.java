package cat.udl.tidic.a_favour.MainPageClasses;

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

import cat.udl.tidic.a_favour.R;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    private Context mContext;
    private int layoutResourceId;
    private DataModel[] data;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;

    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        View listItem;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);


        ImageView imageViewIcon =  listItem.findViewById(R.id.iv_image);
        TextView textViewName =  listItem.findViewById(R.id.tv_text);
        TextView textViewDesc =  listItem.findViewById(R.id.desc);
        TextView amount =  listItem.findViewById(R.id.tv_amount);

        DataModel folder = data[position];

        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        if (textViewDesc != null) { textViewDesc.setText(folder.desc); }
        if (amount != null)
        {
            if (folder.cat != DataModel.CATEGORIES.favorxfavour)
            {
                if (folder.amount != (int) folder.amount)
                {
                    amount.setText("" + folder.amount + "€");
                } else
                    {
                    amount.setText("" + (int) folder.amount + "€");
                }
            }
            else
            {
                amount.setText(R.string.favourxfavour);
            }
        }

        return listItem;
    }
}
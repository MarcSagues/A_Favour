package cat.udl.tidic.a_favour.MainPageClasses;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import cat.udl.tidic.a_favour.R;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    Context mContext;
    int layoutResourceId;
    DataModel data[] = null;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        if (data != null) { this.data = data;}

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);


        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.iv_image);
        TextView textViewName = (TextView) listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = (TextView) listItem.findViewById(R.id.desc);
        TextView amount = (TextView) listItem.findViewById(R.id.tv_amount);

        DataModel folder = data[position];

        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);
        if (textViewDesc != null) { textViewDesc.setText(folder.desc); }
        if (amount != null)
        {
            if(folder.amount != (int) folder.amount) {amount.setText("" + folder.amount + "€");}
            else {amount.setText(""+(int)folder.amount + "€"); }
        }

        return listItem;
    }
}
package cat.udl.tidic.a_favour.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;

public class FavourTypeSpinnerAdapter extends ArrayAdapter<FavourTypeEnum> {

    private final String TAG ="FavourTypeSpinnerAdapter";

    Context mContext;
    int mLayoutResourceId;
    FavourTypeEnum[] mItems;

    public FavourTypeSpinnerAdapter(Context context, int layoutResourceId,
                                    FavourTypeEnum[] data) {
        super(context, layoutResourceId, data);
        this.mLayoutResourceId = layoutResourceId;
        this.mItems = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;

        if (row == null) {

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            holder = new Holder();
            holder.favourTypeColour = row.findViewById(R.id.favourTypeColour);
            holder.favourTypeName = row.findViewById(R.id.favourCategoryName);

            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        FavourTypeEnum item = mItems[position];

        int colorResource = FavourTypeEnum.getColourResource(item);

        holder.favourTypeColour.setBackgroundColor(
                ContextCompat.getColor(mContext,colorResource));
        holder.favourTypeName.setText(item.getName());
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class Holder {
        TextView favourTypeColour;
        TextView favourTypeName;
    }

}

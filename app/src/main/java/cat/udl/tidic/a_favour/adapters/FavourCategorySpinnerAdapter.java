package cat.udl.tidic.a_favour.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;

public class FavourCategorySpinnerAdapter extends ArrayAdapter<CategoryEnum> {

    private final String TAG ="FavourTypeSpinnerAdapter";

    Context mContext;
    int mLayoutResourceId;
    CategoryEnum[] mItems;

    public FavourCategorySpinnerAdapter(Context context, int layoutResourceId,
                                        CategoryEnum[] data) {
        super(context, layoutResourceId, data);
        this.mLayoutResourceId = layoutResourceId;
        this.mItems = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;

        //Log.d(TAG, "Row: " + row);
        if (row == null) {

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
            holder = new Holder();
            holder.favourIconCategory = row.findViewById(R.id.favourCategoryIcon);
            holder.favourNameCategory = row.findViewById(R.id.favourCategoryName);

            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        CategoryEnum item = mItems[position];

       // Log.d(TAG, "Item: " + item);


        int imgResoruce = CategoryEnum.getIcon(item);
            holder.favourIconCategory.setImageResource(
                    imgResoruce);

        holder.favourNameCategory.setText(item.getName());
        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class Holder {
        ImageView favourIconCategory;
        TextView favourNameCategory;
    }

}

package cat.udl.tidic.a_favour;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import cat.udl.tidic.a_favour.Views.ConfigurationView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;
import androidx.annotation.NonNull;


public class LenguagesArrayAdapter extends ArrayAdapter<String> {

    private List<String> items;
    private int tv;
    private ConfigurationView cv;
    private Boolean firstTime = true;


    public LenguagesArrayAdapter(ConfigurationView cv, Context context, int layout, int tv, List<String> items)
    {
        super(context, layout,tv, items);
        // Your sent context
        this.items = items;
        this.tv = tv;
        this.cv = cv;
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public String getItem(int position){
        return items.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @androidx.annotation.NonNull ViewGroup parent)
    {
        View v = super.getView(position, convertView, parent);
        TextView a = v.findViewById(R.id.lenguage);
        ImageView iv = v.findViewById(R.id.lengauge_icon);
        a.setTextColor(Color.BLACK);

            SharedPreferences shared = PreferencesProvider.providePreferences();
            String lenguage = shared.getString("lenguage","en");
            Log.d("The lengauge on shared is", lenguage);
            a.setText(cv.getLengugeName(lenguage));
            iv.setImageResource(LenguageManager.getLenguageImage(lenguage));

            //a.setText(items.get(position));
            //iv.setImageResource(LenguageManager.getLenguageImage(position));
            Log.d("The actual value of position is", String.valueOf(position));

            if(!firstTime) {
                cv.changeLengauge(position);
            }
        //}
        firstTime = false;
        return v;

    }

    @Override
    public View getDropDownView(int position, View convertView, @androidx.annotation.NonNull ViewGroup parent)
    {
        View v = super.getDropDownView(position, convertView, parent);
        TextView a = v.findViewById(tv);
        a.setTextColor(Color.BLACK);
        a.setText(items.get(position));
        ImageView iv = v.findViewById(R.id.lengauge_icon);
        iv.setImageResource(LenguageManager.getLenguageImage(position));
        return v;
    }
}
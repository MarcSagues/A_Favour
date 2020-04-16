package cat.udl.tidic.a_favour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.Views.ConfigurationView;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class LenguagesArrayAdapter extends ArrayAdapter<String> {

    private List<String> items;
    // Your sent context
    private Context context;
    private int layout;
    private int tv;
    ConfigurationView cv;
    Boolean firstTime = true;


    public LenguagesArrayAdapter(ConfigurationView cv, Context context, int layout, int tv, List<String> items) {
        super(context, layout,tv, items);
        this.context = context;
        this.items = items;
        this.layout = layout;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = super.getView(position, convertView, parent);
        TextView a = v.findViewById(tv);
        ImageView iv = v.findViewById(R.id.lengauge_icon);
        a.setTextColor(Color.BLACK);

        if(firstTime)
        {
            SharedPreferences shared = PreferencesProvider.providePreferences();
            String lenguage = shared.getString("lenguage","en");
            Log.d("The lengauge on shared is", lenguage);
            a.setText(LenguageManager.getLengugeName(lenguage));
            iv.setImageResource(LenguageManager.getLenguageImage(lenguage));
            firstTime = false;
        }
        else {

            a.setText(items.get(position));
            iv.setImageResource(LenguageManager.getLenguageImage(position));
            Log.d("The actual value of position is", String.valueOf(position));
            cv.changeLengauge(position);
        }

        return v;

    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
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
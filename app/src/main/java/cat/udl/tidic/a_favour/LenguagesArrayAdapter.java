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

import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

public class LenguagesArrayAdapter extends ArrayAdapter<String> {

    private List<String> items;
    // Your sent context
    private Context context;
    private int layout;
    private int tv;
    private Boolean firstTime = true;
    String CURRENT_L;


    public LenguagesArrayAdapter(Context context, int layout, int tv, List<String> items) {
        super(context, layout,tv, items);
        this.context = context;
        this.items = items;
        this.layout = layout;
        this.tv = tv;
    }


    public String getCurrentLenguageSeleted()
    {
        return CURRENT_L;
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

       if (firstTime)
       {
           firstTime = false;
           SharedPreferences shared = PreferencesProvider.providePreferences();
           String currentLenguage = shared.getString("lenguage","English");
           a.setText(currentLenguage);
           iv.setImageResource(LenguageManager.getLenguageImagebyString(currentLenguage));
       }
       else
       {
           a.setText(items.get(position));
           iv.setImageResource(LenguageManager.getLenguageImage(position));
           this.CURRENT_L = items.get(position);
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
package cat.udl.tidic.a_favour.ProfileClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import cat.udl.tidic.a_favour.R;

public class RecyclerViewManager extends FragmentPagerAdapter
{

    private String[] tabTitles = new String[] { "Favours", "Favourites", "Opinions" };
    private Context context;

    public RecyclerViewManager(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {

        switch (position) {
            case 0:
                //Opinions
                return new BlankFragment(0);
            case 1:
                //Favourites
                return new BlankFragment(1);
            case 2:
                //Favours
                return new BlankFragment(2);
        }

        return null;
    }

    public View setTabTittles(int position)
    {
        @SuppressLint("InflateParams") View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = tab.findViewById(R.id.custom_text);
        tv.setText(tabTitles[position]);
        return tab;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }



    //public void addTab() { }



}
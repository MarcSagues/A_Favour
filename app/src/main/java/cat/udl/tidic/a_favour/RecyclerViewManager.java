package cat.udl.tidic.a_favour;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import cat.udl.tidic.a_favour.Views.ProfileView;

public class RecyclerViewManager extends FragmentPagerAdapter
{

    String tabTitles[] = new String[] { "Favours", "Favourites", "Opinions" };
    Context context;

    public RecyclerViewManager(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

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
        View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) tab.findViewById(R.id.custom_text);
        tv.setText(tabTitles[position]);
        return tab;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }



    public void addTab()
    {

    }



}
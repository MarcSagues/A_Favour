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

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.Favour;

public class RecyclerViewManager extends FragmentPagerAdapter
{

    private String[] tabTitles;
    private Context context;
    private Boolean myprofile;
    private Favour[] my_favours;
    private Favour[] favourites;
    private DataModel.Opinion[] opinions;

    @SuppressWarnings("deprecation")
    public RecyclerViewManager(FragmentManager fm, Context context,
                               Boolean myprofile,
                               Favour[] my_favours,
                               Favour[] favourites,
                               DataModel.Opinion[] opinions)
    {
        super(fm);
        this.context = context;
        this.myprofile = myprofile;
        this.my_favours = my_favours;
        if (favourites != null){this.favourites = favourites;}
        this.opinions = opinions;

        if (myprofile)
        {
            tabTitles = new String[]{context.getResources().getString(R.string.favours),
                    context.getResources().getString(R.string.favourites),
                    context.getResources().getString(R.string.opinions)};
        }
        else
        {
            tabTitles = new String[]{context.getResources().getString(R.string.favours),
                    context.getResources().getString(R.string.opinions)};
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {

        switch (position) {
            case 0:
                //Opinions
                return new BlankFragment(0,context, myprofile,  my_favours, favourites, opinions );
            case 1:
                //Favourites
                return new BlankFragment(1, context, myprofile,my_favours, favourites, opinions);
            case 2:
                //Favours
                return new BlankFragment(2, context, myprofile,my_favours, favourites, opinions);
        }

        return new BlankFragment(0,context,myprofile,my_favours,favourites, opinions);
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

}
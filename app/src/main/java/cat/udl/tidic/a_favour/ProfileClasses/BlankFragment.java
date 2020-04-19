package cat.udl.tidic.a_favour.ProfileClasses;

import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment
{

    private ProfileViewModel pView;
    public int id;
    private Context c;
    private Boolean myprofile;
    private int[] layout_listPROFILE = new int[]{R.layout.favours_list, R.layout.favours_list,R.layout.opinions_list};
    private int[] layout_listOTHER = new int[]{R.layout.favours_list,R.layout.opinions_list};



    BlankFragment(int id, Context c, Boolean myprofile)
    {
        this.myprofile = myprofile;
        pView = new ProfileViewModel();
        this.id = id;
        this.c = c;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        ListView rv = rootView.findViewById(R.id.rv_recycler_view);
        DrawerItemCustomAdapter adapter;
        if (this.myprofile)
        {
            if (id ==0 || id == 1){rv.setOnItemClickListener(new DrawerItemClickListener(c));}
            ProfileViewModel.LISTOFTYPE type = ProfileViewModel.LISTOFTYPE.values()[id];
            adapter = new DrawerItemCustomAdapter(getContext(), layout_listPROFILE[id],pView.getListOf(type, this.myprofile));
        }
        else
        {
            ProfileViewModel.LISTOFTYPE type;
            if (id == 0)
            {
                type = ProfileViewModel.LISTOFTYPE.Favours;
                rv.setOnItemClickListener(new DrawerItemClickListener(c));
            }
            else {type = ProfileViewModel.LISTOFTYPE.Opinions; }

            adapter = new DrawerItemCustomAdapter(getContext(), layout_listOTHER[id],pView.getListOf(type, this.myprofile));
        }

        rv.setAdapter(adapter);
        return rootView;
    }

}

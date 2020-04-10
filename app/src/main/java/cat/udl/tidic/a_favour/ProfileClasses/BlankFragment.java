package cat.udl.tidic.a_favour.ProfileClasses;
import cat.udl.tidic.a_favour.FORTESTING;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BlankFragment extends Fragment
{

    private ProfileViewModel pView;
    public int id;
    private Context c;
    BlankFragment(int id, Context c)
    {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        ListView rv = rootView.findViewById(R.id.rv_recycler_view);
        rv.setOnItemClickListener(new DrawerItemClickListener(null));
        DrawerItemCustomAdapter adapter;


        String[] titles = pView.getTitles();
        String[] desc = pView.getDesc();
        ImageView[] images = pView.getImage();
        float[] amount = pView.getAmount();

        DataModel.Favour eventList[] = FORTESTING.getExampleList();
        DataModel.Opinion eventList_op[] = FORTESTING.getExampleListOPINION();

        if (id == 0 || id == 1)
        {
            adapter = new DrawerItemCustomAdapter(getContext(), R.layout.favours_list, eventList);
            rv.setOnItemClickListener(new DrawerItemClickListener(c));
        }
        else {adapter = new DrawerItemCustomAdapter(getContext(), R.layout.opinions_list, eventList_op);}

        //Opinions
        rv.setAdapter(adapter);
        return rootView;
    }
}

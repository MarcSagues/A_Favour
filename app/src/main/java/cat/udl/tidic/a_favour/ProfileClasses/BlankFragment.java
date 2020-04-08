package cat.udl.tidic.a_favour.ProfileClasses;
import cat.udl.tidic.a_favour.ProfileClasses.MyAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;;import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class BlankFragment extends Fragment
{

    public ProfileViewModel pView;
    public int id;
    public BlankFragment(int id)
    {
        pView = new ProfileViewModel();
        this.id = id;
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
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        //rv.setHasFixedSize(true);

        MyAdapter adapter;

        //Favours or Favourites
        if (id == 0 || id == 1)
        {
            String[] titles = pView.getTitles(MyAdapter.OPTION.Favours);
            String[] desc = pView.getDesc(MyAdapter.OPTION.Favours);
            ImageView[] images = pView.getImage(MyAdapter.OPTION.Favours);
            float[] amount = pView.getAmount(MyAdapter.OPTION.Favours);
             adapter = new MyAdapter(MyAdapter.OPTION.Favours,titles, desc, images,amount);
        }
        //Opinions
        else
        {
            String[] titles = pView.getTitles(MyAdapter.OPTION.Opinions);
            String[] desc = pView.getDesc(MyAdapter.OPTION.Opinions);
            ImageView[] images = pView.getImage(MyAdapter.OPTION.Opinions);
            float[] stars = pView.getOpinionStars();
             adapter = new MyAdapter(MyAdapter.OPTION.Opinions,titles, desc, images, stars);
        }
        rv.setAdapter(adapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        return rootView;
    }
}

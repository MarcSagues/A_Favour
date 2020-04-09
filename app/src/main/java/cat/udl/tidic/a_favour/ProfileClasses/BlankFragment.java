package cat.udl.tidic.a_favour.ProfileClasses;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.models.ProfileViewModel;

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
    BlankFragment(int id)
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
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        ListView rv = rootView.findViewById(R.id.rv_recycler_view);
        rv.setOnItemClickListener(new DrawerItemClickListener(null));
        DrawerItemCustomAdapter adapter;


            String[] titles = pView.getTitles();
            String[] desc = pView.getDesc();
            ImageView[] images = pView.getImage();
            float[] amount = pView.getAmount();
        DataModel[] eventList = new DataModel[5];
        eventList[0] = new DataModel(true,R.drawable.handshacke, "Necessito ajuda per pujar la compra a casa",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc molestie vitae dolor id faucibus. Fusce eu venenatis risus. Fusce malesuada, ipsum at hendrerit dignissim, mauris ligula accumsan elit, eget facilisis diam quam eget sapien. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam mollis varius velit, vel ornare purus imperdiet et. Sed tempus turpis sed ex pellentesque, id dignissim mauris condimentum. Ut posuere quam quis nisi vestibulum semper. Vestibulum nec aliquet metus. Quisque sit amet velit in lorem bibendum vestibulum a et lectus."
                ,25, DataModel.CATEGORIES.favorxfavour);
        eventList[1] = new DataModel(true,android.R.drawable.ic_menu_agenda, "Test2","test description 2",2.5f, DataModel.CATEGORIES.computing);
        eventList[2] = eventList[1];
        eventList[3] = eventList[0];
        eventList[4] = eventList[1];
        if (id == 0 || id == 1) {
            adapter = new DrawerItemCustomAdapter(getContext(), R.layout.favours_list, eventList);
        }
        else {adapter = new DrawerItemCustomAdapter(getContext(), R.layout.opinions_list, eventList);}

        //Opinions
        rv.setAdapter(adapter);
        return rootView;
    }
}

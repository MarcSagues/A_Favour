package cat.udl.tidic.a_favour.ProfileClasses;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemClickListener;
import cat.udl.tidic.a_favour.MainPageClasses.DrawerItemCustomAdapter;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.adapters.FavourAdapter;
import cat.udl.tidic.a_favour.adapters.FavourDiffCallback;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class BlankFragment extends Fragment
{

    private ProfileViewModel pView;
    public int id;
    private Context c;
    private Boolean myprofile;
    //Les seguents arrays defineixen el layout que anira a cada pestaña del profile
    //private int[] layout_listPROFILE = new int[]{R.layout.favours_list, R.layout.favours_list,R.layout.opinions_list};
    private int[] layout_listOTHER = new int[]{R.layout.favours_list,R.layout.opinions_list};
    private Favour[] favours;
    private Favour[] favourites;
    private DataModel.Opinion[] opinions;

    BlankFragment(int id, Context c, Boolean myprofile,
                  Favour[] favours,
                  Favour[] favourites,
                  DataModel.Opinion[] opinions)
    {
        this.myprofile = myprofile;
        pView = new ProfileViewModel(c);
        this.id = id;
        this.c = c;
        this.favours = favours;
        if (favourites != null){this.favourites = favourites;}
        this.opinions = opinions;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView;
        ListView lv = null;
        RecyclerView rv = null;
        if (this.myprofile && id == 2 || !this.myprofile && id == 1)
        {
            rootView = inflater.inflate(R.layout.fragment_blank2, container, false);
             lv = rootView.findViewById(R.id.rv_recycler_view);
        }
        else
        {
            rootView = inflater.inflate(R.layout.fragment_blank, container, false);
            rv = rootView.findViewById(R.id.rv_recycler_view);
        }


        DrawerItemCustomAdapter adapter;

        //Aquesta booleana determina si s'ha de carregar el meu perfil o és el perfil d'un altre usuari
        if (this.myprofile)
        {
            //El id es refereix a la pestaña que estem infalnt
            //(En el teu profile hi ha 3 pestañes : Favours, Favourites i Opinions)
            //(En el perfil públic d'un usuari n'hi han 2 --> Favours i Opinions)

            if (id == 0)
            {
                rv.setLayoutManager(new LinearLayoutManager(c));
                FavourAdapter adapter_favour = new FavourAdapter(new FavourDiffCallback(), ProfileView.class);
                adapter_favour.submitList(Arrays.asList(favours));
                rv.setAdapter(adapter_favour);
            }
            else if (id == 1)
            {
                rv.setLayoutManager(new LinearLayoutManager(c));
                FavourAdapter adapter_favour = new FavourAdapter(new FavourDiffCallback(), ProfileView.class);
                adapter_favour.submitList(Arrays.asList(favourites));
                rv.setAdapter(adapter_favour);
            }
            else
                {
                adapter = new DrawerItemCustomAdapter(getContext(), R.layout.opinions_list, (opinions));
                lv.setAdapter(adapter);
            }
        }
        else
        {
            if (id == 0)
            {
                rv.setLayoutManager(new LinearLayoutManager(c));
                FavourAdapter adapter_favour = new FavourAdapter(new FavourDiffCallback(), ProfileViewModel.class);
                adapter_favour.submitList(Arrays.asList(favours));
                rv.setAdapter(adapter_favour);
            }
            else
            {
                adapter = new DrawerItemCustomAdapter(getContext(), R.layout.opinions_list, (opinions));
                lv.setAdapter(adapter);
            }
        }

        return rootView;
    }

}

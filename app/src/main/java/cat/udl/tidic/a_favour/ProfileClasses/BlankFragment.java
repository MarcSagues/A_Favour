package cat.udl.tidic.a_favour.ProfileClasses;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
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
    //Les seguents arrays defineixen el layout que anira a cada pestaña del profile
    //private int[] layout_listPROFILE = new int[]{R.layout.favours_list, R.layout.favours_list,R.layout.opinions_list};
    private int[] layout_listOTHER = new int[]{R.layout.favours_list,R.layout.opinions_list};
    private DataModel.Favour[] favours;
    private DataModel.Favour[] favourites;
    private DataModel.Opinion[] opinions;

    BlankFragment(int id, Context c, Boolean myprofile,
                  DataModel.Favour[] favours,
                  DataModel.Favour[] favourites,
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
        rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        ListView rv = rootView.findViewById(R.id.rv_recycler_view);
        DrawerItemCustomAdapter adapter;

        //Aquesta booleana determina si s'ha de carregar el meu perfil o és el perfil d'un altre usuari
        if (this.myprofile)
        {
            //El id es refereix a la pestaña que estem infalnt
            //(En el teu profile hi ha 3 pestañes : Favours, Favourites i Opinions)
            //(En el perfil públic d'un usuari n'hi han 2 --> Favours i Opinions)
            if (id ==0 || id == 1){rv.setOnItemClickListener(new DrawerItemClickListener(c));}

            if (id == 0) adapter = new DrawerItemCustomAdapter(getContext(), R.layout.favours_list, (favours));
            else if (id == 1) adapter = new DrawerItemCustomAdapter(getContext(), R.layout.favours_list, (favourites));
            else adapter = new DrawerItemCustomAdapter(getContext(), R.layout.opinions_list, (opinions));
        }
        else
        {
            if (id == 0) adapter = new DrawerItemCustomAdapter(getContext(), R.layout.favours_list, (favours));
            else adapter = new DrawerItemCustomAdapter(getContext(), R.layout.opinions_list, (opinions));
            rv.setOnItemClickListener(new DrawerItemClickListener(c));
        }

        rv.setAdapter(adapter);
        return rootView;
    }

}

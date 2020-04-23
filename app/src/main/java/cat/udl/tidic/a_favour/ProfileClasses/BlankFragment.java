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
    private int[] layout_listPROFILE = new int[]{R.layout.favours_list, R.layout.favours_list,R.layout.opinions_list};
    private int[] layout_listOTHER = new int[]{R.layout.favours_list,R.layout.opinions_list};
    private DataModel.Favour[] data;

    BlankFragment(int id, Context c, Boolean myprofile, DataModel[] data)
    {
        this.myprofile = myprofile;
        pView = new ProfileViewModel();
        this.id = id;
        this.c = c;
        this.data = (DataModel.Favour[]) data;
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
            adapter = new DrawerItemCustomAdapter(getContext(), R.layout.favours_list, (DataModel.Favour[]) data);
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

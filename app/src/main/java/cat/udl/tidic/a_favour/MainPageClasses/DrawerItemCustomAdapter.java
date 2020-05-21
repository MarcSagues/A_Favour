package cat.udl.tidic.a_favour.MainPageClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cat.udl.tidic.a_favour.ImageHelper;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.MainPage;

public class DrawerItemCustomAdapter extends ArrayAdapter<DataModel> {

    private Context mContext;
    private int layoutResourceId;
    private DataModel[] data;
    private int nextnilst;
    private MainPage mainPage;

    //Aquesta classe s'encarrega "d'inflar" les llistes segons l'array que li passem (DataModel[] data)
    //Exemple :
    // DataModel.Favour[] eventList = new DataModel.Favour[1]; --> Aixó és la nostre array
    //ListView list --> Aixó es la llista que inflarem;
    //Creem un nou adaptador amb el layout amb el que volem inflar la llista (R.layout.x) i li passem la llista
    //DrawerItemCustomAdapter adapter_event = new DrawerItemCustomAdapter(this, R.layout.favours_list, list);
    //Posem el adaptador a la llista
    //list.setAdapter(adapter_event)

    public DataModel[] getData()
    {
        return this.data;
    }

    public void setData(DataModel[] data)
    {
        this.data = data;
    }
    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, DataModel[] data, MainPage mainPage)
    {
        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.nextnilst = 0;
        this.mainPage = mainPage;
    }

    public void setData(Context mContext, int layoutResourceId, DataModel[] data, MainPage mainPage){
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
        this.nextnilst = 0;
        this.mainPage = mainPage;
    }

    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItem;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        setLayoutMenu(position);
        listItem = inflater.inflate(layoutResourceId, parent, false);
        //Depenent del tipus d'array que li passem, s'inflara la llista d'una forma o un altre

        //Si la llista que li passem és de favors...
        if (data instanceof DataModel.Favour[])
        {
            if (position == data.length-1)
            {
                //Aquesta variable s'utilitzara per a saber quantes crides a l'API s'han fet
                //nextnilst = 0 ? --> S'han carregat els primers 100 favors
                //nextnilst = 1 ? --> S'han carregat els favors del 100 al 200
                //etc...
                nextnilst+=1;
//                mainPage.LoadMore(nextnilst);
            }

            inflateFavour(listItem,position);
        }
        //Si la llista que li passem és del menú
        else if (data instanceof DataModel.MenuList[])
        {
            inflateMenuList(listItem,position);
        }
        //Si la llista que li passem és de missatges...
        else if (data instanceof  DataModel.Message[])
        {
            inflateMessagePreview(listItem,position);
        }
        //Si la llista que li passem és d'opinions
        else
        {
            inflateOpinions(listItem,position);
        }

        return listItem;
    }

    private void inflateMessagePreview(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.userImage);
        TextView otherUsername = listItem.findViewById(R.id.otherUserName);
        TextView titleofFavour = listItem.findViewById(R.id.favourTitle);
        TextView lastMessage = listItem.findViewById(R.id.lastMessage);
        TextView lastMessageDate = listItem.findViewById(R.id.dateLastMessage);

        DataModel.Message folder = (DataModel.Message) data[position];
        imageViewIcon.setImageResource(R.drawable.example_person);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        otherUsername.setText(folder.otherUsername);
        titleofFavour.setText(folder.favourName);
        lastMessage.setText(folder.lastMessage);
        lastMessageDate.setText(folder.lastMessageDate);
    }

    private void inflateFavour(View listItem, int position)
    {

        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        TextView amount = listItem.findViewById(R.id.favourCategoryName);

        DataModel.Favour folder = (DataModel.Favour) data[position];

        Log.d("DICA","folder: " + folder);
        if (folder != null) {
            imageViewIcon.setImageResource(folder.getIcon());
            textViewName.setText(folder.name);
            if (textViewDesc != null) {
                textViewDesc.setText(folder.description);
            }
            amount.setText(String.format("%s", folder.getAmount()));
        }
    }

    private void inflateMenuList(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        DataModel.MenuList folder = (DataModel.MenuList) data[position];
        if(imageViewIcon!=null)
        {
            imageViewIcon.setImageResource(folder.icon);
            imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));
        }
        textViewName.setText(folder.name);
    }

    private void inflateOpinions(View listItem, int position)
    {
        ImageView imageViewIcon = listItem.findViewById(R.id.iv_image);
        TextView textViewName = listItem.findViewById(R.id.tv_text);
        TextView textViewDesc = listItem.findViewById(R.id.desc);
        RatingBar stars = listItem.findViewById(R.id.stars);

        DataModel.Opinion folder = (DataModel.Opinion) data[position];

        imageViewIcon.setImageResource(folder.icon);
        imageViewIcon.setImageBitmap(ImageHelper.getRoundedCornerBitmap(imageViewIcon, ImageHelper.ROUND));

        textViewName.setText(folder.name);
        if(!folder.description.equals("")){textViewDesc.setText(folder.description);}
        stars.setRating(folder.starRating);
    }

    private void setLayoutMenu(int position)
    {
        if(position == 0 && data instanceof DataModel.MenuList[])
        {
            layoutResourceId = R.layout.list_view_item_profile;
        }
        else if (position != 0 && data instanceof DataModel.MenuList[])
        {
            layoutResourceId = R.layout.list_view_item_row ;
        }
    }
}
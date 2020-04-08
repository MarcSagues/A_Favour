package cat.udl.tidic.a_favour.ProfileClasses;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import cat.udl.tidic.a_favour.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    public enum OPTION{Favours, Favourites, Opinions};
    public OPTION current_option;
    private String[] titles;
    private String[] description;
    private ImageView[] images;
    private float[] amounts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        public CardView mCardView;
        public TextView title;
        public TextView desc;
        public ImageView image;
        public RatingBar stars;
        public TextView amount;


        public MyViewHolder(View v, OPTION id)
        {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.card_view);
            title = (TextView) v.findViewById(R.id.tv_text);
            desc = (TextView) v.findViewById(R.id.desc);
            image = (ImageView) v.findViewById(R.id.iv_image);

            if (id == OPTION.Favours)
            {
                amount = v.findViewById(R.id.tv_amount);
            }
            if (id == OPTION.Opinions)
            {
                stars = (RatingBar) v.findViewById(R.id.stars);
            }
        }
    }

    //Favours
    public MyAdapter(OPTION op, String[] titles, String[] description, ImageView[] images, float[] amount)
    {
        this.titles = titles;
        this.description = description;
        this.images = images;
        this.amounts = amount;
        this.current_option = op;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d("hola", "caracola");
        View v;
        if (this.current_option == OPTION.Favours)
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favours_list, parent, false);
        }
        else
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.opinions_list, parent, false);
        }
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v, current_option);
        return vh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.title.setText(titles[position]);
        holder.desc.setText(description[position]);

        if (current_option == OPTION.Favours)
        {
            float amount_a = amounts[position];
            holder.image.setImageResource(R.drawable.handshacke);
            if(amount_a != (int) amount_a) {holder.amount.setText("" + amount_a + "€");}
            else {holder.amount.setText(""+(int)amount_a + "€"); }
        }
        if (current_option == OPTION.Opinions)
        {
            holder.image.setImageResource(R.drawable.example_person);
            holder.stars.setRating(amounts[position]);
        }
        /*int drawable = (Integer) images[position].getTag();

        switch(drawable)
        {
            case R.drawable.handshacke:
                holder.image.setImageResource(R.drawable.handshacke);
                break;
        }*/


    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
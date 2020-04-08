package cat.udl.tidic.a_favour.ProfileClasses;
import android.annotation.SuppressLint;
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
    private OPTION current_option;
    private String[] titles;
    private String[] description;
    private float[] amounts;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    static class MyViewHolder extends RecyclerView.ViewHolder
    {
        CardView mCardView;
        TextView title;
        TextView desc;
        ImageView image;
        RatingBar stars;
        TextView amount;


        MyViewHolder(View v, OPTION id) {

            super(v);
            mCardView = v.findViewById(R.id.card_view);
            title =  v.findViewById(R.id.tv_text);
            desc =  v.findViewById(R.id.desc);
            image =  v.findViewById(R.id.iv_image);

            if (id == OPTION.Favours) {
                amount = v.findViewById(R.id.tv_amount);
            }
            if (id == OPTION.Opinions) {
                stars =  v.findViewById(R.id.stars);
            }
        }
    }

    //Favours
    MyAdapter(OPTION op, String[] titles, String[] description, ImageView[] images, float[] amount)
    {
        this.titles = titles;
        this.description = description;
        this.amounts = amount;
        this.current_option = op;
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
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
        return new MyViewHolder(v, current_option);

    }

    @SuppressLint("SetTextI18n")
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
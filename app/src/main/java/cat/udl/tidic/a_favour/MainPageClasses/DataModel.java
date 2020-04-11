package cat.udl.tidic.a_favour.MainPageClasses;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import cat.udl.tidic.a_favour.R;


public class DataModel extends Activity
{

    int icon;
    public String name;
    // Constructor.
    public DataModel() { }

    public static class MenuList extends DataModel
    {
        public MenuList(int icon, String name)
        {
            this.icon = icon;
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }
    }

    public static class Favour extends DataModel
    {
        String categoria;
        String description;
        float amount;

        public Favour(String name, String description, float amount, String categoria)
        {
            this.icon = CategoryManager.getImageId(categoria);
            this.name = name;
            this.description = description;
            this.categoria = categoria;
            this.amount = parseFloat(amount);
        }

        public String getName()
        {
           return  this.name;
        }

        public String getDescription()
        {
            return  this.description;
        }

        public float parseFloat(float amount)
        {
            if (amount != (int) amount) { return amount; }
            else {return (int) amount; }
        }

        public String getCategoria()
        {
            return  this.categoria;
        }
        public String getAmount()
        {
            String favxfav = CategoryManager.CATEGORIES.favourxfavour.name();
            if (this.categoria.equals(CategoryManager.CATEGORIES.favourxfavour.name()))
            {
                //TODO
                return "Favour x favour";
            }
            else
            {
                return String.valueOf(this.amount) + "€";
            }
        }
    }

    public static class Opinion extends DataModel
    {

        String description;
        float starRating;

        public Opinion(int icon, String name, String description, float starRating)
        {
            this.icon = icon;
            this.name = name;
            this.description = description;
            this.starRating = starRating;
        }
    }

}
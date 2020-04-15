package cat.udl.tidic.a_favour.MainPageClasses;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import cat.udl.tidic.a_favour.R;


public class DataModel extends Activity
{



    int icon;
    // Constructor.
    public DataModel() { }

    public static class MenuList extends DataModel
    {
        public String name;
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

        @SerializedName("category")
        public String category;
        @SerializedName("name")
        public String name;
        @SerializedName("desc")
        public String description;
        @SerializedName("amount")
        public float amount;
        @SerializedName("id")
        public int id;
        @SerializedName("user")
        public String user;

        public Favour(String name, String description, float amount, String category, int id, String user)
        {
            this.category = category;
            this.name = name;
            this.description = description;
            this.amount = parseFloat(amount);
            this.user = user;
            this.id = id;
            setIcon();

        }

        public String toString()
        {
            return "ICON: " + this.icon + " NAME: " + this.name + " DESC: " + this.description +" CAT: " + this.category +" AMOUNT: " + this.amount;
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
            return  this.category;
        }
        public String getAmount()
        {
            String favxfav = CategoryManager.CATEGORIES.favourxfavour.name();
            if (this.category.equals(CategoryManager.CATEGORIES.favourxfavour.name()))
            {
                //TODO
                return "Favour x favour";
            }
            else
            {
                return String.valueOf(this.amount) + "€";
            }
        }

        public void setIcon()
        {
            this.icon = CategoryManager.getImageId(this.category);
        }
    }

    public static class Opinion extends DataModel
    {

        String description;
        float starRating;
        public String name;

        public Opinion(int icon, String name, String description, float starRating)
        {
            this.icon = icon;
            this.name = name;
            if(description != null) {this.description = description;}
            this.starRating = starRating;
        }
    }

}
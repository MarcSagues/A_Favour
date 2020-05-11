package cat.udl.tidic.a_favour.MainPageClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.R;

@SuppressLint("Registered")
public class DataModel extends Activity
{
    int icon;

    //Cunstructor vuit de DataModel
    //S'ha utilitzat "herència" per a que sigui més fàcil crear i retornar arrays de
    //DataModel sense que la funció requereixi un tipus específic
    public DataModel() { }

    //Tot el que necessita la llista de la pàgina principal
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

    //Tot el que necessiten els favors
    public static class Favour extends DataModel implements Serializable
    {

        @SerializedName("category")
        public String category;
        //@SerializedName("category")
        //public String category;
        @SerializedName("name")
        public String name;
        @SerializedName("desc")
        public String description;
        @SerializedName("amount")
        public float amount;
        @SerializedName("id")
        public int id;
        @SerializedName("owner_id")
        public int owner_id;
        @SerializedName("user")
        public String user;
        private boolean favourite;

        public Favour(String name, String description, float amount, String category, int id, String user, int owner_id)
        {
            this.category = category;
            this.name = name;
            this.description = description;
            this.amount = parseFloat(amount);
            this.user = user;
            this.id = id;
            this.owner_id = owner_id;
            this.favourite = false;
            setIcon();
        }

        public int getOwner_id() {
            return owner_id;
        }

        @NonNull
        @Override
        public String toString()
        {
            return "ANUNCI:" + " CAT " + this.category + " Nom del anunci " + this.name + " Descripció " +this.description + " Amount " + this.amount + " User " + this.user
                    + " id " + this.id + " favourite " + this.favourite;
        }

        public boolean isFavourite()
        {
            return favourite;
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
            if(amount % 1 != 0) {return  amount;}
            else { return (int) amount;}
        }

        public String getCategoria()
        {
            return  this.category;
        }
        @SuppressLint("DefaultLocale")
        public String getAmount()
        {
            if (this.category.equals(CategoryManager.CATEGORIES.favourxfavour.name()))
            {
                Context c;
                c = App.getAppContext();
                return c.getResources().getString(R.string.favourxfavour);
            }
            else
            {
                float am = parseFloat(this.amount);
                return String.format("%.0f",am) + "€";
            }
        }

        public void setIcon()
        {
            this.icon = CategoryManager.getImageId(this.category);
        }

        public int getIcon()
        {
            return this.icon = CategoryManager.getImageId(this.category);
        }

        public void setCategory(String category)
        {
            this.category = category;
        }

        public void setAmount(float amount)
        {
            this.amount = amount;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }
    }

    //Les opinions dels usuaris
    public static class Opinion extends DataModel
    {

        public String description;
        public Number mark;
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

    //TODO
    //Els missaatges
    public static class Message extends DataModel
    {
        public String otherUsername;
        public String favourName;
        public String lastMessage;
        public String lastMessageDate;

        public Message(String otherUsername, String favourName, String lastMessage, String lastMessageDate)
        {
            this.otherUsername = otherUsername;
            this.favourName = favourName;
            this.lastMessage = lastMessage;
            this.lastMessageDate = lastMessageDate;
        }
    }

}
package cat.udl.tidic.a_favour.MainPageClasses;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Objects;

import cat.udl.tidic.a_favour.App;
import cat.udl.tidic.a_favour.R;
import cat.udl.tidic.a_favour.Views.ProfileView;
import cat.udl.tidic.a_favour.models.ProfileViewModel;
import cat.udl.tidic.a_favour.models.UserModel;
import cat.udl.tidic.a_favour.preferences.PreferencesProvider;

@SuppressLint("Registered")
public class DataModel extends Activity
{
    int icon;
    UserModel userModel;


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

        private MutableLiveData<UserModel> userMutable = new MutableLiveData<>();

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
        @SerializedName("lat")
        public double lat;
        @SerializedName("long")
        public double long_;


        public float distance = 0;
        public Location locationA;
        public Location locationB;
        public float longitud = 0;
        public float latitud = 0;

        public Favour(String name, String description, float amount, String category, int id, String user, int owner_id, double lat, double long_)
        {
            this.category = category;
            this.name = name;
            this.description = description;
            this.amount = parseFloat(amount);
            this.user = user;
            this.id = id;
            this.owner_id = owner_id;
            this.favourite = false;
            this.lat = lat;
            this.long_ = long_;
            setIcon();

        }

        public int getOwner_id() {
            return owner_id;
        }

        public double getLat() {
            return lat;
        }

        public double getLong_() {
            return long_;
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

        public void setDistance(SharedPreferences mPreferences) {
            locationA = new Location("point A");
            locationB = new Location("point B");
            System.out.println("id------- "+mPreferences.getInt("id",id));
            System.out.println("id------- "+mPreferences.getFloat("longitud",longitud));
            mPreferences.getFloat("longitud", longitud);
            mPreferences.getFloat("latitud", latitud);
            locationA.setLatitude(longitud);
            locationA.setLongitude(latitud);
            locationB.setLatitude(lat);
            locationB.setLongitude(long_);
            distance = locationA.distanceTo(locationB);
        }
    }

    //Les opinions dels usuaris


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
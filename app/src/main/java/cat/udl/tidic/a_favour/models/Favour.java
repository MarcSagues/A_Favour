package cat.udl.tidic.a_favour.models;

import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Favour implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("category")
    private CategoryEnum category;
    @SerializedName("type")
    private FavourTypeEnum type;
    @SerializedName("name")
    private String name;
    @SerializedName("desc")
    private String description;
    @SerializedName("amount")
    private float amount;
    @SerializedName("owner_id")
    private int owner_id;
    @SerializedName("user")
    private String user;
    private boolean favourite;
    @SerializedName("latitude")
    private double latitutde;
    @SerializedName("longitude")
    private double longitude;


    @NonNull
    @Override
    public String toString()
    {
        return "ANUNCI: \n"
                + " Category: " + this.category + " \n"
                + " Type: " + this.type + " \n"
                + " Nom del anunci " + this.name + " \n"
                + " Descripció " +this.description + " \n"
                + " Amount " + this.amount + " \n"
                + " User " + this.user + " \n"
                + " Id " + this.id + " \n"
                + " Latitude " + this.latitutde + " \n"
                + " Longitude " + this.longitude + " \n"
                + " --------------------------------- \n";
    }

    public FavourTypeEnum getType() {
        return type;
    }

    public void setType(FavourTypeEnum type) {
        this.type = type;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount(){
        return this.amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public double getLatitutde() {
        return latitutde;
    }

    public void setLatitutde(double latitutde) {
        this.latitutde = latitutde;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Favour)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Favour f = (Favour) o;

        // Compare the data members and return accordingly
        return this.name.equals(f.getName())
                && this.category == f.getCategory()
                && this.type == f.getType()
                && this.description.equals(f.getDescription())
                && this.amount == f.getAmount()
                && this.latitutde == f.getLatitutde()
                && this.longitude == f.getLongitude()
                && this.id == f.getId();
    }

    public int getDistanceTo(Location currentLocation) {
        Log.d("Favour (Model)", "getDistanceTo() -> cLocation: " + currentLocation);
        if (currentLocation != null && this.getLatitutde() > 0 && this.getLongitude() > 0) {
            float[] distance = new float[1];
            Location.distanceBetween(currentLocation.getLatitude(), currentLocation.getLongitude(),
                    this.getLatitutde(), this.getLongitude(), distance);
            return (int) (distance[0] / 1000);
        }
        return -1;
    }
}

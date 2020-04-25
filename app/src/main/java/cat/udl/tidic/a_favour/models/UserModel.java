package cat.udl.tidic.a_favour.models;

import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserModel
{
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("phone")
    private String phone;
    @SerializedName("photo")
    private String photo;
    @SerializedName("stars")
    private float stars;
    @SerializedName("favoursDone")
    private int favoursDone;
    @SerializedName("timesHelped")
    private int timesHelped;
    @SerializedName("location")
    private String location;
    @SerializedName("token")
    private String token;
    @SerializedName("login")
    private boolean login;
    @SerializedName("id")
    private int id;


    public UserModel(String created_at, int id, String username, String email, String name, String surname, String birthday, String password, float stars, int favoursDone, int timesHelped, String location, String token)
    {
        this.created_at = created_at;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.password = password;
        this.stars = stars;
        this.favoursDone = favoursDone;
        this.timesHelped = timesHelped;
        this.location = location;
        this.token = token;
        this.id = id;
    }


    public int getId()
    {
        return this.id;
    }
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setLogin(boolean login){
        this.login = login;
    }

    public boolean getLogin(){
        return login;
    }

    String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password){
        this.password = password;}

    String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    int getFavoursDone() {
        return favoursDone;
    }

    public void setFavoursDone(int favoursDone) {
        this.favoursDone = favoursDone;
    }

    int getTimesHelped() {
        return timesHelped;
    }

    public void setTimesHelped(int timesHelped) {
        this.timesHelped = timesHelped;
    }

    String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getToken()
    {
        return token;
    }

    @NonNull
    @Override
    public String toString(){
        return this.name + " " + this.surname + " "+  this.stars;
    }
}

package cat.udl.tidic.a_favour.models;

import java.io.Serializable;

public class Message implements Serializable
{
    private UserModel user;
    private String date;
    private String text;



    public Message(UserModel user, String date, String text)
    {
        this.user = user;
        this.date = date;
        this.text = text;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel u) {
        this.user = u;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

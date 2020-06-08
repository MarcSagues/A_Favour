package cat.udl.tidic.a_favour.models;

import java.io.Serializable;

public class Message implements Serializable
{
    private int owner_id;
    private String date;
    private String text;



    public Message(int owner_id, String date, String text)
    {
        this.owner_id = owner_id;
        this.date = date;
        this.text = text;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
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

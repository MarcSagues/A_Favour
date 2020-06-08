package cat.udl.tidic.a_favour.models;

import java.util.List;

public  class Message
{
    public UserModel user;
    public UserModel other_user;
    public Favour favour;
    public  List<String> messages;
    private String lastMessage;
    private String lastMessageDate;


    public Message(UserModel user, UserModel other_user, Favour fav)
    {
        this.user = user;
        this.other_user = other_user;
        this.lastMessage = lastMessage;
        this.lastMessageDate = lastMessageDate;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageDate() {
        return lastMessageDate;
    }

    public void setLastMessageDate(String lastMessageDate) {
        this.lastMessageDate = lastMessageDate;
    }
}
package cat.udl.tidic.a_favour.models;

import java.io.Serializable;
import java.util.List;

public  class Chat implements Serializable
{
    public UserModel user;
    public UserModel other_user;
    public Favour favour;

    private List<Message> messages;

    public List<Message> getMessages() {
        return this.messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    private String lastMessage;
    private String lastMessageDate;


    public Chat(UserModel user, UserModel other_user, Favour fav, List<Message> allMessages)
    {
        this.user = user;
        this.other_user = other_user;
        this.favour = fav;
        this.messages = allMessages;
        this.lastMessage = allMessages.get(allMessages.size()-1).getText();
        this.lastMessageDate = allMessages.get(allMessages.size()-1).getDate();
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

    public Favour getFavour() {
        return favour;
    }

    public void setFavour(Favour favour) {
        this.favour = favour;
    }
}
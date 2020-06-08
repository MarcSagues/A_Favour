package cat.udl.tidic.a_favour.models;

import java.util.List;

public  class Chats
{
    public UserModel user;
    public UserModel other_user;
    public Favour favour;
    private List<Messages> messages;
    private String lastMessage;
    private String lastMessageDate;


    public Chats(UserModel user, UserModel other_user, Favour fav, List<Messages> allMessages)
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
}
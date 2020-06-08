package cat.udl.tidic.a_favour.models;

import java.util.List;

public class MessageRoom
{
    public List<Chats> allMessages;

    public List<Chats> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(List<Chats> allMessages) {
        this.allMessages = allMessages;
    }
}

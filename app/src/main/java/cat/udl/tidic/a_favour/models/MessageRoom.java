package cat.udl.tidic.a_favour.models;

import java.util.List;

public class MessageRoom
{
    public List<Chat> allMessages;

    public List<Chat> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(List<Chat> allMessages) {
        this.allMessages = allMessages;
    }
}

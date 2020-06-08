package cat.udl.tidic.a_favour.models;

import java.util.List;

public class MessageRoom
{
    public List<Message> allMessages;

    public List<Message> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(List<Message> allMessages) {
        this.allMessages = allMessages;
    }
}

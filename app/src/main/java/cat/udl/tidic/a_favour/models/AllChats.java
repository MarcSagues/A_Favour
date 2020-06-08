package cat.udl.tidic.a_favour.models;

import java.util.ArrayList;
import java.util.List;

public class AllChats
{
    public List<Chat> allChats;

    public List<Chat> getallChats()
    {
        return this.allChats;
    }

    public AllChats(List<Chat> allChats)
    {
        this.allChats = new ArrayList<Chat>();
        this.allChats.addAll(allChats);
    }
}

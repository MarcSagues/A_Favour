package cat.udl.tidic.a_favour;

import java.util.ArrayList;
import java.util.List;

import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;
import cat.udl.tidic.a_favour.models.CategoryEnum;
import cat.udl.tidic.a_favour.models.Favour;
import cat.udl.tidic.a_favour.models.Chat;
import cat.udl.tidic.a_favour.models.FavourTypeEnum;
import cat.udl.tidic.a_favour.models.Message;
import cat.udl.tidic.a_favour.models.Opinions;
import cat.udl.tidic.a_favour.models.UserModel;

public class FORTESTING
{
    //Si dev és FALSE --> Es fan les crides a l'API
    //Si dev és TRUE --> S'hagafen favors/opinions hardcoded
    public static Boolean dev = true;
    public static Boolean checkLogin = true;
    public static DataModel.Favour[] getExampleList()
{
    DataModel.Favour[] eventList = new DataModel.Favour[10];
    eventList[0] = new DataModel.Favour(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Testdescription, Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.daytodaythings.name(), 2, "user1", 1, 41.3887901, 2.1589899);
    eventList[1] = new DataModel.Favour(
            "Lorem ipsum dolor sit amet",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.favourxfavour.name(), 2, "user2",2, 41.3887901, 2.1589899);

    eventList[2] = new DataModel.Favour(
            "consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.computing.name(), 3, "user1",1, 41.3887901, 2.1589899);

    eventList[3] = new DataModel.Favour(
            "Necessito ajuda per pujar la compra a casa",
            "Testdescription helooooou, Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.others.name(), 4, "user4",4, 41.3887901, 2.1589899);

    eventList[4] = new DataModel.Favour(
            "Necessito ajuda per pujar la compra a casa",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.reparation.name(), 5, "user1",1, 41.3887901, 2.1589899);
    eventList[5] = eventList[0];
    eventList[6] = eventList[0];
    eventList[7] = eventList[0];
    eventList[8] = eventList[0];
    eventList[9] = eventList[0];



    return eventList;
}

    public static List<Chat> getChats()
    {
        UserModel user1 = new UserModel("12/12/12",1,"Uri","gg@wp.com",
                "Jampi","Jumpi","12/12/12","uuuuuuu",3,2,1,"Igualada," 
                ,"kk",12,12);

        UserModel user2 = new UserModel("12/12/12",1,"Pepito","gg@wp.com",
                "Pepito","Jumpi","12/12/12","uuuuuuu",3,2,1,"Igualada," 
                ,"kk",12,12);

        Favour favour = new Favour();
        favour.setName("The name of favour");
        favour.setCategory(CategoryEnum.daytodaythings);
        favour.setAmount(20);
        favour.setDescription("Desc");
        favour.setType(FavourTypeEnum.necessito);
        
        List<Message> messages = new ArrayList<Message>();
        Message message1 = new Message(user1,"10:22", "The text of message");
        Message message2 = new Message(user2,"10:23", "The text of message2");
        messages.add(message1);
        messages.add(message2);
        List<Chat> chatList = new ArrayList<Chat>();

        chatList.add(new Chat(user1,user2,favour,messages));
        chatList.add(new Chat(user1,user2,favour,messages));
        chatList.add(new Chat(user1,user2,favour,messages));
        chatList.add(new Chat(user1,user2,favour,messages));
        chatList.add(new Chat(user1,user2,favour,messages));
        
        return chatList;
    }


    public static Opinions[] getExampleListOPINION()
    {
        Opinions[] eventList = new Opinions[6];
        eventList[0] = new Opinions(R.drawable.example_person, "Pepeito Jimenez", "Muy bien todo", 4.5f);
        eventList[1] = eventList[0];
        eventList[2] = eventList[0];
        eventList[3] = eventList[0];
        eventList[4] = eventList[0];
        eventList[5] = eventList[0];

        return eventList;
    }
}

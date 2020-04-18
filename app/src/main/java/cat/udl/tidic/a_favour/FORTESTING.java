package cat.udl.tidic.a_favour;

import android.provider.ContactsContract;

import java.util.HashMap;

import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;

public class FORTESTING
{
    public static Boolean dev = true;
    public static DataModel.Favour[] getExampleList()
{
    DataModel.Favour[] eventList = new DataModel.Favour[10];
    eventList[0] = new DataModel.Favour(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Testdescription, Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,

            CategoryManager.CATEGORIES.daytodaythings.name(), 1, "user1");
    eventList[1] = new DataModel.Favour(
            "Lorem ipsum dolor sit amet",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.favourxfavour.name(), 2, "user2");

    eventList[2] = new DataModel.Favour(
            "consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.computing.name(), 3, "user1");

    eventList[3] = new DataModel.Favour(
            "Necessito ajuda per pujar la compra a casa",
            "Testdescription helooooou, Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.others.name(), 4, "user4");

    eventList[4] = new DataModel.Favour(
            "Necessito ajuda per pujar la compra a casa",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.reparation.name(), 5, "user1");
    eventList[5] = eventList[0];
    eventList[6] = eventList[0];
    eventList[7] = eventList[0];
    eventList[8] = eventList[0];
    eventList[9] = eventList[0];



    return eventList;
}

    public static DataModel.Message[] getMessageList()
    {
        DataModel.Message[] messageList = new DataModel.Message[5];
        messageList[0] = new DataModel.Message(
                "Pepito",
                "Ayuda para noseque", "" +
                "Podrias quedar alas 5?",
                "10/5/2020");

        messageList[1] = new DataModel.Message(
                "Lucía",
                "Repaso de Mates", "" +
                "Necessito que ayudes a mi hijo en Mates",
                "12/10/2010");

        messageList[2] = new DataModel.Message(
                "Tete Lore",
                "Message Ipsum", "" +
                "Lore Lore Ipsum Ipsum",
                "10/5/2020");
        messageList[3] = messageList[0];
        messageList[4] = messageList[2];
        return messageList;
    }


    public static DataModel.Opinion[] getExampleListOPINION()
    {
        DataModel.Opinion[] eventList = new DataModel.Opinion[6];
        eventList[0] = new DataModel.Opinion(R.drawable.example_person, "Pepeito Jimenez", "Muy bien todo", 4.5f);
        eventList[1] = eventList[0];
        eventList[2] = eventList[0];
        eventList[3] = eventList[0];
        eventList[4] = eventList[0];
        eventList[5] = eventList[0];

        return eventList;
    }
}

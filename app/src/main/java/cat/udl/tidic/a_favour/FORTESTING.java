package cat.udl.tidic.a_favour;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;

public class FORTESTING
{

    public static DataModel.Favour[] getExampleList()
    {
        DataModel.Favour[] eventList = new DataModel.Favour[10];
        eventList[0] = new DataModel.Favour(R.drawable.handshacke, "Necessito ajuda per pujar la compra a casa", "Testdescription", 2.5f, DataModel.CATEGORIES.favorxfavour.name());
        eventList[1] = eventList[0];
        eventList[2] = eventList[0];
        eventList[3] = eventList[0];
        eventList[4] = eventList[0];
        eventList[5] = eventList[0];
        eventList[6] = eventList[0];
        eventList[7] = eventList[0];
        eventList[8] = eventList[0];
        eventList[9] = eventList[0];
        return eventList;
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
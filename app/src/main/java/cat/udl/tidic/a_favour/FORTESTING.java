package cat.udl.tidic.a_favour;

import java.util.HashMap;

import cat.udl.tidic.a_favour.MainPageClasses.CategoryManager;
import cat.udl.tidic.a_favour.MainPageClasses.DataModel;

public class FORTESTING
{
    public DataModel.Favour[] getExampleList()
    {
        DataModel.Favour[] eventList = new DataModel.Favour[10];
        eventList[0] = new DataModel.Favour(CategoryManager.getImageId(CategoryManager.CATEGORIES.daytodaythings.name()),
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Testdescription, Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            2.5f,
            CategoryManager.CATEGORIES.daytodaythings.name());

        eventList[1] = new DataModel.Favour(CategoryManager.getImageId(CategoryManager.CATEGORIES.favourxfavour.name()),
                "Lorem ipsum dolor sit amet",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                2.5f,
                CategoryManager.CATEGORIES.favourxfavour.name());

        eventList[2] = new DataModel.Favour(CategoryManager.getImageId(CategoryManager.CATEGORIES.computing.name()),
                "consectetur adipiscing elit.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                2.5f,
                CategoryManager.CATEGORIES.computing.name());

        eventList[3] = new DataModel.Favour(CategoryManager.getImageId(CategoryManager.CATEGORIES.others.name()),
                "Necessito ajuda per pujar la compra a casa",
                "Testdescription helooooou, Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                2.5f,
                CategoryManager.CATEGORIES.others.name());

        eventList[4] = new DataModel.Favour(CategoryManager.getImageId(CategoryManager.CATEGORIES.reparation.name()),
                "Necessito ajuda per pujar la compra a casa",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                2.5f,
                CategoryManager.CATEGORIES.reparation.name());
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

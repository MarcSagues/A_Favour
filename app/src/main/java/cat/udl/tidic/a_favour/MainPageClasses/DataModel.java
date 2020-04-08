package cat.udl.tidic.a_favour.MainPageClasses;

public class DataModel {

    int icon;
    public String name;
    String desc;
    float amount;

    // Constructor.
    public DataModel(int icon, String name, String desc, float amount)
    {
        this.icon = icon;
        this.name = name;
        this.desc = desc;
        this.amount = amount;
    }
}
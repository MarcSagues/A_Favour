package cat.udl.tidic.a_favour.MainPageClasses;

public class DataModel
{
    public enum CATEGORIES {favorxfavour, daytodaythings, computing, reparation, others}
    int icon;
    public String name;
    String desc;
    CATEGORIES cat;
    float amount;

    // Constructor.
    public DataModel(int icon, String name, String desc, float amount, CATEGORIES cat)
    {
        this.icon = icon;
        this.name = name;
        this.desc = desc;
        this.amount = amount;
        this.cat = cat;
    }

    public CATEGORIES getCat()
    {
        return this.cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCat(CATEGORIES cat) {
        this.cat = cat;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
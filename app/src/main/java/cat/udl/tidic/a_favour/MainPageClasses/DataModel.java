package cat.udl.tidic.a_favour.MainPageClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class DataModel implements Parcelable
{
    protected DataModel(Parcel in) {
        icon = in.readInt();
        name = in.readString();
        desc = in.readString();
        amount = in.readFloat();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeFloat(amount);
    }

    public enum CATEGORIES {favorxfavour, daytodaythings, computing, reparation, others}
    int icon;
    public String name;
    String desc;
    CATEGORIES cat;
    float amount;

    public boolean isMyfavour() {
        return myfavour;
    }

    public void setMyfavour(boolean myfavour) {
        this.myfavour = myfavour;
    }

    boolean myfavour;

    // Constructor.
    public DataModel(boolean myfavour, int icon, String name, String desc, float amount, CATEGORIES cat)
    {
        this.icon = icon;
        this.name = name;
        this.desc = desc;
        this.amount = amount;
        this.cat = cat;
        this.myfavour = myfavour;
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
package cat.udl.tidic.a_favour.models;

import cat.udl.tidic.a_favour.MainPageClasses.DataModel;

public class Opinions
{
    public int icon;
    public String description;
    public Number mark;
    float starRating;
    public String name;


    public Opinions(int icon, String name, String description, float starRating)
    {
        this.icon = icon;
        this.name = name;
        if(description != null) {this.description = description;}
        this.starRating = starRating;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Number getMark() {
        return mark;
    }

    public void setMark(Number mark) {
        this.mark = mark;
    }

    public float getStarRating() {
        return starRating;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

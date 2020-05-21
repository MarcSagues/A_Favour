package cat.udl.tidic.a_favour.models;

import cat.udl.tidic.a_favour.R;

public enum CategoryEnum {
    all("all","all"),
    favourxfavour("favourxfavour","favour x favour"),
    daytodaythings("daytodaythings","daytodaythings"),
    computing("computing","computing"),
    reparation("reparation", "reparation"),
    others("others","others");

    String name;
    String id;

    CategoryEnum(String _id, String _name){
        id = _id;
        name = _name;
    }

    public String getName(){
        return name;
    }

    public static int getIcon(CategoryEnum e){

        switch(e){
            case favourxfavour:
                return R.drawable.favourxfavour;
            case daytodaythings:
                return R.drawable.daytodaythings;
            case computing:
                return R.drawable.computing;
            case reparation:
                return R.drawable.reparation;
            default:
                return R.drawable.others;
        }

    }
}

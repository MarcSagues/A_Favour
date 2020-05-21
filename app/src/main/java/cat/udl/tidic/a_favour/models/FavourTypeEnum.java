package cat.udl.tidic.a_favour.models;

import cat.udl.tidic.a_favour.R;

public enum FavourTypeEnum {
    all("all","all"),
    ofereixo("ofereixo","ofereixo"),
    necessito("necessito","necessito");

    String name;
    String id;

    FavourTypeEnum(String _id, String _name){
        id = _id;
        name = _name;
    }

    public String getName(){
        return name;
    }

    public static int getColourResource(FavourTypeEnum e){

        switch(e){
            case ofereixo:
                return R.color.colorPrimary;
            case necessito:
                return R.color.colorAccent;
            default:
                return R.color.white;
        }

    }
}

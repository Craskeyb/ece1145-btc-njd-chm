package hotciv.standard;

import hotciv.framework.Tile;

/* Skeleton implementation of HotCiv.*/



public class TileImpl implements Tile {

    private String type;
    public TileImpl(String type){
        this.type = type;
    }
    public String getTypeString() {return this.type;}

    public void setTypeString(String type){
        this.type = type;
    }

}

package hotciv.standard;

import hotciv.framework.Tile;

import java.util.HashMap;

/* Skeleton implementation of HotCiv.*/



public class TileImpl implements Tile {

    private String type;
    public TileImpl(String type){
        this.type = type;
    }
    public String getTypeString() {return this.type;}


}

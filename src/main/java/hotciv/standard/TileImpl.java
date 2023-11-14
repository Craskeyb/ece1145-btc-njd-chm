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

    public void setTypeString(String type){
        this.type = type;
    }
    public int getFoodValue() {
        // Assuming food production values for each tile type
        switch (type) {
            case GameConstants.PLAINS: return 3;
            case GameConstants.OCEANS: return 1;
            case GameConstants.FOREST: return 2;
            default: return 0;
        }
}

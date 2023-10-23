package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.framework.Game;

public class SettlerImpl implements Unit {
    private Player owner;
    private String type = GameConstants.SETTLER;
    private int defStr = 3;
    private int attStr = 0;


    public SettlerImpl(Player owner)
    {
        this.owner = owner;
    }

    public String getTypeString() {return type;}
    public Player getOwner() {return owner;}

    public int getMoveCount() {return -1;}

    public int getDefensiveStrength() {return defStr;}
    public int getAttackingStrength() {return attStr;}

    public void setDefensiveStrength(int strength) {defStr = strength;}
    public void setAttackingStrength(int strength) {attStr = strength;}

    public Boolean buildCity(Game game, Position p){
        //If there is no city at the space the settler is on, create one and remove the settler from the map
        if(game.getCityAt(p) == null){
            game.createCity(p);
            game.removeUnit(p);
            return true;
        }
        else{
            return false;
        }
    }
}

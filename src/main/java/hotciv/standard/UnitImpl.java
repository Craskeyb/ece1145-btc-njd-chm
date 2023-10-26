package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private Player owner;
    private String type;
    private int defStr;
    private int attStr;
    
    public UnitImpl(Player owner,String type)
    {
        this.owner = owner;
        this.type = type;

        if(type == GameConstants.ARCHER){
            defStr = 3;
            attStr = 2;
        }
        else if(type == GameConstants.SETTLER){
            defStr = 3;
            attStr = 0;
        }
        else{
            defStr = 2;
            attStr = 4;
        }
    }

    public String getTypeString() {return type;}
    public Player getOwner() {return owner;}

    public int getMoveCount() {return -1;}
    public int getDefensiveStrength() {return defStr;}
    public int getAttackingStrength() {return attStr;}
    public void setDefensiveStrength(int strength){}
    public void setAttackingStrength(int strength){}
}

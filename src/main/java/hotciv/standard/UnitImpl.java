package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private Player owner;
    private String type;
    
    public UnitImpl(Player owner,String type)
    {
        this.owner = owner;
        this.type = type;
    }

    public String getTypeString() {return type;}
    public Player getOwner() {return owner;}

    public int getMoveCount() {return -1;}
    public int getDefensiveStrength() {return -1;}
    public int getAttackingStrength() {return -1;}
    public void setDefensiveStrength(int strength){}
    public void setAttackingStrength(int strength){}
}

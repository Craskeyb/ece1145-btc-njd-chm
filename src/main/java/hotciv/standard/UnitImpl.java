package hotciv.standard.implementation;

import hotciv.framework.Unit;

public class UnitImpl implements Unit {
    public String getTypeString() {return type;}
    public Player getOwner() {return owner;}
    public int getMoveCount() {return -1;}
    public int getDefensiveStrength() {return -1;}
    public int getAttackingStrength() {return -1;}
}

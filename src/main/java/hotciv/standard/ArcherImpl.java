package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

public class ArcherImpl implements Unit {
    private Player owner;
    private String type = GameConstants.ARCHER;
    private int defStr = 3;
    private int attStr = 2;
    
    public ArcherImpl(Player owner)
    {
        this.owner = owner;
    }

    public String getTypeString() {return type;}
    public Player getOwner() {return owner;}

    public int getMoveCount() {
        if (this.getDefensiveStrength() == 6){
            return 0;
        }    
        return -1;
    }

    public int getDefensiveStrength() {return defStr;}
    public int getAttackingStrength() {return attStr;}

    public void setDefensiveStrength(int strength) {defStr = strength;}
    public void setAttackingStrength(int strength) {attStr = strength;}

    public void fortify(){
        if (defStr == 3){
            this.setDefensiveStrength(6);
        }
        else{
            this.setDefensiveStrength(3);
        }
    }
}

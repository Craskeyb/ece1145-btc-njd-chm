package hotciv.standard;

import hotciv.framework.*;

public class UnitImpl implements Unit {

    private Player owner;
    private String type;
    private int defStr = 0;
    private int attStr = 0;
    private int moveCt = 0;
    
    public UnitImpl(Player owner,String type)
    {
        this.owner = owner;
        this.type = type;

        if(type == GameConstants.ARCHER){
            defStr = 3;
            attStr = 2;
            moveCt = 1;
        }
        else if(type == GameConstants.SETTLER){
            defStr = 3;
            attStr = 0;
            moveCt = 1;
        }
        else if(type == GameConstants.LEGION){
            defStr = 2;
            attStr = 4;
            moveCt = 1;
        }
        //UFO Case
        else{
            defStr = 8;
            attStr = 1;
            moveCt = 2;
        }
    }

    public String getTypeString() {return type;}
    public Player getOwner() {return owner;}

    public int getMoveCount() {return moveCt;}
    
    public void resetMoveCount(){
        if(this.type == "ufo"){
            moveCt = 2;
        }
        else{
            moveCt = 1;
        }
    }

    public void decreaseMoveCount(){
        moveCt -= 1;
    }

    public int getDefensiveStrength() {return defStr;}
    public int getAttackingStrength() {return attStr;}
    public void setDefensiveStrength(int strength){ defStr = strength;}
    public void setAttackingStrength(int strength){ attStr = strength;}
}

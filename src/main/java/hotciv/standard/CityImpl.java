package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner;
    private int population;
    private int treasury;
    
    //For now, will only produce archers
    private String production = GameConstants.ARCHER;

    public CityImpl(Player owner){
        this.owner = owner;
        this.population = 1;
        this.treasury = 0;
    }
    public Player getOwner() { return this.owner;}
    public int getSize(){ return this.population;}
    public int getTreasury(){ return this.treasury;}
    
    public void increaseTreasury(){
        this.treasury += 6;
    }

    public void decreaseTreasury(int cost){
        this.treasury -= cost;
    }
    
    public String getProduction(){ return production;}
    public String getWorkforceFocus(){ return null;}
}

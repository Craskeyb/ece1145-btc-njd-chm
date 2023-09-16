package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner;
    private int population;
    private int treasury;
    
    public CityImpl(Player owner){
        this.owner = owner;
        this.population = 1;
        this.treasury = 0;
    }
    public Player getOwner() { return this.owner;}
    public int getSize(){ return this.population;}
    public int getTreasury(){ return this.treasury;}
    
    
    public String getProduction(){ return null;}
    public String getWorkforceFocus(){ return null;}
}

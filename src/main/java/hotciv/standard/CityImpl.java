package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City{

    private Player owner;
    private int population;
    private Position position = new Position(0,0);
    private int treasury;

    public CityImpl(Player owner){
        this.owner = owner;
        this.population = 1;
        if(owner == Player.RED){
            this.position = new Position(1,1);
        }
        else{
            this.position = new Position(4,1);
        }
        this.treasury = 0;
    }
    public Player getOwner() { return this.owner;}
    public Position getPosition(){return this.position;}
    public int getSize(){ return this.population;}
    public int getTreasury(){ return this.treasury;}
    
    
    public Player getOwner() { return null;}
    public int getSize(){ return 1;}
    public int getTreasury(){ return 0;}
    public String getProduction(){ return null;}
    public String getWorkforceFocus(){ return null;}
}

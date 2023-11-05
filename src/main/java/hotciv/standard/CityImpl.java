package hotciv.standard;

import hotciv.framework.*;



public class CityImpl implements City{

    private Player owner;
    private int population;
    private int treasury;

    private String focus;
    
    //For now, will only produce archers
    private String production = GameConstants.ARCHER;

    public CityImpl(Player owner){
        this.owner = owner;
        population = 1;
        treasury = 0;
        focus = GameConstants.productionFocus;
    }
    public Player getOwner() { return this.owner;}

    public void setOwner(Player owner) {this.owner = owner;}

    public int getSize(){ return this.population;}
    public int getTreasury(){ return this.treasury;}

    public void setTreasury(int value){treasury = value;}

    public void increaseTreasury(){
        this.treasury += 6;
    }

    public void decreaseTreasury(int cost){
        this.treasury -= cost;
    }
    
    public String getProduction(){ return production;}

    public void changeProduction(String type){
        this.production = type;
    }

    public void setWorkforceFocus(String balance){focus = balance;}
    public String getWorkforceFocus(){ return focus;}


}

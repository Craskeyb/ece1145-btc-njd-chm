package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

Citypublic class CityImpl implements City{
    public Player getOwner() { return null;}
    public int getSize(){ return 1;}
    public int getTreasury(){ return 0;}
    public String getWorkforceFocus(){ return null;}


     public String getProduction() {
        return production;
    }
}

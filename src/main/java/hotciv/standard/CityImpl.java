package hotciv.standard;

import hotciv.framework.*;



public class CityImpl implements City{

    private Player owner;
    private int population;
    private int treasury;
    private String workforceFocus;
    private int foodProduction;
    private int foodAccumulated;
    private int size;
    private String focus;
    
    //For now, will only produce archers
    private String production = GameConstants.ARCHER;

    public CityImpl(Player owner){
        this.owner = owner;
        population = 1;
        treasury = 0;
        focus = GameConstants.productionFocus;
        this.workforceFocus = GameConstants.foodFocus; // Default focus
        this.foodProduction = 0;
        this.foodAccumulated = 0;
        this.size = 1; // Assuming initial size is 1
    }
    @Override
    public String getWorkforceFocus() {
        return workforceFocus;
    }

    @Override
    public int getFoodProduction() {
        return foodProduction;
    }

    @Override
    public void increaseSize() {
        if (size < 9) {
            size++;
            foodAccumulated = 0; // Reset food after growth
        }
    }

    // Method to handle food accumulation
    public void accumulateFood(int newFood) {
        this.foodAccumulated += newFood;
        if (this.foodAccumulated > 5 + (size * 3)) {
            increaseSize();
        }
    }
    public Player getOwner() { 
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getSize(){ 
        return this.population;
    }
    public int getTreasury(){ 
        return this.treasury;
    }

    public void setTreasury(int value){
        treasury = value;
    }

    public void increaseTreasury(){
        this.treasury += 6;
    }

    public void decreaseTreasury(int cost){
        this.treasury -= cost;
    }
    
    public String getProduction(){ 
        return production;
    }

    public void changeProduction(String type){
        this.production = type;
    }

    public void setWorkforceFocus(String balance){
        focus = balance;
    }
    public String getWorkforceFocus(){ 
        return focus;
    }


}

package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/** Skeleton implementation of HotCiv.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

public class GameImpl implements Game {
  private HashMap<Position,Tile> map = new HashMap<Position, Tile>();
  private HashMap<Position,Unit> unitMap = new HashMap<Position, Unit>();
  private HashMap<Position,City> cityMap = new HashMap<Position, City>();
  public GameImpl(){
    for(int i=0;i<=GameConstants.WORLDSIZE;i++){
      for(int j=0;j<=GameConstants.WORLDSIZE;j++){
        if (i == 1 && j == 0){
          map.put(new Position(i,j),new TileImpl(GameConstants.OCEANS));
        }
        else if (i == 0 && j == 1){
          map.put(new Position(i,j),new TileImpl(GameConstants.HILLS));
        }
        else if (i == 2 && j == 2){
          map.put(new Position(i,j),new TileImpl(GameConstants.MOUNTAINS));
        }
        else {
          map.put(new Position(i,j),new TileImpl(GameConstants.PLAINS));
        }
        if (i == 2 && j == 0){
          unitMap.put(new Position(i,j),new UnitImpl(Player.RED, GameConstants.ARCHER));
        }
        else if (i == 3 && j == 2){
          unitMap.put(new Position(i,j),new UnitImpl(Player.BLUE, GameConstants.LEGION));
        }
        else if (i == 4 && j == 3){
          unitMap.put(new Position(i,j),new UnitImpl(Player.RED, GameConstants.SETTLER));
        }
        if (i == 1 && j == 1){
          cityMap.put(new Position(i,j),new CityImpl(Player.RED));
        }
        else if (i == 4 && j == 1){
          cityMap.put(new Position(i,j),new CityImpl(Player.BLUE));
        }
      }
    }
  }
  public Tile getTileAt( Position p ) { return map.get(p);}
  public Unit getUnitAt( Position p ) { return unitMap.get(p); }
  public City getCityAt( Position p ) { return cityMap.get(p); }
  public Player getPlayerInTurn() { return Player.RED; }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }
  public boolean moveUnit( Position from, Position to ) {
    if(from.getRow() == 3 && from.getColumn() == 2){
      return false;
    }
    return true;
  }
  public void endOfTurn() {}
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
   public String getProduction() {
        return production;
    }
}

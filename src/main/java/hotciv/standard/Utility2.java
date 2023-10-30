package hotciv.standard;

import hotciv.framework.*;

import java.util.Iterator;

/** Sep 2019: Updated the book's O computation utility
 * functions.
 *
 * @author Henrik BÃ¦rbak Christensen, Aarhus University
 */
public class Utility2 {
  /**
   * get the terrain factor for the attack and defense strength according to the
   * GammaCiv specification
   *
   * @param game
   *          the game the factor should be given for
   * @param position
   *          the position that the factor should be calculated for
   * @return the terrain factor
   */
  public int getTerrainFactor(Game game, Position position) {
    // cities overrule underlying terrain
    if ( game.getCityAt(position) != null ) { return 3; }
    Tile t = game.getTileAt(position);
    if ( t.getTypeString() == GameConstants.FOREST ||
            t.getTypeString() == GameConstants.HILLS ) {
      return 2;
    }
    return 1;
  }

  /**
   * calculate the additional support a unit at position p owned by a given
   * player gets from friendly units on the given game.
   *
   * @param game
   *          the game to calculate on
   * @param position
   *          the position of the unit whose support is wanted
   * @param player
   *          the player owning the unit at position 'position'
   * @return the support for the unit, +1 for each friendly unit in the 8
   *         neighborhood.
   */
  public int getFriendlySupport(Game game, Position position, Player player) {
    int support = 0;
    int unitR = position.getRow();
    int unitC = position.getColumn();
    
    //Looping through adjacent tiles for support
    if (game.getUnitAt(new Position(unitR - 1, unitC)) != null && game.getUnitAt(new Position(unitR - 1, unitC)).getOwner() == player) {
        support++;
    }
    if (game.getUnitAt(new Position(unitR - 1, unitC + 1)) != null && game.getUnitAt(new Position(unitR - 1, unitC + 1)).getOwner() == player) {
        support++;
    }
    if(game.getUnitAt(new Position(unitR,unitC+1)) != null && game.getUnitAt(new Position(unitR,unitC+1)).getOwner() == player){
        support++;
    }
    if(game.getUnitAt(new Position(unitR+1,unitC+1)) != null && game.getUnitAt(new Position(unitR+1,unitC+1)).getOwner() == player){
        support++;
    }
    if(game.getUnitAt(new Position(unitR+1,unitC)) != null && game.getUnitAt(new Position(unitR+1,unitC)).getOwner() == player){
        support++;
    }
    if(game.getUnitAt(new Position(unitR+1,unitC-1)) != null && game.getUnitAt(new Position(unitR+1,unitC-1)).getOwner() == player){
        support++;
    }
    if(game.getUnitAt(new Position(unitR,unitC-1)) != null && game.getUnitAt(new Position(unitR,unitC-1)).getOwner() == player){
        support++;
    }
    if(game.getUnitAt(new Position(unitR-1,unitC-1)) != null && game.getUnitAt(new Position(unitR-1,unitC-1)).getOwner() == player){
        support++;
    }
    return support;
  }

}
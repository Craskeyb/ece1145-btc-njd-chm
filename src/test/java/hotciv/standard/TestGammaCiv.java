package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/** Skeleton class for AlphaCiv test cases

    Updated Oct 2015 for using Hamcrest matchers

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
public class TestGammaCiv {
  
  private Game game;


  /** Fixture for gammaciv testing. */
  @Before
  public void setUp() {
    game = new GameImpl(new GammaCivFactory());
  }

  /*Tests for GammaCiv architecture. Utilizes strategy implementation for Archer and Settler 
   * units and takes advantage of their built-in action handler functions rather than the gameImpl's 
   * performUnitActionAt() method. In order to run GammaCiv as opposed to AlphaCiv, the units instantiated in the
   * unitMap would be replaced to the new settler and archer objects, and performUnitActionAt() would be refactored
   * to call their respective action methods.
   */

  @Test
  public void settlerAction() {
    //Red settler is at 4,3
    game.performUnitActionAt(new Position(4,3));
    assertThat(game.getUnitAt(new Position(4,3)), is(nullValue(null)));
    assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(game.getPlayerInTurn()));
  }

  @Test
  public void archerAction() {
    Unit archer = game.getUnitAt(new Position(2,0));
    assertThat(archer.getDefensiveStrength(), is(3));
    game.performUnitActionAt(new Position(2,0));
    assertThat(archer.getDefensiveStrength(),is(6));
    game.performUnitActionAt(new Position(2,0));
    assertThat(archer.getDefensiveStrength(), is(3));
  }
}

  /** REMOVE ME. Not a test of HotCiv, just an example of what
      matchers the hamcrest library has... */
//   @Test
//   public void shouldDefinetelyBeRemoved() {
//     // Matching null and not null values
//     // 'is' require an exact match
//     String s = null;
//     assertThat(s, is(nullValue()));
//     s = "Ok";
//     assertThat(s, is(notNullValue()));
//     assertThat(s, is("Ok"));

//     // If you only validate substrings, use containsString
//     assertThat("This is a dummy test", containsString("dummy"));

//     // Match contents of Lists
//     List<String> l = new ArrayList<String>();
//     l.add("Bimse");
//     l.add("Bumse");
//     // Note - ordering is ignored when matching using hasItems
//     assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

//     // Matchers may be combined, like is-not
//     assertThat(l.get(0), is(not("Bumse")));
//   }
// }



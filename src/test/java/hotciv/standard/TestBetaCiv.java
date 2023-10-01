package hotciv.standard;
import hotciv.framework.AgingStrategy;

import org.junit.*;
import static org.junit.Assert.*;
public class TestBetaCiv {
    /* Develop the BetaCiv variant using TDD by refactoring the AlphaCiv production
     * code. Both variants must be maintained.
     * 1. Sketch a compositional design for the HotCiv system that supports the variants.
     * 2. Refactor the AlphaCiv production code to implement your design. Ensure your
     *    AlphaCiv passes all test cases before starting to implement BetaCiv. I advise to
     *    put common code into a package, like hotciv.common, and variant code in some
     *    other package, like hotciv.variants.
     * 3. Implement the BetaCiv variant using TDD.
     *
     * Exercise 36.5 (BetaCiv) - Christian
     * Identical to AlphaCiv with the following changes:
     * Winner is the first player who conquers all cities
     * World aging has a new algorithm to be followed (p. 463 in TB)
     * Between 4000BC and 100BC    100 years pass per round.
     * Around birth of Christ  the sequence is -100, -1, +1, +50.
     * Between 50AD and 1750   50 years pass per round.
     * Between 1750 and 1900   25 years pass per round.
     * Between 1900 and 1970   5 years per round.
     * After 1970  1 year per round.
     */
    private AgingStrategy agingStrategy;

    @Before
    public void setUp()
    {
        agingStrategy = new BetaAgingStrategy();
    }

    @Test
    public void endOfRoundDecreases100Years()
    {
        assertEquals(-3900, agingStrategy.ageWorld(-4000));
    }

    @Test
    public void agingAroundBirthOfChrist()
    {
        assertEquals(-100, agingStrategy.ageWorld(-200));
        assertEquals(-1, agingStrategy.ageWorld(-100));
        assertEquals(1, agingStrategy.ageWorld(-1));
        assertEquals(50, agingStrategy.ageWorld(1));
    }

    @Test
    public void endOfRoundIncreases50Years()
    {
        assertEquals(100, agingStrategy.ageWorld(50));
    }

    @Test
    public void endOfRoundIncreases25Years()
    {
        assertEquals(1775, agingStrategy.ageWorld(1750));
    }

    @Test
    public void endOfRoundIncreases5Years()
    {
        assertEquals(1905, agingStrategy.ageWorld(1900));
    }

    @Test
    public void endOfRoundIncreases1Year()
    {
        assertEquals(2001, agingStrategy.ageWorld(2000));
    }
}










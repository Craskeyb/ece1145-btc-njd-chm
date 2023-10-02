package hotciv.standard;

import hotciv.framework.AgingStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBetaCiv {
    // Member variables

    @Before
    public void setUp() {
        // Initialization code
        agingStrategy = new BetaAgingStrategy();
    }

    @Test
    public void endOfRoundDecreases100Years() {
        // Test code
        assertEquals(-3900, agingStrategy.ageWorld(-4000));
    }

    @Test
    public void agingAroundBirthOfChrist() {
        // Test code
        assertEquals(-100, agingStrategy.ageWorld(-200));
        assertEquals(-1, agingStrategy.ageWorld(-100));
        assertEquals(1, agingStrategy.ageWorld(-1));
        assertEquals(50, agingStrategy.ageWorld(1));
    }

    @Test
    public void endOfRoundIncreases50Years() {
        // Test code
        assertEquals(100, agingStrategy.ageWorld(50));
    }

    @Test
    public void endOfRoundIncreases25Years() {
        // Test code
        assertEquals(1775, agingStrategy.ageWorld(1750));
    }

    @Test
    public void endOfRoundIncreases5Years() {
        // Test code
        assertEquals(1905, agingStrategy.ageWorld(1900));
    }

    @Test
    public void endOfRoundIncreases1Year() {
        // Test code
        assertEquals(2001, agingStrategy.ageWorld(2000));
    }

    // Member variables
    private AgingStrategy agingStrategy;
}
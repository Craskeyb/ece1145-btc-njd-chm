package hotciv.standard;

import hotciv.framework.AgingStrategy;

public class SimpleAgingStrategy implements AgingStrategy {

    @Override
    public int ageWorld(int currentAge) {
        return currentAge - 100;
    }
}

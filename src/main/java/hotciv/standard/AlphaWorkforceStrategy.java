package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.WorkforceStrategy;

public class AlphaWorkforceStrategy implements WorkforceStrategy {
    @Override
    public void updateTreasury(City city) {
        city.setTreasury(city.getTreasury() + 6);
    }
}

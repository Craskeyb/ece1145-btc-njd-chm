package hotciv.standard;

import hotciv.framework.AgingStrategy;

public class BetaAgingStrategy implements AgingStrategy {
    public int ageWorld(int currentAge) {
        if (currentAge < -100) {
            // Between -4000 BC and -100 BC, 100 years pass per round
            return currentAge + 100;
        } else if (currentAge == -100) {
            // Around the birth of Christ, the sequence is -100, -1, +1, +50
            return -1;
        } else if (currentAge == -1) {
            return 1;
        } else if (currentAge == 1) {
            return 50;
        } else if (currentAge >= 50 && currentAge < 1750) {
            // Between 50 AD and 1750 AD, 50 years pass per round
            return currentAge + 50;
        } else if (currentAge >= 1750 && currentAge < 1900) {
            // Between 1750 AD and 1900 AD, 25 years pass per round
            return currentAge + 25;
        } else if (currentAge >= 1900 && currentAge < 1970) {
            // Between 1900 AD and 1970 AD, 5 years pass per round
            return currentAge + 5;
        } else {
            // After 1970 AD, 1 year passes per round
            return currentAge + 1;
        }
    }
}
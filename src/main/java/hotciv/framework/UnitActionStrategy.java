package hotciv.framework;

import java.util.HashMap;

public interface UnitActionStrategy {
    public void performUnitActionAt(Position p, HashMap<Position, Unit> unitMap, Game game);
}

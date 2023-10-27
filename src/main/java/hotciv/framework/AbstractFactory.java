package hotciv.framework;

public interface AbstractFactory {
    AgingStrategy createAgingStrategy();
    GameSetupStrategy createGameSetup();
    WinningStrategy createWinningStrategy();

    // Need to refactor what we have and create a UnitActionStrategy implementation
    //UnitActionStrategy createUnitActionStrategy();
}

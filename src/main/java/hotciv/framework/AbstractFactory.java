package hotciv.framework;

public interface AbstractFactory {
    AgingStrategy createAgingStrategy();
    GameSetupStrategy createGameSetup();
    WinningStrategy createWinningStrategy();
    UnitActionStrategy createUnitActionStrategy();
    AttackStrategy createAttackStrategy();
    WorkforceStrategy createWorkforceStrategy();
    ProductionChangeStrategy createProductionChangeStrategy();
}

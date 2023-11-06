package hotciv.framework;

public interface ProductionChangeStrategy {
    public void changeProduction(Game game, Position p, String unitType);
}

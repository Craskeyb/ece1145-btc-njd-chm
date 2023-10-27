package hotciv.framework;

public interface AttackStrategy {
    public Boolean decideAttack(Game game, GameSetupStrategy mapStrategy, Position to, Position from);
}

package dungeonmania.goals;

import dungeonmania.Game;

public class Treasure extends Goal {
    private int target;
    public Treasure(int target) {
        this.target = target;
    }
    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        return game.getCollectedTreasureCount() >= target;
    }
    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":treasure";
    }
}

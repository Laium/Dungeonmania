package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.enemies.ZombieToastSpawner;

public class EnemyGoal extends Goal {
    private int targetEnemies;
    private boolean spawnerGoal;

    public EnemyGoal(int target) {
        targetEnemies = target;
    }
    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        if (game.getMap().getEntities(ZombieToastSpawner.class).size() <= 0) {
            spawnerGoal = true;
        }

        return (game.getEnemiesKilled() == targetEnemies && spawnerGoal);
    }
    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":enemy_goal";
    }
}

package dungeonmania.entities.enemies.enemyChanges;

import java.util.List;
import java.util.Random;

import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class RandomInvisible {

    public static Position randomOrInvisible(GameMap map, Enemy enemy, Position nextPos) {
        Random randGen = new Random();

        List<Position> pos = enemy.getPosition().getCardinallyAdjacentPositions();
            pos = pos.stream().filter(p -> map.canMoveTo(enemy, p)).toList();
            if (pos.size() == 0) {
                nextPos = enemy.getPosition();
            } else {
                nextPos = pos.get(randGen.nextInt(pos.size()));
            }
        return nextPos;
    }
}

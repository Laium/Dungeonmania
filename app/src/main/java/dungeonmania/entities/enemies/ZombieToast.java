package dungeonmania.entities.enemies;


import dungeonmania.Game;
import dungeonmania.entities.PotionListener;
import dungeonmania.entities.collectables.potions.Potion;
import dungeonmania.entities.enemies.enemyChanges.InvincibleRunaway;
import dungeonmania.entities.enemies.enemyChanges.RandomInvisible;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy implements PotionListener {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    private String movementType = "random";

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
    }

    @Override
    public void move(Game game) {
        Position nextPos = null;
        GameMap map = game.getMap();
        switch (movementType) {
        case "random":
            nextPos = RandomInvisible.randomOrInvisible(map, this, nextPos);
            break;
        case "runAway":
            nextPos = InvincibleRunaway.invincibleOrRunaway(map, this);
            break;
        default:
            break;
        }
        game.getMap().moveTo(this, nextPos);

    }

    @Override
    public void notifyPotion(Potion potion) {
        movementType = AllPotions.potions(potion, this);
    }

    @Override
    public void notifyNoPotion() {
        movementType = "random";
    }

}

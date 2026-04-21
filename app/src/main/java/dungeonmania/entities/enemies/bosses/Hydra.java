package dungeonmania.entities.enemies.bosses;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.battles.HydraBattleStatistics;
import dungeonmania.entities.enemies.ZombieToast;
import dungeonmania.util.Position;

public class Hydra extends ZombieToast {
    public static final double DEFAULT_HEAL_RATE = 0.3;
    public static final double DEFAULT_HEAL_AMOUNT = 3;
    public static final double DEFAULT_HEALTH = 15.0;
    public static final double DEFAULT_ATTACK = 5.0;
    public Hydra(Position position, double health, double attack, double healthIncreaseRate,
                double healthIncreaseAmount) {
        super(position, health, attack);
        //TODO Auto-generated constructor stub
        setBattleStatistics(new HydraBattleStatistics(
            health,
            attack,
            0,
            BattleStatistics.DEFAULT_DAMAGE_MAGNIFIER,
            BattleStatistics.DEFAULT_ENEMY_DAMAGE_REDUCER,
            healthIncreaseRate,
            healthIncreaseAmount
        ));
    }
}

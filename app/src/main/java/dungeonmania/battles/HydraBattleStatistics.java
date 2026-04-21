package dungeonmania.battles;

import dungeonmania.entities.enemies.bosses.Hydra;

public class HydraBattleStatistics extends BattleStatistics {
    public static final double DEFAULT_DAMAGE_MAGNIFIER = 1.0;
    public static final double DEFAULT_PLAYER_DAMAGE_REDUCER = 10.0;
    public static final double DEFAULT_ENEMY_DAMAGE_REDUCER = 5.0;
    private double healthIncreaseRate = Hydra.DEFAULT_HEAL_RATE;
    private double healthIncreaseAmount = Hydra.DEFAULT_HEAL_AMOUNT;
    public HydraBattleStatistics(double health, double attack, double defence,
                                 double attackMagnifier, double damageReducer,
                                 double healthIncreaseRate, double healthIncreaseAmount) {
        super(health, attack, defence, attackMagnifier, damageReducer);
        this.healthIncreaseRate = healthIncreaseRate;
        this.healthIncreaseAmount = healthIncreaseAmount;
    }

@Override
public void setHealth(double newHealth) {
    if (newHealth < this.getHealth() && Math.random() < healthIncreaseRate) {
        super.setHealth(this.getHealth() + healthIncreaseAmount);
    } else {
        super.setHealth(newHealth);
    }
}

}

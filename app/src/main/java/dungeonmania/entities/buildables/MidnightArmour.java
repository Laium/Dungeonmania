package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.collectables.Useable;
import dungeonmania.entities.enemies.ZombieToast;

public class MidnightArmour extends BuildableFight implements Useable {
    public static final double MAX_DURABILITY = 99;

    private double attack;
    private double defence;

    public MidnightArmour(int attack, int defence) {
        super(null);
        this.attack = attack;
        this.defence = defence;
    }


    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, attack, defence, 1, 1));
    }

    @Override
    public void use(Game game) {
        return;
    }

    @Override
    public int getDurability() {
        return 99;
    }

    public boolean unlockStats(Game game) {
        return game.getEntities(ZombieToast.class).size() > 0;
    }

}

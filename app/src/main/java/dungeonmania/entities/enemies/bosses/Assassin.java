package dungeonmania.entities.enemies.bosses;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.entities.enemies.Mercenary;
import dungeonmania.util.Position;

public class Assassin extends Mercenary {
    public static final double DEFAULT_BRIB_FAIL_RATE = 0.5;
    public static final double DEFAULT_ATTACK = 25.0;
    public static final double DEFAULT_HEALTH = 50.0;

    private double bribeFailRate = Assassin.DEFAULT_BRIB_FAIL_RATE;

    public Assassin(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence, double bribeFailRate) {
        super(position, health, attack, bribeAmount, bribeRadius, allyAttack, allyDefence);
        //TODO Auto-generated constructor stub
        this.bribeFailRate = bribeFailRate;
    }

    @Override
    public void interact(Player player, Game game) {
        if (Math.random() > bribeFailRate) {
            super.interact(player, game);
        }
        return;
    }

}

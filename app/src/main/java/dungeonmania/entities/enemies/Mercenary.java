package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.PotionListener;
import dungeonmania.entities.buildables.Sceptre;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.potions.Potion;
import dungeonmania.entities.enemies.enemyChanges.InvincibleRunaway;
import dungeonmania.entities.enemies.enemyChanges.RandomInvisible;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Mercenary extends Enemy implements Interactable, PotionListener {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;

    private int bribeAmount = Mercenary.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Mercenary.DEFAULT_BRIBE_RADIUS;

    private double allyAttack;
    private double allyDefence;
    private boolean allied = false;
    private boolean wasAdjacentToPlayer = false;
    private int mindControlDuration = 0;
    private boolean mindControlled = false;
    private String movementType = "hostile";

    public Mercenary(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
    }

    public boolean isAllied() {
        return allied;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (allied)
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current merc can be bribed
     */
    private boolean canBeBribed(Player player) {
        return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    private boolean canBeMindControlled(Player player) {
        return player.countEntityOfType(Sceptre.class) >= 1;
    }

    /**
     * bribe the merc
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }

    }

    @Override
    public void interact(Player player, Game game) {
        if (canBeBribed(player)) {
            bribe(player);
        } else {
            mindControlDuration = ((Sceptre) player.getSceptre()).getMindControlDuration();
            mindControlled = true;
        }
        allied = true;
        movementType = "allied";
    }

    @Override
    public void move(Game game) {
        Position nextPos = null;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        switch (movementType) {
        case "allied":
            boolean isAdjacentToPlayer = Position.isAdjacent(player.getPosition(), getPosition());
            if (wasAdjacentToPlayer && !isAdjacentToPlayer) {
                nextPos = player.getPreviousDistinctPosition();
            } else {
                // If currently still adjacent, wait in place. Else pursue the player.
                nextPos = isAdjacentToPlayer ? getPosition()
                        : map.dijkstraPathFind(getPosition(), player.getPosition(), this);
                wasAdjacentToPlayer = Position.isAdjacent(player.getPosition(), nextPos);
            }

            if (mindControlled) {
                mindControlDuration--;
                if (mindControlDuration <= 0) {
                    mindControlled = false;
                    allied = false;
                }
            }
            break;
        case "invisible":
            // Move random
            nextPos = RandomInvisible.randomOrInvisible(map, this, nextPos);
            map.moveTo(this, nextPos);
            break;
        case "invincible":
        nextPos = InvincibleRunaway.invincibleOrRunaway(map, this);
            break;
        case "hostile":
            nextPos = map.dijkstraPathFind(getPosition(), player.getPosition(), this);
            break;
        default:
            break;
        }
        map.moveTo(this, nextPos);
    }

    @Override
    public boolean isInteractable(Player player) {
        return !allied && (canBeBribed(player) || canBeMindControlled(player));
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!allied)
            return super.getBattleStatistics();
        return new BattleStatistics(0, allyAttack, allyDefence, 1, 1);
    }

    @Override
    public void notifyPotion(Potion potion) {
        if (allied)
            return;
        movementType = AllPotions.potions(potion, this);
    }

    @Override
    public void notifyNoPotion() {
        if (allied)
            return;

        movementType = "hostile";
    }
}

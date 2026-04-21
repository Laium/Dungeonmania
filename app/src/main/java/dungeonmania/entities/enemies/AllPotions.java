package dungeonmania.entities.enemies;

import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.entities.collectables.potions.Potion;

public class AllPotions {
        public static String potions(Potion potion, Enemy enemy) {
            if (potion instanceof InvincibilityPotion) {
                if (enemy instanceof ZombieToast) {
                    return "runAway";
                }
                if (enemy instanceof Mercenary) {
                    return "invincible";
                }
            }
            if (potion instanceof InvisibilityPotion) {
                if (enemy instanceof Mercenary) {
                    return "invincible";
                }
            }
            return "random";
        }

}

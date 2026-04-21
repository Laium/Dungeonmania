package dungeonmania.entities.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.Player;
import dungeonmania.entities.buildables.Bow;
import dungeonmania.entities.buildables.Sceptre;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.Sunstone;
import dungeonmania.entities.collectables.Sword;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Useable;
import dungeonmania.entities.collectables.Wood;

public class Inventory {
    private List<InventoryItem> items = new ArrayList<>();

    public boolean add(InventoryItem item) {
        items.add(item);
        return true;
    }

    public void remove(InventoryItem item) {
        items.remove(item);
    }

    // Get the list of possible buildables
    public List<String> getBuildables() {

        List<String> result = new ArrayList<>();

        if (canCraftBow()) {
            result.add("bow");
        }
        if (canCraftShield()) {
            result.add("shield");
        }
        if (canCraftSceptre()) {
            result.add("sceptre");
        }
        if (canCraftMidnightArmour()) {
            result.add("midnight_armour");
        }
        return result;
    }

    // Check whether a player has the supplies to build a particular buildable. If so, build the item.
    // Currently since there are only two buildables we have a boolean to keep track of which buildable it is.
    public InventoryItem checkBuildCriteria(Player p, String craftItem, EntityFactory factory) {

        switch (craftItem) {
            case "bow":
                craftBow();
                return factory.buildBow();
            case "shield":
                craftShield();
                return factory.buildShield();
            case "sceptre":
                craftSceptre();
                return factory.buildSceptre();
            case "midnight_armour":
                craftMidnightArmour();
                return factory.buildMidnightArmour();
            default:
                break;
        }
        return null;
    }

    public boolean canCraftBow() {
        int wood = count(Wood.class);
        int arrows = count(Arrow.class);

        return (wood >= 1 && arrows >= 3);
    }

    public boolean canCraftShield() {
        int wood = count(Wood.class);
        int treasure = count(Treasure.class);
        int keys = count(Key.class);
        int stones = count(Sunstone.class);
        return (wood >= 2 && ((treasure >= 1 || keys >= 1) || stones >= 1));
    }

    public boolean canCraftSceptre() {
        int wood = count(Wood.class);
        int arrows = count(Arrow.class);
        int treasure = count(Treasure.class);
        int keys = count(Key.class);
        int stones = count(Sunstone.class);
        boolean enoughKeysorTreasure = (keys >= 1 || treasure >= 1);
        boolean enoughWoodorArrows = (wood >= 1 || arrows >= 2);
        boolean normalRecipe = (enoughWoodorArrows && enoughKeysorTreasure && stones >= 1);
        boolean replacementRecipe = enoughWoodorArrows && (!enoughKeysorTreasure && stones >= 2);

        return (normalRecipe || replacementRecipe);
    }

    public boolean canCraftMidnightArmour() {
        int swords = count(Sword.class);
        int stones = count(Sunstone.class);
        return (swords >= 1 && stones >= 1);
    }

    public void craftBow() {
        List<Wood> wood = getEntities(Wood.class);
        List<Arrow> arrows = getEntities(Arrow.class);

        removeItem(arrows, 3);
        removeItem(wood, 1);
    }

    public void craftShield() {
        List<Wood> wood = getEntities(Wood.class);
        List<Treasure> treasure = getEntities(Treasure.class);
        List<Key> keys = getEntities(Key.class);

        removeItem(wood, 2);
        if (treasure.size() >= 1) {
            removeItem(treasure, 1);
        } else if (keys.size() >= 1) {
            removeItem(keys, 1);
        }
    }

    public void craftSceptre() {
        List<Wood> wood = getEntities(Wood.class);
        List<Treasure> treasure = getEntities(Treasure.class);
        List<Key> keys = getEntities(Key.class);
        List<Arrow> arrows = getEntities(Arrow.class);
        List<Sunstone> stones = getEntities(Sunstone.class);

        if (wood.size() >= 1) {
            removeItem(wood, 1);
        } else {
            removeItem(arrows, 2);
        }

        if (keys.size() >= 1) {
            removeItem(keys, 1);
        } else if (treasure.size() >= 1) {
            removeItem(treasure, 1);
        }

        removeItem(stones, 1);
    }

    public void craftMidnightArmour() {
        List<Sword> swords = getEntities(Sword.class);
        List<Sunstone> stones = getEntities(Sunstone.class);
        removeItem(stones, 1);
        removeItem(swords, 1);
    }

    public void removeItem(List<?> item, int amount) {
        for (int i = 0; i < amount; i++) {
            items.remove(item.get(i));
        }
    }

    public <T extends InventoryItem> T getFirst(Class<T> itemType) {
        for (InventoryItem item : items)
            if (itemType.isInstance(item))
                return itemType.cast(item);
        return null;
    }

    public <T extends InventoryItem> int count(Class<T> itemType) {
        int count = 0;
        for (InventoryItem item : items)
            if (itemType.isInstance(item))
                count++;
        return count;
    }

    public Entity getEntity(String itemUsedId) {
        for (InventoryItem item : items)
            if (item.getId().equals(itemUsedId))
                return item;
        return null;
    }

    public List<Entity> getEntities() {
        return items.stream().map(Entity.class::cast).collect(Collectors.toList());
    }

    public <T> List<T> getEntities(Class<T> clz) {
        return items.stream().filter(clz::isInstance).map(clz::cast).collect(Collectors.toList());
    }

    public boolean hasWeapon() {
        return getFirst(Sword.class) != null || getFirst(Bow.class) != null;
    }

    public Useable getWeapon() {
        Useable weapon = getFirst(Sword.class);
        if (weapon == null)
            return getFirst(Bow.class);
        return weapon;
    }

    public Useable getSceptre() {
        Useable sceptre = getFirst(Sceptre.class);
        return sceptre;
    }

}

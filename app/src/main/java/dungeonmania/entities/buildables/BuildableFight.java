package dungeonmania.entities.buildables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItemFight;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public abstract class BuildableFight extends InventoryItemFight {
    public BuildableFight(Position position) {
        super(position);
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        return;
    }

}

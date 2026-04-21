package dungeonmania.entities.inventory;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.util.Position;

public abstract class InventoryItemFight extends InventoryItem  {
    public InventoryItemFight(Position position) {
        super(position);
    }

    // These methods are specific to battle-related items
    public abstract BattleStatistics applyBuff(BattleStatistics origin);
    public abstract int getDurability();
}

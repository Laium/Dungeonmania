
package dungeonmania.Task2Tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SunstoneTests {
    @Test
    @DisplayName("Sun stone can open door and treasure goal is completed")
    public void unlockDoorAndTreasureGoal() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_UnlockDoorAndTreasureGoal",
                "c_sunStoneTest_UnlockDoorAndTreasureGoal");

        // pick up sunStone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // check goal not completed
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));


        // walk through door and check still sun stone exists
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(new Position(3, 1), TestUtils.getEntities(res, "player").get(0).getPosition());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @DisplayName("Have both sun stone and key and door opens")
    public void unlockDoorHaveBoth() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_UnlockDoorHaveBoth",
                "c_sunStoneTest_UnlockDoorHaveBoth");

        // pick up sunStone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "key").size());
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // walk through door and check still sun stone exists
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertNotEquals(1, TestUtils.getInventory(res, "key").size());
        assertEquals(new Position(4, 1), TestUtils.getEntities(res, "player").get(0).getPosition());
    }

    @Test
    @DisplayName("Crafting Shield with sun stone")
    public void craftShieldStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_CraftShieldAllPaths",
                "c_sunStoneTest_CraftShieldAllPaths");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(2, TestUtils.getInventory(res, "wood").size());

        assertEquals(0, TestUtils.getInventory(res, "shield").size());
        res = assertDoesNotThrow(() -> dmc.build("shield"));
        assertEquals(1, TestUtils.getInventory(res, "shield").size());

        // since sun stone was replacement, it was not consumed
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
    }

    @Test
    @DisplayName("Crafting Shield with sun stone, key and treasure")
    public void craftShieldStoneKeyTreasure() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_CraftShieldAllPaths",
                "c_sunStoneTest_CraftShieldAllPaths");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(2, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        assertEquals(0, TestUtils.getInventory(res, "shield").size());
        res = assertDoesNotThrow(() -> dmc.build("shield"));
        assertEquals(1, TestUtils.getInventory(res, "shield").size());

        // its a tie but logic will have treasures used in a tie
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());

    }

    @Test
    @DisplayName("Crafting Shield with sun stone, key")
    public void craftShieldStoneKey() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_CraftShieldAllPaths",
                "c_sunStoneTest_CraftShieldAllPaths");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);


        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(2, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        assertEquals(0, TestUtils.getInventory(res, "shield").size());
        res = assertDoesNotThrow(() -> dmc.build("shield"));
        assertEquals(1, TestUtils.getInventory(res, "shield").size());

        // key should be used
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());

    }

    @Test
    @DisplayName("Crafting Shield with sun stone, Treasure")
    public void craftShieldStoneTreasure() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sunStoneTest_CraftShieldAllPaths",
                "c_sunStoneTest_CraftShieldAllPaths");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.UP);


        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(2, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        assertEquals(0, TestUtils.getInventory(res, "shield").size());
        res = assertDoesNotThrow(() -> dmc.build("shield"));
        assertEquals(1, TestUtils.getInventory(res, "shield").size());

        // treasure should be used
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());

    }
}



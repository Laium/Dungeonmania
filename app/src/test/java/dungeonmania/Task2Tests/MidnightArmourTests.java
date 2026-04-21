package dungeonmania.Task2Tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

public class MidnightArmourTests {

    @Test
    @DisplayName("Test craft armour and no Zombies")
    public void craftArmourNoZombies() {
        // player health is 1, attack is 4
        // merc health is 5, attack is 5
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_NoZombies", "c_midnightArmourTest_NoZombies");

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());

        List<EntityResponse> entities = res.getEntities();

        // if armour works, merc should be dead
        assertEquals(0, TestUtils.countEntityOfType(entities, "mercenary"));
    }

    @Test
    @DisplayName("Test craft armour and Zombies from spawner")
    public void craftArmourZombies() {
        // player health is 1, attack is 4
        // merc health is 5, attack is 5
        // zombie spawn rate is 1
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_ZombieSpawner", "c_midnightArmourTest_ZombieSpawner");

        assertEquals(0, TestUtils.getEntities(res, "zombie_toast").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // zombie should spawn
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast").size());

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());

        List<EntityResponse> entities = res.getEntities();

        // if armour detects zombies, player should be dead
        assertEquals(0, TestUtils.countEntityOfType(entities, "player"));
    }

    @Test
    @DisplayName("Test craft when insufficient materials")
    public void craftArmour() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_midnightArmourTest_ZombieSpawner", "c_midnightArmourTest_ZombieSpawner");

        // none of the items
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());

        // half of the items
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));

        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
        assertEquals(0, TestUtils.getInventory(res, "sword").size());

        List<EntityResponse> entities = res.getEntities();

        // if armour detects zombies, player should be dead
        assertEquals(0, TestUtils.countEntityOfType(entities, "player"));
    }
}

package dungeonmania.Task2Tests;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import dungeonmania.exceptions.*;
import dungeonmania.mvp.TestUtils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SceptreTests {

    @Test
    @DisplayName("Test InvalidActionException no Items")
    public void noItemsInvalidActionException() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertThrows(InvalidActionException.class, () -> dmc.build("sceptre"));

    }

    @Test
    @DisplayName("Test building with arrows, key, sun stone")
    public void buildArrowKeyStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick 2 arrows
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pickup key
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        // Pickup sun stone
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with arrows, treasure, sun stone")
    public void buildArrowTreasureStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick 2 arrows
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pickup key
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        // Pickup sun stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with wood, key, sun stone")
    public void buildWoodKeyStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick up 1 wood
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pickup key
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        // Pickup sun stone
        res = dmc.tick(Direction.LEFT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with wood, treasure, sun stone")
    public void buildWoodTreasureStone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick up 1 wood
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pickup key
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        // Pickup sun stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with wood,2 sun stone")
    public void buildWood2Stone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick up 1 wood
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pickup sun stone
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // Pickup sun stone
        res = dmc.tick(Direction.LEFT);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory but since
        // sun stone was also used as a replacement, it retains one
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with arrows, 2 sun stone")
    public void buildArrow2Stone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick 2 arrows
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pickup stone
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // Pickup sun stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory, 1 sun stone is retained
        // as 1 was used as itself and the other as replacement
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with arrows, key, 2 sun stone")
    public void buildArrowKey2Stone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick 2 arrows
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pickup stone
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // Pickup sun stone
        res = dmc.tick(Direction.LEFT);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());

        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "key").size());


        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory, since there is enough
        // keys, we only use sunstones once
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with arrows, treasure, 2 sun stone")
    public void buildArrowTreasure2Stone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick 2 arrows
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(2, TestUtils.getInventory(res, "arrow").size());

        // Pickup stone
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // Pickup sun stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());

        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());


        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory, since there is enough
        // treasure, we only use sunstones once
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with wood, key, 2 sun stone")
    public void buildWoodKey2Stone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick up 1 wood
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pickup sun stone
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // Pickup sun stone
        res = dmc.tick(Direction.LEFT);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());

        // pickup Key
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "key").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory, since there is enough
        // key, we use sun stones only once
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Test building with wood, treasure, 2 sun stone")
    public void buildWoodTreasure2Stone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_AllComponents", "c_sceptreTest_AllComponents");
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "sunstone").size());

        // Pick up 1 wood
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "wood").size());

        // Pickup sun stone
        res = dmc.tick(Direction.UP);
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());

        // Pickup sun stone
        res = dmc.tick(Direction.RIGHT);
        assertEquals(2, TestUtils.getInventory(res, "sunstone").size());

        // pickup treasure
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "treasure").size());

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // Materials used in construction disappear from inventory, since there is enough
        // treasure, we only use sunstones once
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(1, TestUtils.getInventory(res, "sunstone").size());
    }

    @Test
    @DisplayName("Testing mind control")
    public void mindControl() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreTest_mindControl", "c_sceptreTest_mindControl");
        // spawn 3 mercs
        String mindControlMerc = TestUtils.getEntities(res, "mercenary").get(0).getId();
        Position mindControlMercPosition = TestUtils.getEntities(res, "mercenary").get(0).getPosition();
        // ensure our mindcontrol target is the correct merc
        assertEquals(new Position(0, 4), mindControlMercPosition);

        // Collect crafting materials
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        // tick 3 has all materials

        // tick 4 craft
        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        // tick 5 mind control, duration is 2
        // bribe radius is 0 so we know it is mind control
        res = assertDoesNotThrow(() -> dmc.interact(mindControlMerc));

        // tick 6 fight first merc
        // player attack is 4
        // ally bonus attack is 1
        // merc health is 5
        // duration is 1
        DungeonResponse postBattleResponse = dmc.tick(Direction.RIGHT);
        List<EntityResponse> entities = postBattleResponse.getEntities();

        // first merc should be dead
        assertEquals(2, TestUtils.countEntityOfType(entities, "mercenary"));

        // duration is now 0, ally buff ends so mindcontrol logic is correct
        postBattleResponse = dmc.tick(Direction.RIGHT);

        // 2 merc should be alive
        entities = postBattleResponse.getEntities();
        assertEquals(2, TestUtils.countEntityOfType(entities, "mercenary"));

    }
}

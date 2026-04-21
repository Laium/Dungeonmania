package dungeonmania.Task2Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class HydraTest {

    @Test
    @Tag("11-5")
    @DisplayName("Test player battles hydra, player dies cause regens too much  from 1 to 100 hp")
    public void testPlayerDiesFightBrokenHydra() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse postBattleResponse = TestUtils.genericHydraSequence(controller,
                "c_hydraTest_health");
        List<EntityResponse> entities = postBattleResponse.getEntities();

        assertTrue(TestUtils.countEntityOfType(entities, "player") == 0);
    }

    @Test
    @Tag("11-5")
    @DisplayName("Test player beats hydra, hydra rng didnt heal")
    public void testPlayerDiesWhenBattleMercenary() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse postBattleResponse = TestUtils.genericHydraSequence(controller,
                "c_hydraTest_health_fails");
        List<EntityResponse> entities = postBattleResponse.getEntities();

        assertTrue(TestUtils.countEntityOfType(entities, "hydra") == 0);
    }
        @Test
    @Tag("10-1")
    @DisplayName("Testing hydra movement")
    public void movement() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_hydraTest_movement", "c_hydraTest_movement");

        assertEquals(1, getHydras(res).size());

        // Random movement might include choosing to stay still, so we should just
        // check that they do move at least once in a few turns
        boolean hydraMoved = false;
        Position prevPosition = getHydras(res).get(0).getPosition();
        for (int i = 0; i < 5; i++) {
            res = dmc.tick(Direction.UP);
            if (!prevPosition.equals(getHydras(res).get(0).getPosition())) {
                hydraMoved = true;
                break;
            }
        }
        assertTrue(hydraMoved);
    }

    @Test
    @Tag("10-2")
    @DisplayName("Testing hydra cannot move through closed doors and walls")
    public void doorsAndWalls() {
        //  W   W   W   W
        //  P   W   Z   W
        //      W   D   W
        //          K
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_hydraTest_doorsAndWalls", "c_hydraTest_doorsAndWalls");
        assertEquals(1, getHydras(res).size());
        Position position = getHydras(res).get(0).getPosition();
        res = dmc.tick(Direction.UP);
        assertEquals(position, getHydras(res).get(0).getPosition());
    }
    private List<EntityResponse> getHydras(DungeonResponse res) {
        return TestUtils.getEntities(res, "hydra");
    }
}

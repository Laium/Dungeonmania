
package dungeonmania.Task2Tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.mvp.TestUtils;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

public class MicroEvolutionTests {
    @Test
    @DisplayName("Test Player kills Zombie, completes goal")
    public void testOnlyEnemiesNoSpawner() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame(
            "d_microEvolutionTest_onlyEnemies", "c_microEvolutionTest_EnemyGoal1");
        DungeonResponse postBattleResponse = controller.tick(Direction.RIGHT);

        List<EntityResponse> entities = postBattleResponse.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "zombie_toast") == 0);
        assertEquals("", TestUtils.getGoals(postBattleResponse));
    }

    @Test
    @DisplayName("Test Player breaks one spawner, completes goal")
        public void testOnlySpawnerNoEnemies() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame(
            "d_microEvolutionTest_onlySpawner", "c_microEvolutionTest_EnemyGoal0");
        controller.tick(Direction.RIGHT);
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        res = assertDoesNotThrow(() -> controller.interact(spawnerId));

        List<EntityResponse> entities = res.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "zombie_toast_spawner") == 0);
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @DisplayName("Reach enemy goal but still spawner exists")
        public void testEnemiesButSpawner() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse postBattleResponse = controller.newGame(
            "d_microEvolutionTest_oneEnemyOneSpawner", "c_microEvolutionTest_EnemyGoal1");
        postBattleResponse = controller.tick(Direction.RIGHT);

        List<EntityResponse> entities = postBattleResponse.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "zombie_toast_spawner") == 1);
        assertEquals(":enemy_goal", TestUtils.getGoals(postBattleResponse));
    }

    @Test
    @DisplayName("Got Spawners but still enemy goal needed")
        public void testSpawnersbutEnemyGoal() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame(
            "d_microEvolutionTest_oneSpawnerOneEnemy", "c_microEvolutionTest_EnemyGoal1");
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();
        controller.tick(Direction.RIGHT);
        res = assertDoesNotThrow(() -> controller.interact(spawnerId));

        List<EntityResponse> entities = res.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "zombie_toast_spawner") == 0);
        assertEquals(":enemy_goal", TestUtils.getGoals(res));
    }

    @Test
    public void andAll() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame(
            "d_microEvolutionTest_multipleGoalsAndAll", "c_microEvolutionTest_multipleGoalsAndAll");

        assertTrue(TestUtils.getGoals(res).contains(":exit"));
        assertTrue(TestUtils.getGoals(res).contains(":enemy_goal"));
        assertTrue(TestUtils.getGoals(res).contains(":boulders"));

        // kill spider
        res = dmc.tick(Direction.RIGHT);
        assertTrue(TestUtils.getGoals(res).contains(":exit"));
        assertFalse(TestUtils.getGoals(res).contains(":enemy_goal"));
        assertTrue(TestUtils.getGoals(res).contains(":boulders"));

        // move boulder onto switch
        res = dmc.tick(Direction.RIGHT);
        assertTrue(TestUtils.getGoals(res).contains(":exit"));
        assertFalse(TestUtils.getGoals(res).contains(":enemy_goal"));
        assertFalse(TestUtils.getGoals(res).contains(":boulders"));

        res = dmc.tick(Direction.DOWN);
        assertTrue(TestUtils.getGoals(res).contains(":exit"));
        assertFalse(TestUtils.getGoals(res).contains(":enemy_goal"));
        assertFalse(TestUtils.getGoals(res).contains(":boulders"));

        // move to exit
        res = dmc.tick(Direction.DOWN);
        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    public void testMultipleGoalsOrAll() {
        DungeonManiaController controller = new DungeonManiaController();
        controller.newGame(
            "d_microEvolutionTest_multipleGoalsOrAll", "c_microEvolutionTest_multipleGoalsOrAll");
        DungeonResponse postBattleResponse = controller.tick(Direction.RIGHT);

        List<EntityResponse> entities = postBattleResponse.getEntities();
        assertTrue(TestUtils.countEntityOfType(entities, "zombie_toast") == 0);
        assertEquals("", TestUtils.getGoals(postBattleResponse));
    }

}

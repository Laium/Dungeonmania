package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals;
        return switch (jsonGoal.getString("goal")) {
            case "AND" -> {
                subgoals = jsonGoal.getJSONArray("subgoals");
                yield new And(createGoal(subgoals.getJSONObject(0), config),
                        createGoal(subgoals.getJSONObject(1), config));
            }
            case "OR" -> {
                subgoals = jsonGoal.getJSONArray("subgoals");
                yield new Or(createGoal(subgoals.getJSONObject(0), config),
                        createGoal(subgoals.getJSONObject(1), config));
            }
            case "exit" -> new ExitGoal();
            case "boulders" -> new Boulders();
            case "treasure" -> {
                int treasureGoal = config.optInt("treasure_goal", 1);
                yield new Treasure(treasureGoal);
            }
            case "enemy_goal" -> {
                int enemyGoal = config.optInt("enemy_goal", 0);
                yield new EnemyGoal(enemyGoal);
            }
            default -> null;
        };
    }
}

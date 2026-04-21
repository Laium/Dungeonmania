// package dungeonmania.goals;

// import java.util.List;

// import dungeonmania.Game;
// import dungeonmania.entities.Entity;
// import dungeonmania.entities.Exit;
// import dungeonmania.entities.Player;
// import dungeonmania.entities.Switch;
// import dungeonmania.util.Position;

// public class Goal {
//     private String type;
//     private int target;
//     private Goal goal1;
//     private Goal goal2;

//     public Goal(String type) {
//         this.type = type;
//     }

//     public Goal(String type, int target) {
//         this.type = type;
//         this.target = target;
//     }

//     public Goal(String type, Goal goal1, Goal goal2) {
//         this.type = type;
//         this.goal1 = goal1;
//         this.goal2 = goal2;
//     }

//     /**
//      * @return true if the goal has been achieved, false otherwise
//      */
//     public boolean achieved(Game game) {
//         if (game.getPlayer() == null)
//             return false;
//         switch (type) {
//         default:
//             break;
//         }
//         return false;
//     }

//     public String toString(Game game) {
//         if (this.achieved(game))
//             return "";
//         switch (type) {
//         default:
//             return "";
//         }
//     }

// }

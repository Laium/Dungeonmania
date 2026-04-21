package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.entities.collectables.Useable;

public class Sceptre extends Buildable implements Useable {
    private int mindControlDuration;
    public Sceptre(int mindControlDuration) {
        super(null);
        this.mindControlDuration = mindControlDuration;
    }

    @Override
    public void use(Game game) {
        mindControlDuration--;
    }

    public int getMindControlDuration() {
        return mindControlDuration;
    }


}

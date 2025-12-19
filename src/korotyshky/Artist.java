package korotyshky;

import recordCor.Coordinates;
import interfaces.*;

public class Artist extends Korotyshka implements PersonDie, Targetable {
    protected Coordinates position;

    public Artist(String name, int hungerLevel, Coordinates position) {
        super(name, hungerLevel);
        this.position = position;
    }

    @Override
    public Coordinates getPosition() {
        return position;
    }

    @Override
    public void reactToBallHit() {
        System.out.println("Артист корчит рожицы");
    }

    @Override
    public void die() {
        isDead = true;
        System.out.println("Артист упал с помоста и умер!");
    }

    @Override
    public String toString() {
        return "Artist{name='" + name + "', position=" + position + ", isDead=" + isDead + "}";
    }
}
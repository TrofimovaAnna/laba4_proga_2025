package korotyshky;

import threeObjects.Mirror;
import java.util.Random;
import exceptions.LowEnergyException;


public class Kozlik extends Korotyshka implements interfaces.PersonDie {
    public Kozlik(String name, int hungerLevel) {
        super(name, hungerLevel);
    }

    public void lookIntoMirror(Mirror mirror) {
        if (isDead()) return;
        if (mirror.isBroken()) return;

        Random rand = new Random();
        int distortion = rand.nextInt(101); // 0–100
        int damage = mirror.getDamageLevel();
        int maxJoy = Math.max(0, 90 - damage);
        int finalJoy = Math.max(0, maxJoy - distortion);

        try {
            if (finalJoy > 50) {
                changeHungerLevel(-15);
                System.out.println(name + " смеётся, глядя в зеркало! Голод уменьшился.");
            } else if (finalJoy > 20) {
                System.out.println(name + " смотрится в зеркало, но ничего особенного не происходит.");
            } else {
                changeHungerLevel(5);
                System.out.println(name + " не смешно в этот раз, он стал немного голоднее.");
            }
        } catch (LowEnergyException e) {
            System.out.println("АВАРИЯ у Козлика: " + e.getMessage());
            die();
        }
    }

    @Override
    public void reactToBallHit() {
        System.out.println(name + " не участвует в бросании мячей.");
    }

    @Override
    public void die() {
        isDead = true;
        System.out.println(name + " умирает от горя.");
    }

    @Override
    public String toString() {
        return "Kozlik{name='" + name + "', hungerLevel=" + hungerLevel + ", isDead=" + isDead + "}";
    }
}
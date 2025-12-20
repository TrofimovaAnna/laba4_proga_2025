package korotyshky;

import threeObjects.*;
import recordCor.Coordinates;
import java.util.Random;
import interfaces.Targetable;
import exceptions.FairException;

public class Neznaika extends Korotyshka implements interfaces.PersonDie {
    protected threeObjects.Ball ball;
    private static final Coordinates NEZNAIKA_POSITION = new Coordinates(0, 0);

    public Neznaika(String name, int hungerLevel, threeObjects.Ball ball) {
        super(name, hungerLevel);
        this.ball = ball;
    }

    @Override
    public void reactToBallHit() {
        System.out.println(name + " не ловит мячики.");
    }

    public void throwBall(Targetable target) {
        if (isDead()) {
            return;
        }
        Random rand = new Random();
        Coordinates targetPos = target.getPosition();
        int distance = Math.abs(targetPos.x() - NEZNAIKA_POSITION.x()) +
                Math.abs(targetPos.y() - NEZNAIKA_POSITION.y());
        int baseAccuracy = 70;
        int accuracy = Math.max(20, baseAccuracy - distance * 2);
        boolean hit = rand.nextInt(100) < accuracy;

        if (hit) {
            String targetName;
            if (target instanceof Artist) {
                targetName = "Артиста";
                System.out.println(name + " бросил мячик и попал в " + targetName);
            } else if (target instanceof FairObject k) {
                targetName = k.getName();
                System.out.println(name + " бросил мячик и попал в " + targetName);
            }

            if (target instanceof Artist) {
                if (rand.nextInt(100) < 10) {
                    System.out.println("О нет! Критический удар! Артист погиб!");
                    ((Artist) target).die();
                    die();
                    System.out.println(name + " не выдержал увиденного и умер от горя!");
                } else {
                    changeHungerLevel(-10);
                    System.out.println(name + " смеётся! Артист корчит рожицы.");
                }
            } else if (target instanceof Mirror) {
                Mirror mirror = (Mirror) target;
                try {
                    mirror.takeDamage(40);
                    // Успешный урон — зеркало было целым
                    if (mirror.isBroken()) {
                        System.out.println(name + " попал в зеркало! Но ему всё равно.");
                    } else {
                        changeHungerLevel(-10);
                        System.out.println(name + " смеётся! Голод немного уменьшился.");
                    }
                } catch (FairException e) {
                    System.out.println(name + " бросил мячик в зеркало, но оно уже разбито!");
                    System.out.println(e.getMessage());
                    changeHungerLevel(2); // досада
                }
            } else if (target instanceof Stage) {
                Stage stage = (Stage) target;
                stage.takeDamage(15);
                if (stage.isDestroyed()) {
                    die();
                    System.out.println(name + " грустно, помост разрушен!");
                } else {
                    changeHungerLevel(-20);
                    System.out.println(name + " стало веселее! Голод уменьшился.");
                }
            }
        } else {
            System.out.println(name + " бросил мячик, но промахнулся!");
            changeHungerLevel(3);
        }
    }

    @Override
    public void die() {
        isDead = true;
        System.out.println(name + " умер от горя.");
    }

    @Override
    public String toString() {
        return "Neznaika{name='" + name + "', hungerLevel=" + hungerLevel + ", isDead=" + isDead + "}";
    }

}


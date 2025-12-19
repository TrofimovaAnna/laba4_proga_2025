package korotyshky;

import exceptions.LowEnergyException;

public abstract class Korotyshka {
    protected String name;
    protected int hungerLevel;
    protected boolean isDead;

    public Korotyshka(String name, int hungerLevel) {
        this.name = name;
        this.hungerLevel = hungerLevel;
        this.isDead = false;
    }

    public abstract void reactToBallHit();


    public void changeHungerLevel(int amount) {
        this.hungerLevel = Math.max(0, this.hungerLevel + amount);

        // Смерть от голода при hungerLevel > 100 (а не 150!)
        if (this.hungerLevel > 100) {
            throw new LowEnergyException(name + " умер от голода!");
        }
    }

    public String getName() { return name; }
    public int getHungerLevel() { return hungerLevel; }
    public boolean isDead() { return isDead; }
    public void setDead(boolean dead) { isDead = dead; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Korotyshka)) return false;
        Korotyshka that = (Korotyshka) o;
        return hungerLevel == that.hungerLevel &&
                isDead == that.isDead &&
                java.util.Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, hungerLevel, isDead);
    }

    @Override
    public String toString() {
        return "Korotyshka{name='" + name + "', hungerLevel=" + hungerLevel + ", isDead=" + isDead + "}";
    }
}
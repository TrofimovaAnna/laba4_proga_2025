package threeObjects;

import material.Material;
import recordCor.Coordinates;

public class Stage extends FairObject {
    private int strength;
    protected Material material;

    public Stage(String name, Material material, Coordinates position) {
        super(name, 200, position);
        this.material = material;
        this.strength = 100;
    }

    public void takeDamage(int amount) {
        if (isDestroyed()) return;
        this.strength -= amount;
        if (isDestroyed()) {
            System.out.println("Помост сломался!");
        }
    }

    public boolean isDestroyed() {
        return strength <= 0;
    }

    @Override
    public void interact(korotyshky.Korotyshka character) {
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stage)) return false;
        Stage stage = (Stage) o;
        return strength == stage.strength && material == stage.material && java.util.Objects.equals(name, stage.name) && java.util.Objects.equals(position, stage.position);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, material, strength, position);
    }

    @Override
    public String toString() {
        return "Stage{name='" + name + "', material=" + material + ", position=" + position + ", destroyed=" + isDestroyed() + "}";
    }

}

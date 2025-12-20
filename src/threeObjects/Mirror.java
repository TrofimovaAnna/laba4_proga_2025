package threeObjects;

import material.Material;
import recordCor.Coordinates;
//import interfaces.Targetable;
import exceptions.FairException;

public class Mirror extends FairObject {
    private int damageLevel;
    protected Material material;

    public Mirror(String name, Material material, Coordinates position) {
        super(name, 50, position);
        this.material = material;
        this.damageLevel = 0;
    }

    public void takeDamage(int amount) throws FairException {
        if (isBroken()) {
            throw new FairException("Нельзя бросать мяч, зеркало уже разбито!");
        }
        this.damageLevel += amount;
        if (isBroken()) {
            System.out.println("Зеркало разбилось!");
        }
    }

    public boolean isBroken() {
        return damageLevel >= 90;
    }

    public int getDamageLevel() {
        return damageLevel;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mirror)) return false;
        Mirror mirror = (Mirror) o;
        return damageLevel == mirror.damageLevel && material == mirror.material && java.util.Objects.equals(name, mirror.name) && java.util.Objects.equals(position, mirror.position);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, material, damageLevel, position);
    }

    @Override
    public String toString() {
        return "Mirror{name='" + name + "', material=" + material + ", position=" + position + ", broken=" + isBroken() + "}";
    }

}


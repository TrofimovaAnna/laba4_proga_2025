package threeObjects;

import recordCor.Coordinates;

public class Ball extends FairObject {
    protected material.Material material;

    public Ball(material.Material material) {
        super("Резиновый мячик", 5, new Coordinates(0, 0));
        this.material = material;
    }

    public material.Material getMaterial() {
        return material;
    }

    @Override
    public void interact(korotyshky.Korotyshka character) {
        // noop
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ball)) return false;
        Ball ball = (Ball) o;
        return material == ball.material && java.util.Objects.equals(name, ball.name);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, material);
    }

    @Override
    public String toString() {
        return "Ball{material=" + material + "}";
    }
}

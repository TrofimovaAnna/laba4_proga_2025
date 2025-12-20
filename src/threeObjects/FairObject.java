package threeObjects;

import interfaces.Targetable;
import recordCor.Coordinates;

public abstract class FairObject implements Targetable {
    protected String name;
    protected int size;
    protected Coordinates position;

    public FairObject(String name, int size, Coordinates position) {
        this.name = name;
        this.size = size;
        this.position = position;
    }

    public String getName() { return name; }
    public int getSize() { return size; }
    public Coordinates getPosition() { return position; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FairObject)) return false;
        FairObject that = (FairObject) o;
        return size == that.size && java.util.Objects.equals(name, that.name) && java.util.Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, size, position);
    }

    @Override
    public String toString() {
        return "FairObject{name='" + name + "', position=" + position + "}";
    }

}


package org.cis120.tfe;

public class Tile {
    private int value;

    public Tile(int value) {
        this.value = value;
    }

    /*
     * Copy constructor that will create a new Tile object with fields
     * of the same values
     */
    public Tile(Tile t) {
        this.value = t.getValue();
    }

    public int getValue() {
        return value;
    }

    public void setValue() {
        this.value = 2 * this.value;
    }

    public Tile copy() {
        return new Tile(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Tile))
            return false;
        Tile tile = (Tile) o;
        return getValue() == tile.getValue();
    }
}

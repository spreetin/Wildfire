package se.oru.wildfire;

public final class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Coordinate other = (Coordinate) o;
        return this.x == other.x() && this.y == other.y();
    }

    @Override
    public int hashCode() {
        return x * 2 + y * 3; // Create unique hash key using prime multiplication
    }
}

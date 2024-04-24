public final class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final int x(){
        return x;
    }

    public final int y(){
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
        if (this.x == other.x() && this.y == other.y()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * 2 + y * 3; // Create unique hash key using prime multiplication
    }
}

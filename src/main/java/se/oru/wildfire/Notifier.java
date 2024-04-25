package se.oru.wildfire;

public interface Notifier {

    public Coordinate[] updatedCells();

    public Cell retrieveCell(int x, int y);

    public Cell retrieveCell(Coordinate coordinate);

    public boolean isDifferent(int x, int y);
}

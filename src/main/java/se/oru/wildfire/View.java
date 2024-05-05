package se.oru.wildfire;

import javafx.geometry.Bounds;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class View extends GridPane implements Observer {
    private Rectangle[][] cells;
    private Color paintColor = Color.DARKGREEN;
    private int brushSize = 1;
    private boolean mousePressed = false;

    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        Coordinate[] updatedCells = o.updatedCells();
        for(Coordinate coordinates : updatedCells){
            drawCell(o.retrieveCell(coordinates), coordinates);
        }
    }

    public void setInitialMap(InitialMap initialMap){
        Coordinate size = initialMap.getSize();
        cells = new Rectangle[size.x()][size.y()];
        for (int i = 0; i < size.x(); i++) {
            for (int j = 0; j < size.y(); j++) {
                cells[i][j] = new Rectangle( (double) 800 / size.x(), (double) 700 / size.y());
                cells[i][j].setStroke(Color.BLACK);
                Cell cell = initialMap.getCell(i, j);
                drawCell(cell, new Coordinate(i, j));
                this.add(cells[i][j], i, j);
            }
        }
        setupMouseHandlers(size);
    }

    public void drawCell(Cell cell, Coordinate coordinates){
        Color color = null;
        Rectangle rect = cells[coordinates.x()][coordinates.y()];
        if(rect != null){
            if(cell.isBurning()){
                color = Color.DARKORANGE;
            } else if (cell.burnedOut()) {
                color = Color.BLACK;
            } else {
                switch (cell.getGroundType()) {
                    case None -> color = Color.WHITE;
                    case Trees -> color = Color.DARKGREEN;
                    case Stone -> color = Color.DARKGREY;
                    case Water -> color = Color.DARKBLUE;
                    default -> {
                    }
                }
            }
            drawRect(rect, color);
        }
    }

    public Rectangle[][] getCells(){
        return cells;
    }

    public void setBrushSize(int newSize) {
        this.brushSize = newSize;
    }

    public void setGroundType(Color color){
        this.paintColor = color;
    }

    private void drawRect(Rectangle rect,Color color){
        rect.setFill(color);
    }

    private boolean isValidAction(int x, int y){
        return x >= 0 && x < cells.length && y >= 0 && y < cells[0].length;
    }

    private void setupMouseHandlers(Coordinate size) {
        this.setOnMousePressed(event ->
        {
            mousePressed = true;
            handleCellMouseOver(event, size);
        });

        this.setOnMouseDragged(event ->
        {
            if (mousePressed) {
                handleCellMouseOver(event, size);
            }
        });
        this.setOnMouseReleased(_ -> mousePressed = false);
    }

    private void handleCellMouseOver(MouseEvent event, Coordinate size) {
        for (int row = 0; row < size.y(); row++) {
            for (int col = 0; col < size.x(); col++) {
                Bounds bounds = cells[col][row].getBoundsInParent();
                if (bounds.contains(event.getX(), event.getY())) {
                    handleCellClick(col, row);
                    return;
                }
            }
        }
    }

    private void handleCellClick(int i, int j) {
        int realBrushSize = brushSize / 2;
        for (int dX = -realBrushSize; dX <= realBrushSize; dX++) {
            for (int dY = -realBrushSize; dY <= realBrushSize; dY++) {
                int new_x = i + dX;
                int new_y = j + dY;
                if (isValidAction(new_x, new_y)) {
                    drawRect(cells[new_x][new_y], paintColor);
                }
            }
        }
    }



    // Future implementations


    public void redrawMap(){
        // TODO: Implement
    }

    // Dragging mouse cell click.

    InitialMap returnPaintedMap(){
        // TODO: Implement
        return null;
    }
}

package se.oru.wildfire;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Objects;

public class View extends GridPane implements Observer {
    private Rectangle[][] cells;

    public Rectangle[][] getCells(){
        return cells;
    }

    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        Coordinate[] updatedCells = o.updatedCells();
        // TODO: Implement
        for(Coordinate coordinates : updatedCells){
            drawCell(o.retrieveCell(coordinates), coordinates);
        }
    }

    public void redrawMap(){

    }

    public void setInitialMap(InitialMap initialMap){
        Coordinate size = initialMap.getSize();
        cells = new Rectangle[size.x()][size.y()];
        for (int i = 0; i < size.x(); i++) {
            for (int j = 0; j < size.y(); j++) {
                Rectangle rect = new Rectangle(10, 10);
                Cell cell = initialMap.getCell(i, j);
                Coordinate coordinate = new Coordinate(i,j);
                drawCell(cell, coordinate);
                rect.setStroke(Color.BLACK);
                int finalI = i;
                int finalJ = j;
                rect.setOnMouseClicked(_ -> handleCellClick(finalI, finalJ));
                this.add(rect, j, i);
                cells[i][j] = rect;
            }
        }
    }

    public void drawCell(Cell cell, Coordinate coordinates){
        if(cell.isBurning()){
            cells[coordinates.x()][coordinates.y()].setFill(Color.DARKORANGE);
        } else {
            if (cell.getGroundType() == Cell.GroundType.Trees) {
                cells[coordinates.x()][coordinates.y()].setFill(Color.DARKGREEN);
            } else if (cell.getGroundType() == Cell.GroundType.Water) {
                cells[coordinates.x()][coordinates.y()].setFill(Color.DARKBLUE);
            } else if (cell.getGroundType() == Cell.GroundType.Stone) {
                cells[coordinates.x()][coordinates.y()].setFill(Color.DARKGREY);
            }
        }
    }

    private void handleCellClick(int i, int j) {
        Rectangle rect = cells[i][j];
        Color currentColor = (Color) rect.getFill();
        // needs to get color from paint class
        Color newColor = currentColor.equals(Color.WHITE) ? Color.DARKGREEN : Color.WHITE;
        rect.setFill(newColor);
    }
}

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
                cells[i][j] = new Rectangle(10, 10);
                cells[i][j].setStroke(Color.BLACK);
                Cell cell = initialMap.getCell(i, j);
                drawCell(cell, new Coordinate(i, j));
                int finalI = i;
                int finalJ = j;
                cells[i][j].setOnMouseClicked(_ -> handleCellClick(finalI, finalJ));
                this.add(cells[i][j], i, j);
            }
        }
    }

    public void drawCell(Cell cell, Coordinate coordinates){
        Rectangle rect = cells[coordinates.x()][coordinates.y()];
        if(rect != null){
            if(cell.isBurning()){
                rect.setFill(Color.DARKORANGE);
            } else {
                switch (cell.getGroundType()){
                    case None:
                        rect.setFill(Color.WHITE);
                        break;
                    case Trees:
                        rect.setFill(Color.DARKGREEN);
                        break;
                    case Stone:
                        rect.setFill(Color.DARKGREY);
                        break;
                    case Water:
                        rect.setFill(Color.DARKBLUE);
                        break;
                    default:
                        break;
                }
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

package se.oru.wildfire;

import javafx.geometry.Bounds;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

public class View extends GridPane implements Observer {
    private Rectangle[][] cells = new Rectangle[0][0];
    private Color paintColor = Color.DARKGREEN;
    private int brushSize = 1;
    private boolean mousePressed = false;

    // Define colours used
    private final Color treeColor = Color.DARKGREEN;
    private final Color waterColor = Color.DARKBLUE;
    private final Color stoneColor = Color.DIMGREY;
    private final Color fireColor = Color.DARKORANGE;
    private final Color burnedOutColor = Color.BLACK;
    private final Color noneColor = Color.WHITE;

    public View(){
        setManaged(false);
        widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> resizeChildren());
        heightProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> resizeChildren());
        setupMouseHandlers();
    }

    private void resizeChildren(){
        for (Rectangle[] row : cells) {
            for (Rectangle cell : row) {
                cell.setWidth(Math.floor(widthProperty().doubleValue() / (double)cells.length));
                cell.setHeight(Math.floor(heightProperty().doubleValue() / (double)cells[0].length));
            }
        }
    }

    @Override
    public void newUpdate(Notifier o) {
        // Cast o to Model class
        Coordinate[] updatedCells = o.updatedCells();
        for(Coordinate coordinates : updatedCells){
            drawCell(o.retrieveCell(coordinates), coordinates);
        }
    }

    public void setInitialMap(InitialMap initialMap){
        this.getChildren().clear();
        Coordinate size = initialMap.getSize();
        cells = new Rectangle[size.x()][size.y()];

        for (int i = 0; i < size.x(); i++) {
            for (int j = 0; j < size.y(); j++) {
                cells[i][j] = new Rectangle( (double) 600 / size.x(), (double) 700 / size.y());
                Cell cell = initialMap.getCell(i, j);
                drawCell(cell, new Coordinate(i, j));
                this.add(cells[i][j], i, j);
            }
        }
        resizeChildren();
    }

    public void drawCell(Cell cell, Coordinate coordinates){
        Color color = null;
        Rectangle rect = cells[coordinates.x()][coordinates.y()];
        if(rect != null){
            if(cell.isBurning() && cell.getGroundType() != Cell.GroundType.Stone && cell.getGroundType() != Cell.GroundType.Water){
                color = fireColor;
            } else if (cell.burnedOut()) {
                color = burnedOutColor;
            } else {
                switch (cell.getGroundType()) {
                    case None -> color = noneColor;
                    case Trees -> color = treeColor;
                    case Stone -> color = stoneColor;
                    case Water -> color = waterColor;
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

    public int getBrushSize(){
        return this.brushSize;
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

    private void setupMouseHandlers() {
        this.setOnMousePressed(event ->
        {
            mousePressed = true;
            handleCellMouseOver(event);
        });

        this.setOnMouseDragged(event ->
        {
            if (mousePressed) {
                handleCellMouseOver(event);
            }
        });
        this.setOnMouseReleased(ignore -> mousePressed = false);
    }

    private void handleCellMouseOver(MouseEvent event) {
        for (int row = 0; row < cells[0].length; row++) {
            for (int col = 0; col < cells.length; col++) {
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

    public void redrawMap(){
        // TODO: Implement
    }

    InitialMap returnPaintedMap(){
        Cell[][] mapData = new Cell[cells.length][cells[0].length];
        for (int i=0; i<cells.length; i++){
            for (int j=0; j<cells[0].length; j++){
                Paint fill = cells[i][j].getFill();
                if (fill instanceof Color c){
                    if (c == treeColor){
                        mapData[i][j] = new Cell(Cell.GroundType.Trees);
                    } else if (c == waterColor){
                        mapData[i][j] = new Cell(Cell.GroundType.Water);
                    } else if (c == stoneColor){
                        mapData[i][j] = new Cell(Cell.GroundType.Stone);
                    } else if (c == burnedOutColor){
                        mapData[i][j] = new Cell(100);
                    } else if (c == fireColor){
                        mapData[i][j] = new Cell(10);
                    }
                } else {
                    mapData[i][j] = new Cell();
                }
            }
        }
        return new InitialMap(mapData);
    }

    public void resize(){
        if (getParent() instanceof Pane){
            Pane pane = (Pane) getParent();
            setHeight(pane.getHeight());
            setWidth(pane.getWidth());
        }
    }
}

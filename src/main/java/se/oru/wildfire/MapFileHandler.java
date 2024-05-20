package se.oru.wildfire;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;

public class MapFileHandler {

    public static boolean saveMap(Cell[][] map, Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose map location");
        File file = fileChooser.showSaveDialog(stage);
        String data;
        ArrayList<String> rows = new ArrayList<>();
        StringBuilder main_sb = new StringBuilder();
        for (Cell[] row : map){
            ArrayList<String> rowStrings = new ArrayList<>();
            for (Cell cell : row){
                if (cell.getGroundType() == Cell.GroundType.Water){
                    rowStrings.add("W");
                } else if (cell.getGroundType() == Cell.GroundType.Stone){
                    rowStrings.add("S");
                } else if (cell.isBurning()){
                    rowStrings.add("F");
                } else {
                    rowStrings.add("T");
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<rowStrings.size();i++){
                if (i != 0){
                    sb.append("/").append(rowStrings.get(i));
                } else {
                    sb.append(rowStrings.get(0));
                }
            }
            main_sb.append(sb.toString()).append("\n");
        }
        try (FileOutputStream outputStream = new FileOutputStream(file.getAbsolutePath())) {
            outputStream.write(main_sb.toString().getBytes());
            outputStream.flush();
            return true;
        } catch (IOException ex){
            return false;
        }
    }

    public static Cell[][] loadMap(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose map location");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && file.canRead()){
            try {
                Scanner scanner = new Scanner(file);
                ArrayList<Cell[]> array = new ArrayList<>();
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] split = line.split("/");
                    Cell[] row = new Cell[split.length];
                    for (int i=0;i<split.length;i++){
                        String part = split[i];
                        Cell cell = new Cell();
                        if (Objects.equals(part, "T")){
                            cell.setGroundType(Cell.GroundType.Trees);
                        } else if (Objects.equals(part, "W")){
                            cell.setGroundType(Cell.GroundType.Water);
                        } else if (Objects.equals(part, "S")){
                            cell.setGroundType(Cell.GroundType.Stone);
                        } else if (Objects.equals(part, "F")){
                            cell.setGroundType(Cell.GroundType.Trees);
                            cell.setBurnedLevel(10);
                        }
                        row[i] = cell;
                    }
                    array.add(row);
                }
                return array.toArray(new Cell[0][0]);
            } catch (IOException ex){
                return null;
            }
        }
        return null;
    }
}

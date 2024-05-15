package se.oru.wildfire;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.FileWriter;

public class ReportGenerator {
    private int[] exportBurning;
    private int[] exportBurnedOut;
    private FrameStatistics frameStatistics;

    public ReportGenerator(FrameStatistics frameStatistics){
        this.frameStatistics = frameStatistics;
    }

    public void recordCounter(){
        Cell[][] tickData = frameStatistics.getTick(500); // random int value for now
        for (int i=0; i< tickData.length; i++){
            int countBurnedOut= 0;
            int countBurning = 0;
            for(int j=0;j<tickData[i].length;j++){
                if (tickData[i][j].isBurning()){
                    countBurning ++;
                }
                else if (tickData[i][j].burnedOut()){
                    countBurnedOut ++;
                }
                exportBurning[i] = countBurning;
                exportBurnedOut[i] = countBurnedOut;
            }
        }
        generate(tickData);
    }

    private void generate(Cell[][] tickData) {
        String fileName = "Simulation.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //Header
            writer.write("Simulation : Burned and Burning \n");

            // Write Data
            for (int i = 0; i < tickData.length; i++) {
                writer.write(i + ", " + exportBurning[i]+ ", " + exportBurnedOut[i] + "\n");
            }
            System.out.println("Report File Written");
        } catch (IOException e){
            System.err.println("Report Write to File Failed"+ e.getMessage());
        }
    }
}

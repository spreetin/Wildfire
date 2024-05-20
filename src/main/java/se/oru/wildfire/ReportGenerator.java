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
        int currentTick = frameStatistics.getCurrentTick();
        Cell[][] tickData = frameStatistics.getTick(currentTick);
        exportBurning = new int[tickData.length];
        exportBurnedOut = new int[tickData.length];
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

            }
            exportBurning[i] = countBurning;
            exportBurnedOut[i] = countBurnedOut;
        }
        generate();
    }

    private void generate() {
        String fileName = "Simulation.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //Header
            writer.write("Simulation : Burning and Burned \n");

            // Write Data
            for (int i = 0; i < exportBurning.length; i++) {
                writer.write(exportBurning[i] + ", " + exportBurnedOut[i] + "\n");
            }
            System.out.println("Report File Written");
        } catch (IOException e){
            System.err.println("Report Write to File Failed"+ e.getMessage());
        }
    }
}

package se.oru.wildfire;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;

public class ReportGenerator {
    private final FrameStatistics frameStatistics;

    public ReportGenerator(FrameStatistics frameStatistics){
        this.frameStatistics = frameStatistics;
    }

    public void recordCounter(){
        int tickNum = 0;
        ArrayList<Integer> burning = new ArrayList<>();
        ArrayList<Integer> burnedOut = new ArrayList<>();
        while (frameStatistics.hasTick(tickNum)){
            Cell[][] tickData = frameStatistics.getTick(tickNum);
            int countBurnedOut= 0;
            int countBurning = 0;
            for (Cell[] tickDatum : tickData) {
                for (Cell cell : tickDatum) {
                    if (cell.burnedOut()) {
                        countBurnedOut++;
                    } else if (cell.isBurning()) {
                        countBurning++;
                    }
                }
            }
            burning.add(countBurning);
            burnedOut.add(countBurnedOut);
        }
        generate(burning, burnedOut);
    }

    private void generate(ArrayList<Integer> burning, ArrayList<Integer> burnedOut) {
        String fileName = "Simulation.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //Header
            writer.write("Simulation : Burning and Burned \n");

            // Write Data
            for (int i = 0; i < burning.size(); i++) {
                writer.write(burning.get(i) + ", " + burnedOut.get(i) + "\n");
            }
            System.out.println("Report File Written");
        } catch (IOException e){
            System.err.println("Report Write to File Failed"+ e.getMessage());
        }
    }
}

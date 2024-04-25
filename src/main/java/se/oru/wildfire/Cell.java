package se.oru.wildfire;

public class Cell {

    private int m_burning = 0;

    public Cell(){

    }

    public Cell(int burningLevel){
        m_burning = burningLevel;
    }

    public boolean burnedOut(){
        return m_burning >= 100;
    }

    public boolean isBurning(){
        return m_burning != 0;
    }

    public int burnedLevel(){
        return m_burning;
    }

    public void setNeighbourBurning(){
        m_burning += 10;
    }

    public void setBurnedLevel(int level){
        m_burning = level;
    }
}
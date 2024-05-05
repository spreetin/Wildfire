package se.oru.wildfire;

public class Cell {

    public enum GroundType {
        None,
        Trees,
        Water,
        Stone
    }

    private int m_burning = 0;
    private GroundType m_type = GroundType.Trees;

    public Cell(){

    }

    public Cell(int burningLevel){
        m_burning = burningLevel;
    }

    public boolean burnedOut(){
        return m_burning >= 100;
    }

    public boolean isBurning(){
        return m_burning != 0 && m_burning < 100;
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

    public GroundType getGroundType(){
        return m_type;
    }

    public void setGroundType(GroundType type){
        m_type = type;
    }

    public boolean isBurnable(){
        return m_type == GroundType.Trees;
    }
}

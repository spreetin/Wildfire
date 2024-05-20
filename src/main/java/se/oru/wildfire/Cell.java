package se.oru.wildfire;

public class Cell {

    private final int burnedOutLevel = 100;

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

    public Cell(Cell other){
        m_burning = other.m_burning;
        m_type = other.m_type;
    }

    public Cell(int burningLevel){
        m_burning = burningLevel;
    }

    public Cell(GroundType ground){
        m_type = ground;
    }

    public boolean burnedOut(){
        return m_burning >= burnedOutLevel;
    }

    public boolean isBurning(){
        return m_burning > 10 && !burnedOut();
    }

    public int burnedLevel(){
        return m_burning;
    }

    public void setBurnedLevel(int level){
        m_burning = level;
        if (m_burning < 0)
            m_burning = 0;
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

    public boolean canSpread(){
        return m_burning >= 30 && !burnedOut();
    }
}

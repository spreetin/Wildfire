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

    public void ignite(){
        m_burning += 10;
    }

    public boolean burnedOut(){
        return m_burning >= 200;
    }

    public boolean isBurning(){
        return m_burning != 0 && !burnedOut();
    }

    public int burnedLevel(){
        return m_burning;
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

    public boolean canSpread(){
        return m_burning >= 10;
    }
}

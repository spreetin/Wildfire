public class Cell {

    private int m_burning = 0;

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
        // TODO: Implement
    }

    public void setBurnedLevel(int level){
        // TODO: Implement
    }
}

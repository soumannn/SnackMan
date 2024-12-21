package de.hs_rm.backend.gamelogic.characters.players;

public class Chicken {
    private static int currentID = 0;
    private int posX;
    private int posY;
    private int id;
    
    public Chicken(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.id = currentID;
        currentID++;
    }
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
    
}

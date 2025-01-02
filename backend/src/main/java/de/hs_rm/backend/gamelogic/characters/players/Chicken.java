package de.hs_rm.backend.gamelogic.characters.players;

public class Chicken {
    private int posX;
    private int posY;
    private final double SPEED;
    
    public Chicken(int posX, int posY, double speed) {
        this.posX = posX;
        this.posY = posY;
        this.SPEED = speed;
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

    public double getSpeed () {
        return SPEED;
    }
    
}

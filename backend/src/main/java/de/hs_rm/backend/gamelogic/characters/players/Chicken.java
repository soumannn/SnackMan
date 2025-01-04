package de.hs_rm.backend.gamelogic.characters.players;

public class Chicken {
    private double posX;
    private double posY;
    private String id;
    private int angle;
    private final double SPEED;
    
    public Chicken(double posX, double posY, double speed) {
        this.posX = posX;
        this.posY = posY;
        this.SPEED = speed;
    }
    public double getPosX() {
        return posX;
    }
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public double getPosY() {
        return posY;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }
    public double getSpeed () {
        return SPEED;
    }
    public String getId() {
       return id;
    }
    public String setId() {
        return id;
     }
    
}

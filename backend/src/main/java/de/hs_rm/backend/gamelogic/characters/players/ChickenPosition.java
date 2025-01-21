package de.hs_rm.backend.gamelogic.characters.players;

import java.util.List;

public class ChickenPosition {

    private int posX;
    private int posY;
    private String id;
    private int angle;
    private List<Egg> eggList;
    private int currentCalorie;

    private boolean tooFat = false;

    public ChickenPosition(String id, int posX, int posY, int angle, List<Egg> eggList, int currentCalorie, boolean isTooFat){
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.angle = angle;
        this.eggList = eggList;
        this.currentCalorie = currentCalorie;
        this.tooFat = isTooFat;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public List<Egg> getEggList() {
        return eggList;
    }

    public void setEggList(List<Egg> eggList) {
        this.eggList = eggList;
    }

    public int getCurrentCalorie() {
        return currentCalorie;
    }

    public void setCurrentCalorie(int currentCalorie) {
        this.currentCalorie = currentCalorie;
    }

    public boolean isTooFat() {
        return tooFat;
    }

    public void setTooFat(boolean tooFat) {
        this.tooFat = tooFat;
    }

    @Override
    public String toString() {
        return "ChickenPosition{" +
                "id=" + id +
                ", posX=" + posX +
                ", posY=" + posY +
                ", angle=" + angle +
                ", eggList=" + eggList +
                ", currentCalorie=" + currentCalorie +
                '}';
    }

    
}

package de.hs_rm.backend.gamelogic.characters.players;

import java.util.ArrayList;
import java.util.List;

// Bauplan der Characters --> hierbei handelt es sich um eine abstrakte Klasse!
public abstract class Character{
    //Initialisierung 
    //Der Spieler bekommt später eine spring boot entity id 
    private long id; 
    private String name;
    private String gameId; 
    private double playerSpeed;
    PlayerPosition playerposition;
    private int currentcalorie;
    private int life; 
    private List<Item> items;



    public Character(long id, String name, String gameId, double playerSpeed, PlayerPosition playerposition,  int life){
        this.id=id; 
        this.name=name; 
        this.gameId=gameId; 
        this.playerSpeed=playerSpeed; 
        this.playerposition=playerposition; 
        this.life = life; 
        this.currentcalorie= 0;
        this.items = new ArrayList<>(); 

    }
    //Getter udn Setter 
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGamiId() {
        return gameId;
    }
    public void setGamiId(String gamiId) {
        this.gameId = gamiId;
    }
    public double getPlayerSpeed() {
        return playerSpeed;
    }
    public void setPlayerSpeed(double playerSpeed) {
        this.playerSpeed = playerSpeed;
    }
    public PlayerPosition getPlayerposition() {
        return playerposition;
    }
    public void setPlayerposition(PlayerPosition playerposition) {
        this.playerposition = playerposition;
    }

    public int getLife() {
        return life;
    }
    public void setLife(int life) {
        this.life = life;
    }

    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    //Methode: addieren der Kalorien
    public void mycurrentItem(){
        Item currentItem= items.remove(0);

        if (currentItem instanceof FoodItems foodItem) {
            currentcalorie += foodItem.getNutriScore().getCalorieBonus();
        } else if(currentItem instanceof ObjectsItems){
            //TODO: noch die effekte
        }
    }

    //Abstrakte Methoden 
    public abstract PlayerPosition move(); 
    //public abstract void pickUpItem(Item item); 

    // Methode: Sammeln von Items
    public void pickUpItemLogic(Item item) {
        items.add(item);
        System.out.println(item.getName() + "is picked up");
    }

    //TODO: muss noch ausgearbietet werden
    // Methode: Leben verloren
    public void caught() {
        life--;
        if (life <= 0) {
            System.out.println(getName() + " has been caught and has no more lives.");
        }
    }
    
}

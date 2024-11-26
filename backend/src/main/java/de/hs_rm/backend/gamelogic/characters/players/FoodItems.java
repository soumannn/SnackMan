package de.hs_rm.backend.gamelogic.characters.players;

public class FoodItems implements Item {

    private String name;
    private PlayerPosition playerPosition;
    //private int calories;
    private NutriScore nutriScore;
    
    public FoodItems(String name, PlayerPosition playerPosition, NutriScore nutriScore ) {
        this.name = name;
        this.playerPosition = playerPosition;
        //this.calories = calories;
        this.nutriScore = nutriScore;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    //TODO: applyEffekte müssen noch gemach werden
    @Override
    public void applyEffect(Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

    public NutriScore getNutriScore() {
        return nutriScore;
    }

    public void setNutriScore(NutriScore nutriScore) {
        this.nutriScore = nutriScore;
    }


}

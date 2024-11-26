package de.hs_rm.backend.gamelogic.characters.players;

public enum NutriScore {
    A("Green", 50),
    B("Blue",40),
    C("Yellow", 30),
    D("Orange", 20),
    E("Red", 10);

    private final String color;
    private final int calorieBonus;

    NutriScore(String color, int calorieBonus) {
        this.color = color;
        this.calorieBonus = calorieBonus;
    }

    public String getColor() {
        return color;
    }

    public int getCalorieBonus() {
        return calorieBonus;
    }
}

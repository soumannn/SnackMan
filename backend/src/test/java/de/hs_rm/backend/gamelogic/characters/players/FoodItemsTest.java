package de.hs_rm.backend.gamelogic.characters.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FoodItemsTest {
    private FoodItems foodItem;
    private Player testPlayer;
    
    //das wird vor jedem Test getestet
    @BeforeEach
    void setUp() {
        testPlayer = new Player("TestPlayer");
        foodItem = new FoodItems("Apple", 5, 10, NutriScore.A);
    }

    @Test
    void testConstructorAndGetter() {
        // Testet, ob der Konstruktor die Felder korrekt initialisiert
        assertEquals("Apple", foodItem.getName());
        assertEquals(5, foodItem.getX());
        assertEquals(10, foodItem.getY());
        assertEquals(NutriScore.A, foodItem.getNutriScore());
    }

}

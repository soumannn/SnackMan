package de.hs_rm.backend.gamelogic.characters.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjectItemsTest {
    
     private ObjectsItems objectsItem;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        // Test-Objekt und Test-Player erstellen
        objectsItem = new ObjectsItems("Magic Wand", 10, 15, "Boosts player's mana");
        testPlayer = new Player("TestPlayer", "test@example.com", "password123");
    }

    @Test
    void testConstructorAndGetters() {
        // Testet Konstruktor und Getter
        assertEquals("Magic Wand", objectsItem.getName());
        assertEquals(10, objectsItem.getX());
        assertEquals(15, objectsItem.getY());
        assertEquals("Boosts player's mana", objectsItem.getEffectDescription());
    }

    @Test
    void testSetters() {
        // Setzen neuer Werte und Überprüfung
        objectsItem.setName("Golden Sword");
        objectsItem.setX(20);
        objectsItem.setY(25);
        objectsItem.setEffectDescription("Increases player's strength");

        assertEquals("Golden Sword", objectsItem.getName());
        assertEquals(20, objectsItem.getX());
        assertEquals(25, objectsItem.getY());
        assertEquals("Increases player's strength", objectsItem.getEffectDescription());
    }

    //TODO:
    @Test
    void testApplyEffect() {

    }
    
}

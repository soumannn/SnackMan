package de.hs_rm.backend.gamelogic.characters.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemsTest {
    private TestItem testItem;
    private Player testPlayer; 

    @BeforeEach
    void setUp() {
        testItem = new TestItem("TestItem", 5, 10);
    }

    @Test
    void testConstructorAndGetters() {
        // Überprüft, ob die Attribute korrekt initialisiert wurden
        assertEquals("TestItem", testItem.getName());
        assertEquals(5, testItem.getX());
        assertEquals(10, testItem.getY());
    }

    @Test
    void testSetters() {
        // Setzen neuer Werte und Überprüfung
        testItem.setName("NewName");
        testItem.setX(15);
        testItem.setY(20);

        assertEquals("NewName", testItem.getName());
        assertEquals(15, testItem.getX());
        assertEquals(20, testItem.getY());
    }
    
}

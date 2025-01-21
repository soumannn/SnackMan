package de.hs_rm.backend.gamelogic.characters.players;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Timer;
import java.util.TimerTask;

class GhostTest {

    private Ghost ghost;

    @Mock
    private ObjectsItems mockItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialisiere den Ghost mit einer Geschwindigkeit von 1.0 und Position (0, 0)
        ghost = new Ghost(1.0, 0, 0);
    }

    @Test
    void testConstructor() {
        // Überprüfe die initialen Werte
        assertEquals(1.0, ghost.getSpeed(), "Ghost speed should be initialized correctly");
        assertEquals(0, ghost.getTouchcount(), "Touchcount should be initialized to 0");
    }

    @Test
    void testHitMethod() {
        // Simuliere einen Treffer
        ghost.hit();
        assertEquals(1, ghost.getTouchcount(), "Touchcount should increase by 1 after hit");
        
        ghost.hit();
        assertEquals(2, ghost.getTouchcount(), "Touchcount should increase by 1 after second hit");
    }

    @Test
    void testUseItemSpeedBoost() {
        // Simuliere das Setzen eines Speed Boosts
        ObjectsItems speedBoostItem = new GhostObjectItem("Speed Boost", 0, 0, null);
        ghost.setCurrentObjectItem(speedBoostItem);

        // Teste, dass die Geschwindigkeit verdoppelt wird
        ghost.useItem(speedBoostItem);
        assertEquals(1.0, ghost.getSpeed(), "Ghost when using Speed Boost");

    }
}

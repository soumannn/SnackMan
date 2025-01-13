package de.hs_rm.backend.gamelogic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.hs_rm.backend.gamelogic.characters.players.FoodItems;
import de.hs_rm.backend.gamelogic.characters.players.GhostObjectItem;
import de.hs_rm.backend.gamelogic.characters.players.Item;
import de.hs_rm.backend.gamelogic.characters.players.NutriScore;
import de.hs_rm.backend.gamelogic.map.Tile;
import de.hs_rm.backend.gamelogic.map.TileType;


public class TileTest {

    private Tile tile;
    private Item item1;
    private Item item2;

    @BeforeEach
    public void setUp() {
        tile = new Tile(TileType.SURFACE);
        item1 = new FoodItems("Item1", -1,-1,NutriScore.A);
        item2 = new GhostObjectItem("Speed Boost", -1, -1, "Increases movement speed temporarily");
        tile.addItem(item1);
        tile.addItem(item2);
    }

    @Test
    public void testRemoveItem_RemovesItemFromList() {
        tile.removeItem(item1);
        assertFalse(tile.getItemList().contains(item1));
    }

    @Test
    public void testRemoveItem_ReturnsRemovedItem() {
        Item removedItem = tile.removeItem(item1);
        assertEquals(item1, removedItem);
    }

    @Test
    public void testRemoveItem_ReturnsNullIfItemNotInList() {
        Item itemNotInList = new FoodItems("ItemNotInList", -1,-1,NutriScore.A);
        Item removedItem = tile.removeItem(itemNotInList);
        assertNull(removedItem);
    }
}
package de.hs_rm.backend.gamelogic.map;

import java.util.ArrayList;
import java.util.List;

import de.hs_rm.backend.gamelogic.characters.players.Chicken;

public class Tile {
    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }
}
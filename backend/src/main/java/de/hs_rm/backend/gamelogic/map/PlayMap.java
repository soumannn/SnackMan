package de.hs_rm.backend.gamelogic.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hs_rm.backend.gamelogic.Game;

public class PlayMap {

    private Tile[][] tiles; // Für BE
    private char[][] map;// Für FE

    public PlayMap(String filePath) {
        loadMap(filePath);
        createTiles();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    private void loadMap(String filePath) {
        String line;
        int height = 0;
        int width = 0;
        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/maps/" + filePath + ".txt"))) {
            // Höhe und Breite der Map
            while ((line = reader.readLine()) != null) {
                width = Math.max(width, line.length());
                height++;
            }
            // Map-Array für frontend
            map = new char[height][width];

        } catch (IOException e) {
            LOGGER.error("Error loading map size from file '{}': {}", filePath, e.getMessage(), e);
            map = new char[0][0];// zurück setzen
            return;
        }
        // jetzt inhalt lesen
        try (BufferedReader secondReader = new BufferedReader(
                new FileReader("src/main/resources/maps/" + filePath + ".txt"))) {
            int x = 0;
            while ((line = secondReader.readLine()) != null) {
                for (int y = 0; y < line.length(); y++) {
                    char symbol = line.charAt(y);
                    map[x][y] = symbol;
                }
                x++;
            }
        } catch (IOException e) {
            LOGGER.error("Error loading map content from file '{}': {}", filePath, e.getMessage(), e);
            map = new char[0][0];
        }
    }
    
// Erstellt Tiles für BE
    private void createTiles() {
        if (map == null || map.length == 0) {
            tiles = new Tile[0][0]; // zurück setzen
            return;
        }
        // Tiles bekommt Größe der Map
        tiles = new Tile[map.length][map[0].length];

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                try {
                    tiles[x][y] = createTile(map[x][y]);
                } catch (IllegalArgumentException e) {
                    LOGGER.error("Invalid symbol '{}' at position ({}, {}): {}", map[x][y], x, y, e.getMessage(), e);
                    tiles[x][y] = null;
                }
            }
        }
    }

    // Erstellt ein Tile
    private Tile createTile(char symbol) {
        switch (symbol) {
            case '*': // Wand
                return new Tile(TileType.WALL);
            case ' ': // Frei
                return new Tile(TileType.SURFACE);
            default:
                throw new IllegalArgumentException("Unknown character in map: " + symbol);
        }
    }

    public char[][] getMap() {
        return map;
    }

    public int getWidth() {
        if (map != null && map.length > 0) {
            return map[0].length;
        }
        return 0;
    }

    public int getHeight() {
        if (map != null) {
            return map.length;
        }
        return 0;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
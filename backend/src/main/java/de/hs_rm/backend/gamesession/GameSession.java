package de.hs_rm.backend.gamesession;

import de.hs_rm.backend.gamelogic.characters.players.Chicken;
import de.hs_rm.backend.gamelogic.map.PlayMap;
import de.hs_rm.backend.gamelogic.map.Tile;
import de.hs_rm.backend.gamelogic.map.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasse für eine Spielsession (aktive Spielrunde)
 */
public class GameSession {
    private final String lobbyId;
    private boolean sessionIsActive;
     private final List<Chicken> chickens;

        public GameSession(String lobbyId, int chickenNum, PlayMap playmap) {
            this.lobbyId = lobbyId;
            this.sessionIsActive = false;
            this.chickens = createChickensOnRandomTiles(chickenNum, playmap);
        }

    /**
     * Erstellt eine Liste von Hühnern und fügt sie auf zufällige Tiles auf der Karte hinzu
     * @param chickenNum Anzahl der Hühner die erstellt werden sollen
     * @param playmap Karte auf der die Hühner erstellt werden sollen
     * @return Liste von Hühnern
     */
        public List<Chicken> createChickensOnRandomTiles(int chickenNum, PlayMap playmap) {
            List<Chicken> chickens = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < chickenNum; i++) {
                Tile randomTile;
                int index;
                do {
                    index = random.nextInt(playmap.getTilesList().size());
                    randomTile = playmap.getTilesList().get(index);
                } while (randomTile.getType() != TileType.SURFACE || randomTile.hasChicken());

                Chicken chicken = new Chicken(index % playmap.getWidth(), index / playmap.getWidth());
                chickens.add(chicken);
                //DONE: chicken zu random tile hinzufügen
                randomTile.addChicken(chicken);
            }

            return chickens;
        }

        public void endGameSession() {
            this.sessionIsActive = false;
        }

    public String getLobbyId() {
        return lobbyId;
    }

    public List<Chicken> getChickens() {
        return chickens;
    }

    public boolean isSessionIsActive() {
        return sessionIsActive;
    }

    public void setSessionIsActive(boolean sessionIsActive) {
        this.sessionIsActive = sessionIsActive;
    }
}

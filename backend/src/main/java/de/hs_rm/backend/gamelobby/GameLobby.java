package de.hs_rm.backend.gamelobby;

import de.hs_rm.backend.gamelogic.characters.players.*;
import de.hs_rm.backend.gamelogic.characters.players.Character;
import de.hs_rm.backend.gamelogic.map.PlayMap;
import de.hs_rm.backend.gamelogic.map.Tile;
import de.hs_rm.backend.gamelogic.map.TileType;
import de.hs_rm.backend.gamesession.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Klasse für die Lobby eines Spiels
 */
public class GameLobby {
    private final String lobbyId;
    private final String gameMasterName;
    private final List<Player> players;
    private boolean gameSessionStarted;
    private PlayMap playMap;
    private int chickenNum;

    private static final Logger LOGGER = LoggerFactory.getLogger(GameLobby.class);

    public GameLobby(String gameMasterName, String generatedLobbyId) {
        this.lobbyId = generatedLobbyId;
        this.gameMasterName = gameMasterName;
        this.players = new ArrayList<>();
        this.players.add(new Player(gameMasterName));
        this.gameSessionStarted = false;
        this.chickenNum = 0;
    }

    /**
     * Um das eigentliche Spiel zu starten von der Lobby aus
     *
     * @return GameSession
     */
    public GameSession startGameSession() {
        LOGGER.info("Starting game session for lobby: {}", this.lobbyId);

        // TODO: hier sollte random name als param übergeben werden
        this.playMap = new PlayMap("map1");

        // Hier wird Charakterliste erstellt und player zugewiesen
        Map<String, Character> characters = new HashMap<>();
        Random random = new Random();
        for (Player player : players) {
            Tile randomTile = null;
            // Wiederholen, bis ein Surface-Tile gefunden wird
            int index = -1;
            do {
                index = random.nextInt(this.playMap.getTilesList().size());
                randomTile = this.playMap.getTilesList().get(index);
            } while (randomTile.getType() != TileType.SURFACE || randomTile.hasCharacter());

            switch (player.getPlayerrole()) {
                // DONE: random position von Charakter
                case GHOST -> {

                    characters.put(player.getName(), new Ghost(1.0, index % this.playMap.getWidth(), index / this.playMap.getWidth()));
                    randomTile.addCharacter(characters.get(player.getName()));
                }
                case SNACKMAN -> {

                    characters.put(player.getName(), new Snackman(1.0, index % this.playMap.getWidth(), index / this.playMap.getWidth(), 3));
                    randomTile.addCharacter(characters.get(player.getName()));
                }
                default -> {
                    LOGGER.warn("Unknown player role for player: {}", player.getName());
                }
            }

        }

        GameSession gameSession = new GameSession(this.lobbyId, chickenNum, this.playMap);
        this.gameSessionStarted = true;
        return gameSession;
    }

    /**
     * Kickt einen Spieler aus der Lobby.
     * GameMaster kann nicht gekickt werden, und nur GameMaster kann kicken
     *
     * @param usernameKicking,    Benutzer, der gekickt wird
     * @param usernameToBeKicked, der Benutzer, der gekickt werden soll
     * @return boolean, true wenn der Spieler gekickt wird, false wenn nicht
     */
    public boolean kick(String usernameKicking, String usernameToBeKicked) {
        LOGGER.info("Kicking player {} from game lobby with ID: {}", usernameToBeKicked, this.lobbyId);

        if (usernameToBeKicked.contentEquals(this.gameMasterName)) {
            LOGGER.info("Game master cannot be kicked");
            return false;
        }

        if (!usernameKicking.contentEquals(this.gameMasterName)) {
            LOGGER.info("Only game master can kick");
            return false;
        }

        return removePlayer(usernameToBeKicked);
    }

    /**
     * Entfernt den Spieler aus der Lobby, der gerade geht.
     *
     * @return boolean, true wenn Spieler gegangen ist, false wenn nicht
     */
    public boolean leaveGame(Player player) {
        LOGGER.info("Player {} is leaving game lobby with ID: {}", player.getName(), this.lobbyId);
        if (player.getName().contentEquals(gameMasterName)) {
            throw new IllegalStateException("Gamemaster cannot leave the game.");
        }

        return removePlayer(player.getName());
    }

    /**
     * Spieler aus der Lobby entfernen
     *
     * @return boolean, true wenn Spieler entfernt wurde, false wenn nicht
     */
    private boolean removePlayer(String playerName) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().contentEquals(playerName)) {
                players.remove(i);
                LOGGER.info("Player {} has been removed", playerName);
                return true;
            }
        }
        LOGGER.info("Player {} not found in player list", playerName);
        return false;
    }

    /**
     * Einem Spieler in der Lobby beitreten
     *
     * @param player, zu verbindender Spieler
     * @return boolean, true wenn Spieler beigetreten ist, false wenn nicht
     */
    public boolean addPlayer(Player player) {
        boolean playerIsInExistingPlayers = players.stream()
                .anyMatch(existingPlayer ->
                        existingPlayer.getName().equals(player.getName())
                );

        if (!playerIsInExistingPlayers) {
            players.add(player);
            LOGGER.info("Player {} joined game lobby with ID: {}", player.getName(), this.lobbyId);
            return true;
        } else {
            LOGGER.info("Player already in Lobby!");
            return false;
        }
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setPlayMap(PlayMap playMap) {
        this.playMap = playMap;
    }

    public boolean isGameSessionStarted() {
        return gameSessionStarted;
    }

    public void setChicken(int total) {
        LOGGER.info("Chicken: {}, Game: {}", total, this.lobbyId);
        this.chickenNum = total;
    }
}

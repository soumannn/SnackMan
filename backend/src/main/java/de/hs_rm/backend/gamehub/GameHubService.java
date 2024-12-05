package de.hs_rm.backend.gamehub;

import de.hs_rm.backend.gamelobby.GameLobby;
import de.hs_rm.backend.gamelogic.characters.players.Player;
import de.hs_rm.backend.gamesession.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Die Klasse GameHubService bietet die zentrale Logik für die Verwaltung von Spiel-Lobbys {@link GameLobby}
 * und Sitzungen {@link GameSession}. Sie dient als Schnittstelle zwischen der Anwendungslogik und dem
 * Repository, indem sie Operationen wie das Erstellen, Starten, Beenden und Verwalten von Spielern, Lobbys
 * und aktiven Spiel Sitzungen ausführt.
 */
public class GameHubService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameHubService.class);
    private static final int ID_LENGTH = 5;
    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final GameHubRepository repo = new GameHubRepository();

    public GameHubService() {
    }

    /**
     * Erstellt GameLobby mit gameMaster als erstem Spieler
     *
     * @return GameLobby, welche neu erstellt wurde
     */
    public GameLobby createGameLobby(String gameMaster) {
        String gameId = generateGameId();
        GameLobby createdGameLobby = repo.addGameLobby(new GameLobby(gameMaster, gameId));
        LOGGER.info("Game lobby created with ID: {}", gameId);
        return createdGameLobby;
    }

    /**
     * Sucht die GameLobby mit dieser lobbyId und erstellt damit die GameSession, welche anschließend gestartet wird
     *
     * @return GameSession, welche gestartet wurde
     */
    public GameSession startGameSession(String lobbyId) {
        GameLobby gameLobby = repo.getGameLobbyById(lobbyId);
        GameSession gameSession = gameLobby.startGameSession();
        GameSession addedGameSession = repo.addGameSession(gameSession);
        LOGGER.info("Game session added to repository with ID: {}", gameSession.getLobbyId());
        return addedGameSession;
    }

    /**
     * Beendet GameSession und gibt die GameLobby mit der gleichen lobbyId zurück ohne weitere Modifikationen
     *
     * @return GameLobby, welche schon vor Spielstart existierte
     */
    public GameLobby endGameSession(String lobbyId) {
        GameSession gameSession = repo.getGameSessionById(lobbyId);
        gameSession.endGameSession();
        LOGGER.info("Game session ended with ID: {}", lobbyId);

        // get old game lobby again
        GameLobby gameLobby = repo.getGameLobbyById(lobbyId);
        LOGGER.info("Got game lobby with ID: {}", lobbyId);
        // TODO: Add modification to gamelobby, e.g. if player left game session before
        return gameLobby;
    }


    /**
     * Kickt Spieler aus GameLobby
     *
     * @return true, wenn Spieler gekickt wurde, sonst false
     */
    public boolean kick(String usernameKicking, String usernameKicked, String lobbyId) {
        GameLobby gameLobby = repo.getGameLobbyById(lobbyId);
        return gameLobby.kick(usernameKicking, usernameKicked);
    }


    /**
     * Fügt Player zur GameLobby hinzu
     *
     * @return true, wenn Spieler hinzugefügt wurde, sonst false
     */
    public boolean joinPlayerToLobby(String playerName, String lobbyId) {
        Player player = repo.getPlayerByName(playerName);
        GameLobby gameLobby = repo.getGameLobbyById(lobbyId);
        return gameLobby.addPlayer(player);
    }

    /**
     * Generiert eindeutige ID für GameLobby
     * synchronized: verhindert, dass mehrere Threads gleichzeitig doppelte IDs erzeugen
     *
     * @return eindeutige ID aus Buchstaben und Zahlen
     */
    private synchronized String generateGameId() {
        String newGameId;
        do {
            newGameId = generateRandomString();
        } while (repo.containsGameLobby(newGameId));
        return newGameId;
    }

    /**
     * Generiert zufälligen String aus Buchstaben und Zahlen
     *
     * @return zufälliger String aus ALPHA_NUMERIC_STRING
     */
    private String generateRandomString() {
        StringBuilder sb = new StringBuilder(GameHubService.ID_LENGTH);
        Random random = new Random();

        for (int i = 0; i < GameHubService.ID_LENGTH; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length()); // zwischen 0 und ALPHA_NUMERIC_STRING.length() - 1
            sb.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }
}

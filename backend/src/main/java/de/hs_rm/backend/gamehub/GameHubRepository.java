package de.hs_rm.backend.gamehub;

import de.hs_rm.backend.gamelobby.GameLobby;
import de.hs_rm.backend.gamelogic.characters.players.Player;
import de.hs_rm.backend.gamesession.GameSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class GameHubRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameHubRepository.class);

    private Map<String, Player> players = new HashMap<String, Player>();
    private Map<String, GameLobby> gameLobbies = new HashMap<String, GameLobby>();
    private Map<String, GameSession> gameSessions = new HashMap<String, GameSession>();

    public GameHubRepository() {}

    public Player getPlayerByName(String name) {
        Player player = players.get(name);
        if (player == null) LOGGER.info("Player with name {} not found in repository", name);
        return player;
    }

    public GameLobby getGameLobbyById(String lobbyId) {
        GameLobby gameLobby = gameLobbies.get(lobbyId);
        if (gameLobby == null) LOGGER.info("Game lobby with ID {} not found in repository", lobbyId);
        return gameLobby;
    }

    public boolean containsGameLobby(String lobbyId) {
        return gameLobbies.containsKey(lobbyId);
    }

    public GameLobby addGameLobby(GameLobby gameLobby) {
        gameLobbies.put(gameLobby.getLobbyId(), gameLobby);
        return gameLobby;
    }

    public GameSession getGameSessionById(String lobbyId) {
        GameSession gameSession = gameSessions.get(lobbyId);
        if (gameSession == null) LOGGER.info("Game session with ID {} not found in repository", lobbyId);
        return gameSession;
    }

    public GameSession addGameSession(GameSession gameSession) {
        gameSessions.put(gameSession.getLobbyId(), gameSession);
        return gameSession;
    }
}

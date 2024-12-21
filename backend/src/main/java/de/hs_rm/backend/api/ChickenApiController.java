package de.hs_rm.backend.api;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import de.hs_rm.backend.gamelogic.Game;
import de.hs_rm.backend.gamelogic.GameService;
import de.hs_rm.backend.gamelogic.map.PlayMap;
import de.hs_rm.backend.messaging.GameMessagingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/chicken")
public class ChickenApiController {

    @Autowired
    GameMessagingService messagingService;

    @Autowired
    GameService gameService;

    @Value("${scripts.dir}") 
    private String scriptsDirectory;

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayMap.class);


    @PostMapping("moveChicken/{gameId}/{xCoordinate}/{yCoordinate}")
    public String postMethodName(@RequestBody String entity) {
        // ToDO Aron
        return entity;
    }

    // Liste aller Hühner an Frontend geben
    @GetMapping("spawnChicken/{gameId}")
    public ResponseEntity<?> getChickenPosition(@PathVariable String gameId) {
        Game existingGame = gameService.getGameById(gameId);

        if(existingGame == null) {
            return createErrorResponse("No game found");
        }

        return createOkResponse(existingGame.getChickens());
    }
    

    // Todo Aron: Eventuell createErrorResponse createOkResponse in Oberklasse auslagern
     
    private ResponseEntity<Map<String, Object>> createErrorResponse(String feedbackMessage) {
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("status", "error");
        feedbackData.put("feedback", feedbackMessage);
        feedbackData.put("time", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(feedbackData);
    }


    private ResponseEntity<Map<String, Object>> createOkResponse(Object data) {
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("status", "error");
        feedbackData.put("feedback", data);
        feedbackData.put("time", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.OK).body(feedbackData);
    }     
    
    
      // Helper method for standardized ok response
    // private ResponseEntity<Map<String, Object>> createOkResponse(Game game) {
    //     Map<String, Object> feedbackData = new HashMap<>();
    //     ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    //     String gameJSON;
    //     feedbackData.put("status", "ok");
    //     feedbackData.put("time", LocalDateTime.now().toString());
    //     try {
    //         gameJSON = ow.writeValueAsString(game);
    //         feedbackData.put("feedback", game);
    //     } catch (JsonProcessingException e) {
    //         e.printStackTrace();
    //         feedbackData.put("feedback", "something in backend went wrong!");
    //     }

    //     return ResponseEntity.status(HttpStatus.OK).body(feedbackData);
    // }
}

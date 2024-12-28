package de.hs_rm.backend.gamelogic.characters.players;

import java.io.File;

import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.hs_rm.backend.gamelogic.map.PlayMap;
import jakarta.annotation.PreDestroy;

@Service
public class ChickenMovementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayMap.class);


    @Value("${scripts.dir}")
    private String scriptsDirectory;

    private PythonInterpreter interpreter;



    public ChickenMovementService() {
        interpreter = new PythonInterpreter();

        try{
        String scriptPath = "bot_movement.py";
            
            File scriptFile = new File(scriptsDirectory, scriptPath);

            if (scriptFile.exists()) {
                LOGGER.info("Starte Python Skript...");
                interpreter.execfile(scriptsDirectory + "/" + scriptPath);
                LOGGER.info("Python Skript erfolgreich gestartet");
            } else {
                LOGGER.error("Python Skript konnte nicht gestartet werden: " + scriptFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }



    public Object executePythonFunction(String functionName, Object... args) {

            try{
                StringBuilder pythonCall = new StringBuilder(functionName + "(");
                for( int i = 0; i < args.length; i++) {
                    pythonCall.append(args[i]);
                    if( i < args.length - 1) {
                        pythonCall.append(",");
                    }
                }
                pythonCall.append(")");
                interpreter.exec("result =" + pythonCall.toString());
                // Was wird zurückgegeben?
                return interpreter.get("result");
            } catch(Exception e) {
                LOGGER.error("ExecutePythonFunction doesnt work: '{}':{} ", functionName, e);
                return null;
            }
            
        
    }

    @PreDestroy
    public void cleanup() {
        if (interpreter != null) {
            interpreter.close();
            LOGGER.info("Python Interpreter geschlossen");
        } else {
            LOGGER.warn("Python Interpreter doesnt exist");
        }
    }
    
}

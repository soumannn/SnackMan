package de.hs_rm.backend.gamelogic.characters.players;

public class ObjectsItems implements Item {
    private String name;
    private PlayerPosition playerPosition;

    
    public ObjectsItems(String name, PlayerPosition playerPosition) {
        this.name = name;
        this.playerPosition = playerPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    // Getter für die Position
    @Override
    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    //TODO: applyeffekte müssen noch gemach werden
    // Anwenden des Effekts (z. B. Fähigkeit aktivieren)
    @Override
    public void applyEffect(Player player) {
        //System.out.println(player.getName() + " nutzt " + name + ": " + effectDescription);
        // Hier könntest du weitere logische Auswirkungen hinzufügen, z. B. Energiegewinn, Fähigkeiten aktivieren etc.
    }


}

package de.hs_rm.backend.gamelogic.characters.players;
//Diese Klasse beinhaltet besondere Eigenschaften bzw. Items die im Spiel gesammelt werden können
public interface Item {
    //Namen des Items
    String getName();

    //Position des Items
    PlayerPosition getPlayerPosition();

    void applyEffect(Player player); //player wird mitgegeben: um zu wissen auf welchen Spieler der Effekt angewendet wird

}

package edu.server.room;

import edu.server.Connection;
import edu.server.engine.Game;
import edu.server.player.Player;

import java.util.Objects;

public class Room extends Game{

    private int roomID;
//  Question is how to generate specific id for Room

    /**
     * @param con
     * @return players[]
     * players[0] = player with this con
     * players[1] = player remainder
     */
    public Player[] getPlayerByCon(Connection con){
        Player[] players = new Player[2];
        if(getGuestPlayer().getCon() == con){
            players[0] = getGuestPlayer();
            players[1] = getHostPlayer();
        }else if(getHostPlayer().getCon() == con){
            players[0] = getHostPlayer();
            players[1] = getGuestPlayer();
        }else{
            return null;
        }
        return players;
    }

    public boolean checkHostPlayer(Connection con){
        if(getHostPlayer().getCon() == con)
            return true;
        return false;
    }

    public Player getHostPlayer() {
        return this.players[0];
    }

    public void setHostPlayer(Player hostPlayer) {
        this.players[0] = hostPlayer;
    }

    public Player getGuestPlayer() {
        return this.players[0];
    }

    public void setGuestPlayer(Player guestPlayer) {
        this.players[1] = guestPlayer;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roomID);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Room) {
            Room room = (Room) obj;
            return room.roomID == this.roomID;
        }
        return false;
    }

}

package edu.server.room;

import edu.server.engine.Game;
import edu.server.player.Player;

import java.util.Objects;

public class Room {
    private Game game;
    private Player hostPlayer;
    private Player guestPlayer;
    private int roomID;
//  Question is how to generate specific id for Room

    public Room(Player hostPlayer) {
        this.game = new Game();
        this.hostPlayer = hostPlayer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getHostPlayer() {
        return hostPlayer;
    }

    public void setHostPlayer(Player hostPlayer) {
        this.hostPlayer = hostPlayer;
    }

    public Player getGuestPlayer() {
        return guestPlayer;
    }

    public void setGuestPlayer(Player guestPlayer) {
        this.guestPlayer = guestPlayer;
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

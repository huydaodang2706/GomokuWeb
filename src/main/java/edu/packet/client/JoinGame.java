package edu.packet.client;

import edu.packet.Packet;

public class JoinGame extends Packet {
    private int roomID;
    private String username;

    public JoinGame(int roomID, String username) {
        this.roomID = roomID;
        this.username = username;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

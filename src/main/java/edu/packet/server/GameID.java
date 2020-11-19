package edu.packet.server;

import edu.packet.Packet;

import java.io.Serializable;

public class GameID extends Packet {
    private int roomID;

    public GameID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }
}

package edu.common.packet.server;

import edu.common.packet.Packet;

public class GameID extends Packet {
    private int roomID;

    public GameID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }
}

package edu.common.packet.server;

import com.google.gson.annotations.SerializedName;
import edu.common.packet.Packet;

public class GameID extends Packet {
    @SerializedName("roomID")
    private int roomID;

    public GameID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }
}

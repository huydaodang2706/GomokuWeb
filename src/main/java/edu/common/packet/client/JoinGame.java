package edu.common.packet.client;

import com.google.gson.annotations.SerializedName;
import edu.common.packet.Packet;

public class JoinGame extends Packet {
    @SerializedName("roomID")
    private int roomID;
    @SerializedName("username")
    private String username;

    public JoinGame(int roomID, String username) {
        this.roomID = roomID;
        this.username = username;
        this.setId(new String("0x04"));
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

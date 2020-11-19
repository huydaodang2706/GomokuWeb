package edu.common.packet.client;

import edu.common.packet.Packet;

public class CreateGame extends Packet {
    private String username;

    public CreateGame(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

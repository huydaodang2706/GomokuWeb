package edu.packet.server;

import edu.packet.Packet;

public class GuestFound extends Packet {
    private String username;

    public GuestFound(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

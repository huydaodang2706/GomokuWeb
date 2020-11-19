package edu.packet.server;

import edu.packet.Packet;

public class ConfirmRule extends Packet {
    private boolean status;

    public ConfirmRule(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

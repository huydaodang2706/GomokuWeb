package edu.common.packet.client;

import edu.common.packet.Packet;

public class StartRequest extends Packet {
    public StartRequest() {
        this.setId(new String("0x07"));
    }
}

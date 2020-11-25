package edu.common.packet.client;

import edu.common.packet.Packet;

public class RuleSet extends Packet {

    private int size;
    private long gameTime;
    private long moveTime;

    public RuleSet(int size, long gameTime, long moveTime) {
        this.size = size;
        this.gameTime = gameTime;
        this.moveTime = moveTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getGameTime() {
        return gameTime;
    }

    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }

    public long getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(long moveTime) {
        this.moveTime = moveTime;
    }
}

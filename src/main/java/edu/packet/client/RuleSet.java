package edu.packet.client;

import edu.packet.Packet;

public class RuleSet extends Packet {

    private int size;
    private int gameTime;
    private int moveTime;

    public RuleSet(int size, int gameTime, int moveTime) {
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

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getMoveTime() {
        return moveTime;
    }

    public void setMoveTime(int moveTime) {
        this.moveTime = moveTime;
    }
}

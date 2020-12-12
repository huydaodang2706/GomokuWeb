package edu.common.packet.client;

import com.google.gson.annotations.SerializedName;
import edu.common.packet.Packet;

public class RuleSet extends Packet {
    @SerializedName("size")
    private int size;
    @SerializedName("gameTime")
    private long gameTime;
    @SerializedName("moveTime")
    private long moveTime;

    public RuleSet(int size, long gameTime, long moveTime) {
        this.size = size;
        this.gameTime = gameTime;
        this.moveTime = moveTime;
        this.setId(new String("0x02"));
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

package edu.common.packet;

public class DrawResponse extends Packet{
    private boolean agree;

    public DrawResponse(boolean agree) {
        this.agree = agree;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}

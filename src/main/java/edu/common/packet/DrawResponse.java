package edu.common.packet;

import com.google.gson.annotations.SerializedName;

public class DrawResponse extends Packet{
    @SerializedName("agree")
    private boolean agree;

    public DrawResponse(boolean agree) {
        this.agree = agree;
        this.setId(new String("0x0e"));
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }
}

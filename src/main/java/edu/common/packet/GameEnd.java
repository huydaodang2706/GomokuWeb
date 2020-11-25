package edu.common.packet;

public class GameEnd extends Packet{
    public enum EndingType { HOST_WON, GUEST_WON, DRAW };
    public enum ReasonType {
        BY_WINNING_MOVE,
        BY_OPPONENT_SURRENDER,
        BY_OPPONENT_LEFT,
        BY_AGREEMENT,
        BY_BOARD_FULL,
        BY_BOTH_DISCONNECTION
    }
    private EndingType endingType;
    private ReasonType reason;

    public EndingType getEndingType() {
        return endingType;
    }

    public void setEndingType(EndingType endingType) {
        this.endingType = endingType;
    }

    public ReasonType getReason() {
        return reason;
    }

    public void setReason(ReasonType reason) {
        this.reason = reason;
    }
}

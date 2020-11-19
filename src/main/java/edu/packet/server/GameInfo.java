package edu.packet.server;

import edu.packet.Packet;
import edu.packet.client.RuleSet;

public class GameInfo extends Packet {
    private RuleSet ruleSet;
    private String hostUsername;

    public GameInfo(RuleSet ruleSet, String hostUsername) {
        this.ruleSet = ruleSet;
        this.hostUsername = hostUsername;
    }

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public void setRuleSet(RuleSet ruleSet) {
        this.ruleSet = ruleSet;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setHostUsername(String hostUsername) {
        this.hostUsername = hostUsername;
    }
}

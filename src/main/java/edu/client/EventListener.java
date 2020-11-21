package edu.client;

import edu.client.events.GameListener;
import edu.common.packet.StonePut;
import edu.common.packet.server.ConfirmRule;
import edu.common.packet.server.GameID;
import edu.common.packet.server.GameInfo;
import edu.common.packet.server.GuestFound;

import java.util.LinkedList;
import java.util.List;

public class EventListener  {
    private final List<GameListener> listeners;

    public EventListener() {
        this.listeners = new LinkedList<>();
    }

    public void received(Object p, Client client){
        if(p instanceof GameID){
            GameID idPacket = (GameID)p;
            handleIDPacket(idPacket,client);
        }else if(p instanceof ConfirmRule){
            ConfirmRule cfRulePacket = (ConfirmRule) p;
            handleCfRulePacket(cfRulePacket,client);
        }else if(p instanceof GuestFound){
            GuestFound guestfd = (GuestFound) p;
            handleGuestFdPacket(guestfd, client);
        }else if(p instanceof GameInfo){
            GameInfo gameInfoPacket = (GameInfo) p;
            handleGameInfoPacket(gameInfoPacket, client);
        }else if(p instanceof StonePut){

        }
    }

    public void handleIDPacket(GameID idPacket, Client client){
        //Present game id (room id) to gui of host player
    }

    public void handleCfRulePacket(ConfirmRule cfRulePacket, Client client){
        //Send message "Rule is set successfully" to gui
    }

    public void handleGuestFdPacket(GuestFound guestfd, Client client){
        //Send message "Guest found" to gui
    }

    public void handleGameInfoPacket(GameInfo gameInfoPacket, Client client){

    }

    public void addListener(GameListener listener) {
        this.listeners.add(listener);
    }

    public List<GameListener> getListeners() {
        return listeners;
    }
}

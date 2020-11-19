package edu.server;

import edu.common.packet.StonePut;
import edu.common.packet.client.CreateGame;
import edu.common.packet.client.JoinGame;
import edu.common.packet.client.RuleSet;
import edu.common.packet.client.StartRequest;
import edu.common.packet.server.GameID;
import edu.common.packet.server.GameInfo;
import edu.common.packet.server.GuestFound;
import edu.server.engine.Game;
import edu.server.engine.GameState;
import edu.common.Move;
import edu.server.player.Player;
import edu.server.room.Room;
import edu.server.room.RoomList;

public class EventListener {
    /**
     * @param p
     * @param con
     * Function to received object from client
     * Classify request packet and execute
     */
    public void received(Object p, Connection con){
        if(p instanceof CreateGame){
            CreateGame crtPacket = (CreateGame)p;
            handleCreateGame(crtPacket,con);
        }else if(p instanceof RuleSet){
            RuleSet rulePacket = (RuleSet) p;
            handleRuleSet(rulePacket,con);
        }else if(p instanceof JoinGame){
            JoinGame joinPacket = (JoinGame)p;
            handleJoinGame(joinPacket,con);
        }else if(p instanceof StartRequest){
            StartRequest startRq = (StartRequest)p;
            handleStartRq(startRq,con);
        }else if(p instanceof StonePut){
            StonePut stone = (StonePut)p;
            handleStonePutRq(stone,con);
        }
        // GameEnd
        // Surrender
        // OpponentLeft
        // OfferDraw
        // DrawResponse
    }

    /**
     * @param crtPacket
     * @param con
     * Handle packet create new game
     */
    public void handleCreateGame(CreateGame crtPacket,Connection con){
        Room room = new Room(new Player(crtPacket.getUsername(),con));
        room.getHostPlayer().getCon().setRoom(room);
        // Add room to room list
        RoomList.roomList.add(room);
        GameID gameID = new GameID(room.getRoomID());
        con.sendObject(gameID);
    }

    /**
     * @param rulePacket
     * @param con
     */
    public void handleRuleSet(RuleSet rulePacket, Connection con){
        //Only host client can set rule
        Game game = con.getRoom().getGame();
        game.getSettings().setSize(rulePacket.getSize());

        if(rulePacket.getGameTime() == -1){
            game.getSettings().setGameTimingEnabled(false);
        }else{
            game.getSettings().setGameTimingEnabled(true);
            game.getSettings().setGameTimeMillis(rulePacket.getGameTime()*1000);
        }

        if(rulePacket.getMoveTime() == -1){
            game.getSettings().setMoveTimingEnabled(false);
        }else{
            game.getSettings().setMoveTimingEnabled(true);
            game.getSettings().setMoveTimeMillis(rulePacket.getMoveTime());
        }

        if(con.getRoom().getGuestPlayer() == null){
            // Do nothing
        }else{
            // Create GameInfo packet and send this packet to guest client
            GameInfo gameInfo = new GameInfo(rulePacket,con.getRoom().getHostPlayer().getUsername());
            con.getRoom().getGuestPlayer().getCon().sendObject(gameInfo);
        }
    }

    /**
     * @param joinPacket
     * @param con
     */
    public void handleJoinGame(JoinGame joinPacket, Connection con){
        Room room = findRoom(joinPacket.getRoomID());
        if(room != null) {
            // Add guest player to room, add connection room -> connection
            room.setGuestPlayer(new Player(joinPacket.getUsername(),con));
            room.getGuestPlayer().getCon().setRoom(room);

            // Set user players to game
            Player[] players = new Player[2];
            players[0] = room.getHostPlayer();
            players[1] = room.getGuestPlayer();
            room.getGame().setPlayers(players);

            // Send guest found object to host
            GuestFound guestFound = new GuestFound(joinPacket.getUsername());
            room.getHostPlayer().getCon().sendObject(guestFound);
        }else{
            System.out.println("Room not found, Join Request refused");
            //Send back to client some code
        }

//        GameInfo info = new GameInfo()
    }

    /**
     * @param startRq
     * @param con
     */
    public void handleStartRq(StartRequest startRq, Connection con){
        Room room = con.getRoom(); // Get room from connection
        if(room != null){
            if(room.getGuestPlayer() != null){
                GameState state = new GameState(room.getGame().getSettings().getSize());
                room.getGame().setState(state);
                room.getGame().start();
            }else{
                // Cho nay xu ly ben front end
                System.out.println("Guest player not found");
            }
            // Send GameStart packet to 2 client
        }else{
            System.out.println("Room not found, Start Request refused");
            //Send back to client some code
        }
    }

    /**
     * @param stone
     * @param con
     */
    public void handleStonePutRq(StonePut stone,Connection con){
        Move newMove = new Move(stone.getX(),stone.getY());
        Game game = con.getRoom().getGame();
        if(game.setUserMove(newMove)){
            //Do something
        }else{
            //Do something
        }
    }



    /**
     * @param roomID
     * @return room found
     * Find a room in room list with id
     */
    public Room findRoom(int roomID){
        for (Room x : RoomList.roomList){
            if(x.getRoomID() == roomID)
                return x;
        }
        return null;
    }
}

package edu.server;

import com.google.gson.Gson;
import edu.common.packet.*;
import edu.common.packet.client.*;
import edu.common.packet.server.GameID;
import edu.common.packet.server.GameInfo;
import edu.common.packet.server.GuestFound;
import edu.server.engine.Game;
import edu.server.engine.GameState;
import edu.common.Move;
import edu.server.player.Player;
import edu.server.room.Room;
import edu.server.room.RoomList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EventListener {

    public void received_data(String p, Connection con) {
        JSONParser parser = new JSONParser();
        JSONObject packetJson = null;
        try {
            packetJson = (JSONObject) parser.parse(p);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String packetID = (String) packetJson.get("id");

        Gson gson = new Gson();
        switch (packetID){
            case "0x00":
                //Create Game
                CreateGame crtPacket = gson.fromJson(p,CreateGame.class);
                handleCreateGame(crtPacket,con);
                break;
            case "0x02":
                //RuleSet
                RuleSet rulePacket = gson.fromJson(p,RuleSet.class);
                handleRuleSet(rulePacket,con);
                break;
            case "0x04":
                //Join Game
                JoinGame joinPacket = gson.fromJson(p,JoinGame.class);
                handleJoinGame(joinPacket,con);
                break;
            case "0x07":
                //Start Request
                StartRequest startRq = gson.fromJson(p,StartRequest.class);
                handleStartRq(startRq,con);
                break;
            case "0x09":
                //Stone Put
                StonePut stone = gson.fromJson(p,StonePut.class);
                handleStonePutRq(stone,con);
                break;
            case "0x0a":
                //Surrender
                // If a player surrender -> GameEnd
                Surrender surPacket = gson.fromJson(p,Surrender.class);
                handleSurPacket(surPacket,con);
                break;
            case "0x0b":
                //Leave Game
                // If opponent left the winner is the remainder
                // Send GameEnd to only winner
                handleLeaveGame(con);
                break;
            case "0x0d":
                //Offer Draw
                handleDrawRq(con);
                break;
            case "0x0e":
                //Draw Response
                DrawResponse drawRs = gson.fromJson(p,DrawResponse.class);
                handleDrawRs(drawRs,con);
                break;
            default:
                break;
        }
    }

    /**
     * @param crtPacket
     * @param con
     * Handle packet create new game
     */
    public void handleCreateGame(CreateGame crtPacket,Connection con){
        Room room = new Room();
        room.setHostPlayer(new Player(crtPacket.getUsername(),con));
        room.getHostPlayer().getCon().setRoom(room);

        // Create new ID for this room
        room.setRoomID(RoomList.roomList.getLast().getRoomID() + 1);

        // Add room to the end of room list
        RoomList.roomList.addLast(room);
        GameID gameID = new GameID(room.getRoomID());
        con.sendObject(gameID);
    }

    /**
     * @param rulePacket
     * @param con
     */
    public void handleRuleSet(RuleSet rulePacket, Connection con){
        //Only host client can set rule
        Game game = con.getRoom();
        game.getSettings().setSize(rulePacket.getSize());

        if(rulePacket.getGameTime() == -1){
            game.getSettings().setGameTimingEnabled(false);
        }else{
            game.getSettings().setGameTimingEnabled(true);
            game.getSettings().setGameTimeMillis(rulePacket.getGameTime());
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
            if(room.getGuestPlayer() == null){
                // Add guest player to room, add connection room -> connection
                room.setGuestPlayer(new Player(joinPacket.getUsername(),con));
                room.getGuestPlayer().getCon().setRoom(room);

                // Send guest found object to host
                GuestFound guestFound = new GuestFound(joinPacket.getUsername());
                room.getHostPlayer().getCon().sendObject(guestFound);

                // Send game info to guest
                RuleSet ruleSet = new RuleSet(room.getSettings().getSize(),
                                    room.getSettings().getGameTimeMillis(),
                                    room.getSettings().getMoveTimeMillis());
                GameInfo info = new GameInfo(ruleSet,room.getHostPlayer().getUsername());
                con.sendObject(info);
            }else{
                // Room guest existed
                System.out.println("Room fulled, cannot join");
            }
        }else{
            System.out.println("Room not found, Join Request refused");
            //Send back to client some code
        }
    }

    /**
     * @param startRq
     * @param con
     */
    public void handleStartRq(StartRequest startRq, Connection con){
        Room room = con.getRoom(); // Get room from connection
        if(room != null){
            if(room.getGuestPlayer() != null){
                // Create new state and start game (room extends game)
                GameState state = new GameState(room.getSettings().getSize());
                room.setState(state);
                room.start();
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
        Game game = con.getRoom();
        if(game.setUserMove(newMove)){
            //Do something
        }else{
            //Do something
        }
    }


    /**
     *
     * @param surPacket
     * @param con
     */
    public void handleSurPacket(Surrender surPacket,Connection con){
        // Send EndGame Packet
        Room room = con.getRoom();

        Player[] players = room.getPlayerByCon(con);
        Player surPlayer = players[0];
        Player winPlayer = players[1];

        GameEnd gameEnd = new GameEnd();
        gameEnd.setReason(GameEnd.ReasonType.BY_OPPONENT_SURRENDER);

        if(winPlayer == room.getHostPlayer()){
            gameEnd.setEndingType(GameEnd.EndingType.HOST_WON);
        }else if(winPlayer == room.getGuestPlayer()){
            gameEnd.setEndingType(GameEnd.EndingType.GUEST_WON);
        }

        winPlayer.getCon().sendObject(gameEnd);
        surPlayer.getCon().sendObject(gameEnd);
    }

    /**
     * Receive OfferDraw Packet and forward to other player
     * @param con
     */
    public void handleDrawRq(Connection con){
        Room room = con.getRoom();

        Player[] players = room.getPlayerByCon(con);
        Player drawPlayer = players[0];
        Player recvDrawPlayer = players[1];

        recvDrawPlayer.getCon().sendObject(drawPlayer);
    }

    public  void handleDrawRs(DrawResponse drawRs,Connection con){
        Room room = con.getRoom();

        if(drawRs.isAgree()){
            GameEnd gameEnd = new GameEnd();
            gameEnd.setEndingType(GameEnd.EndingType.DRAW);
            gameEnd.setReason(GameEnd.ReasonType.BY_AGREEMENT);

            room.getHostPlayer().getCon().sendObject(gameEnd);
            room.getGuestPlayer().getCon().sendObject(gameEnd);
        }else{
            // Forward drawRs to other player to player resume Move
            Player[] players = room.getPlayerByCon(con);
            players[1].getCon().sendObject(drawRs);
        }
    }

    /**
     * @param con
     */
    public void handleLeaveGame(Connection con){
        Room room = con.getRoom();
        Player[] players = room.getPlayerByCon(con);

        if(room.checkAlive()){
            Player leftPlayer = players[0];
            Player winPlayer = players[1];

            GameEnd gameEnd = new GameEnd();
            gameEnd.setReason(GameEnd.ReasonType.BY_OPPONENT_LEFT);

            if(winPlayer == room.getHostPlayer()){
                gameEnd.setEndingType(GameEnd.EndingType.HOST_WON);
            }else if(winPlayer == room.getGuestPlayer()){
                gameEnd.setEndingType(GameEnd.EndingType.GUEST_WON);
            }

            // In this case only need to send to winner player
            winPlayer.getCon().sendObject(gameEnd);

            // If remain player is not host player set it host
            if(room.checkHostPlayer(con)){
                room.setHostPlayer(winPlayer);
            }
            room.setGuestPlayer(null);
        }else{
            if(players[1] == null){
                //No players in this room, remove this room
                RoomList.roomList.remove(room);
                return;
            }else if(room.checkHostPlayer(con)){
                //Set remain player as host player
                room.setHostPlayer(players[1]);
            }
            room.setGuestPlayer(null);

            //Send ClientLeft object to remain player
            players[1].getCon().sendObject(new OpponentLeft());
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
        }else if(p instanceof Surrender){
            // If a player surrender -> GameEnd
            Surrender surPacket = (Surrender)p;
            handleSurPacket(surPacket,con);
        }else if(p instanceof LeaveGame){
            // If opponent left the winner is the remainder
            // Send GameEnd to only winner
            handleLeaveGame(con);
        }else if(p instanceof OfferDraw){
            // A player send draw offer request to another player
            OfferDraw drawRq = (OfferDraw)p;
            handleDrawRq(con);
        }else if(p instanceof DrawResponse){
            // A player received draw offer request and response
            DrawResponse drawRs = (DrawResponse)p;
            handleDrawRs(drawRs,con);
        }
        // GameEnd
        // Surrender
        // OpponentLeft
        // OfferDraw
        // DrawResponse
    }
}

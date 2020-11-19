package edu.server.player;

import edu.server.Connection;
import edu.server.engine.GameState;
import edu.server.engine.Move;

public class Player {

    private String username;
    private Move move;
    private Connection con;

    /**
     * Create a new player.
     * @param username Game information
     */
    public Player(String username,Connection con) {
        this.username = username;
        this.con = con;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    /**
     * Request a move from this player.
     * @param state Current game state
     * @return Move the player wants to make
     */
    public Move getMove(GameState state){
        // Suspend until the user clicks a valid move (handled by the game)
        try {
            synchronized(this) {
                this.wait();
            }
        } catch(InterruptedException e) {
            return null;
        }
        return move;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}

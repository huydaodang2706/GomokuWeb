package edu.server;

import com.google.gson.Gson;
import edu.server.room.Room;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class Connection implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private EventListener listener;
    private boolean running = false;

    public int id;
    public String playerName;
    public Room room = null;

    public Connection(Socket socket, int id) {
        this.socket = socket;
        this.id = id;

        try {
            this.out = new DataOutputStream(socket.getOutputStream());
            this.in = new DataInputStream(socket.getInputStream());
            listener = new EventListener();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Run a thread of specific client
     * Read input of Client to Server
     */
    @Override
    public void run() {
        running = true;

        while (running) {
            try {
                String data = in.readUTF();
//              Execute object received
                listener.received_data(data, this);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        if (!socket.isClosed()) {
            close();

        }
    }

    /**
     * Close socket and connection
     */
    public void close() {
        try {
//          Missing some code
            running = false;
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param packet
     * Convert Object Response packet to String
     * Send string of response object to Client Socket
     */
    public void sendObject(Object packet) {
        try {
//            out.reset();
            Gson gson = new Gson();
            //Convert Object to json and to string
            String data = gson.toJson(packet).toString();
            out.writeUTF(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public EventListener getListener() {
        return listener;
    }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}

package edu.server;

import edu.server.room.Room;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class Connection implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private EventListener listener;
    private boolean running = false;

    public int id;
    public String playerName;
    public Room room = null;

    public Connection(Socket socket, int id) {
        this.socket = socket;
        this.id = id;

        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
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
        try {
            running = true;

            while (running) {
                try {
                    Object data = in.readObject();
//                  Execute object received
                    listener.received(data, this);
                } catch (ClassNotFoundException | EOFException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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
     * Send object of response object to Client Socket
     */
    public void sendObject(Object packet) {
        try {
            out.reset();
            out.writeObject(packet);
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

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import msg.Codes;

/**
 *
 * @author lucas
 */
public class ConnectionListener implements Runnable {

    private final ServerSocket server;
    private final InterpreteMsg interpretate;
    
    public ConnectionListener(ServerSocket server) {
        this.server = server;
        interpretate = new InterpreteMsgConcrete();
    }

    @Override
    public void run() {
        try {
            Socket client;
            ObjectInputStream connection;
            Object login;
            while (true) {
                client = server.accept();
                connection = new ObjectInputStream(client.getInputStream());
                login = connection.readObject();
                interpretate.parseMsg(login, client, connection);
            }
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_LOGIN );
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.Socket;
import mediator.ConcreteConnection;
import mediator.Connection;
import msg.Mensage;

/**
 *
 * @author lucas
 */
public abstract class AbstractClient {

    protected final Connection mediator;
    protected final Socket clientSocket;
    private final String login;

    /**
     * The constructor.
     *
     * @param clientSocket the client's socket
     * @param login the login name
     */
    public AbstractClient(Socket clientSocket, String login) {
        this.mediator = ConcreteConnection.getInstance();
        this.clientSocket = clientSocket;
        this.login = login;
    }

    /**
     * Get the login.
     *
     * @return the login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Send a mensage for this client
     *
     * @param msg the mensge to be sent
     */
    public abstract void sendMensage(Mensage msg);

    /**
     * Check if the OutPutStream was created
     *
     * @return true if it was or false if doesn't
     */
    public abstract boolean checkOutputStream();

    /**
     * Disconnect this client.
     *
     * @throws IOException
     */
    public void disconnect() throws IOException {
        this.clientSocket.close();
    }

    /**
     * The Client's IP
     *
     * @return the IP
     */
    public String getIP() {
        return clientSocket.getInetAddress().getHostAddress();
    }
}

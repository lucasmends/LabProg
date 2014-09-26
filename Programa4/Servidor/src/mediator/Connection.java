/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import client.AbstractClient;
import msg.Mensage;

/**
 *
 * @author lucas
 */
public interface Connection {

    /**
     * Check the mensage received from a client, if it is for the chat send for
     * all, if it is for disconnect do it also.
     *
     * @param msg the mensage
     */
    public void receiveMsg(Object msg);

    /**
     * Send a mensage for all int he list
     *
     * @param msg the mensage
     */
    public void sendMensage(Mensage msg);

    /**
     * Add a client to the chat only if it dos not have an used username.
     * @param client client to be added
     */
    public void addClient(AbstractClient client);

    /**
     * Disconnect this client from the chat.
     * @param client client to be disconnected
     */
    public void disconnet(AbstractClient client);

    /**
     * Force a client to disconnect
     * @param client client to be disconnected
     */
    public void pop(AbstractClient client);
    /**
     * Update the Client's list for all clients.
     */
    public void updateClientList();

    /**
     * Close connection for all clients.
     */
    public void close();
}

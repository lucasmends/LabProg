/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import client.Client;
import client.AbstractClient;
import java.util.Vector;
import msg.Codes;
import msg.Mensage;

/**
 *
 * @author lucas
 */

public class ConcreteConnection implements Connection {

    private final static ConcreteConnection instance = new ConcreteConnection();

    private ConcreteConnection() {
        clients = new Vector();
    }

    public static ConcreteConnection getInstance() {
        return instance;
    }

    private Vector clients;

    @Override
    public synchronized void sendMensage(Mensage msg) {
        for (Object a : this.clients) {
            AbstractClient client = (Client) a;
            client.sendMensage(msg);
        }
    }

    @Override
    public synchronized void receiveMsg(Object msg) {
        if (msg instanceof Mensage) {
            Mensage mensage = (Mensage) msg;
            if (mensage.getCode() == Codes.MENSAGE_ALL) {
                this.sendMensage(mensage);
            } else if (mensage.getCode() == Codes.DISCONNECT) {
                AbstractClient disconnect = null;
                for (Object a : clients) {
                    AbstractClient client = (Client) a;
                    if (client.getLogin().equals(mensage.getLogin())) {
                        disconnect = client;
                        break;
                    }
                }

                this.disconnet(disconnect);
            }
        }
    }

    @Override
    public void addClient(AbstractClient client) {
        if (!client.checkOutputStream()) {
            this.disconnet(client);
            return;
        }
        boolean check = true;
        for (Object a : clients) {
            AbstractClient clientConnected = (Client) a;
            if (client.getLogin().equals(clientConnected.getLogin())) {
                check = false;
            }
        }
        if (check) {
            new Thread((Client) client).start();
            clients.add(client);
            client.sendMensage(new Mensage(Codes.LOGIN_SUCCESS, client.getLogin(), null));
            this.updateClientList();
        } else {
            Mensage loginInUse = new Mensage(Codes.ERROR_LOGIN_IN_USE, client.getLogin(), null);
            client.sendMensage(loginInUse);
            this.disconnet(client);
        }
    }

    @Override
    public void disconnet(AbstractClient client) {
        try {
            client.disconnect();
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_DISCONNECT + " from: " + client.getLogin() + ", ip: " + client.getIP());
        }
    }

    @Override
    public void updateClientList() {
        StringBuilder clients = new StringBuilder();
        for (Object a : this.clients) {
            AbstractClient client = (Client) a;
            clients.append(client.getLogin()).append('\n');
        }
        Mensage msg = new Mensage(Codes.UPDATE_LIST, null, clients.toString());
        this.sendMensage(msg);
    }

    @Override
    public void close() {
        if (!clients.isEmpty()) {
            for (Object a : clients) {
                AbstractClient client = (Client) a;
                this.disconnet(client);
            }
        }
    }
}

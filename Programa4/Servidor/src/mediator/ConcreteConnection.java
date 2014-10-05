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
import thread.Find;

/**
 *
 * @author lucas
 */
public class ConcreteConnection implements Connection {

    private final static ConcreteConnection instance = new ConcreteConnection();

    private ConcreteConnection() {
        clients = new Vector();
    }

    /**
     * Singleton instance.
     *
     * @return the instance
     */
    public static ConcreteConnection getInstance() {
        return instance;
    }

    private Vector clients;

    /**
     * Output the list with the connected clients.
     */
    public void clientsList() {
        if(!clients.isEmpty())
            for (Object client : clients) {
                Client a = (Client) client;
                System.out.println(a.getLogin());
            }
        else
            System.out.println("");
    }

    /**
     * Check if the client is connected.
     *
     * @param client the login to check
     * @return the responce if it is connected
     */
    private boolean exists(String client) {

        for (Object obj : clients) {
            Client a = (Client) obj;
            if (a.getLogin().equals(client));
                return true;
        }
        return false;
    }

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
        if (client.getLogin().equals("Servidor")) {
            check = false;
        }
        for (Object a : clients) {
            AbstractClient clientConnected = (Client) a;
            if (client.getLogin().equals(clientConnected.getLogin())) {
                check = false;
                break;
            }
        }
        if (check) {
            new Thread((Client) client, client.getLogin()).start();

            StringBuilder clients = new StringBuilder();
            for (Object a : this.clients) {
                AbstractClient clienT = (Client) a;
                clients.append(clienT.getLogin()).append('\n');
            }

            client.sendMensage(new Mensage(Codes.LOGIN_SUCCESS, client.getLogin(), clients.toString()));

            this.sendMensage(new Mensage(Codes.LOGIN, client.getLogin(), null));

            this.clients.add(client);

            System.out.println("Client " + client.getLogin() + " with IP: " + client.getIP() + " just logged in");
        } else {
            client.sendMensage(new Mensage(Codes.ERROR_LOGIN_IN_USE, client.getLogin(), null));
            System.out.println("Name: " + client.getLogin() + " already in use, from IP: " + client.getIP());
            try {
                client.disconnect();
            } catch (Exception e) {
                System.err.println("Error code: " + Codes.ERROR_DISCONNECT + " from duplicate: " + client.getLogin() + ", ip: " + client.getIP());
            }

        }
    }

    @Override
    public void disconnet(AbstractClient client) {
        try {
            client.disconnect();
            clients.remove(client);
            this.sendMensage(new Mensage(Codes.DISCONNECT, client.getLogin(), null));
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_DISCONNECT + " from: " + client.getLogin() + ", ip: " + client.getIP());
        }
        System.out.println("Client: " + client.getLogin() + " with IP:" + client.getIP() + " just disconnected");
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
                try {
                    this.sendMensage(new Mensage(Codes.DISCONNECT, client.getLogin(), null));
                    Find.thread(client.getLogin()).stop();
                    client.disconnect();
                } catch (Exception e) {
                    System.err.println("Error code: " + Codes.ERROR_DISCONNECT + " from: " + client.getLogin() + ", ip: " + client.getIP());
                }
                System.out.println("Client: " + client.getLogin() + " with IP:" + client.getIP() + " just disconnected");
            }
        }
    }

    @Override
    public void pop(AbstractClient client) {
        try {
            client.pop();
            clients.remove(client);
            this.sendMensage(new Mensage(Codes.DISCONNECT, client.getLogin(), null));
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_DISCONNECT + " from: " + client.getLogin() + ", ip: " + client.getIP());
        }
        System.out.println("Client: " + client.getLogin() + " with IP:" + client.getIP() + " just disconnected");
    }

    /**
     * Tries to kick a client from the server.
     * @param client the client's login to be kicked
     * @return true if the client exists and was kicked and false if it does not exist;
     */
    public boolean pop(String client) {
        if (this.exists(client)) {
            for (Object a : clients) {
                Client cli = (Client) a;
                if (cli.getLogin().equals(client)) {
                    this.pop(cli);
                    break;
                }
            }
            return true;
        }
        return false;
    }
}

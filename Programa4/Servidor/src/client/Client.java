/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import msg.Codes;
import msg.Mensage;

/**
 *
 * @author lucas
 */
public final class Client extends AbstractClient implements Runnable {

    private ObjectOutputStream sendMensage;

    public Client(Socket socket, String login) {
        super(socket, login);
        try {
            sendMensage = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_CREATE_OUTPUTSTREAM + " from: " + this.getLogin() + ", ip: " + this.getIP());
            sendMensage = null;
        }
    }


    @Override
    public void run() {
        ObjectInputStream receive;

        try {
            receive = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_CREATE_INPUTSTREAM + "from: " + this.getLogin() + ", ip: " + this.getIP());
            mediator.disconnet(this);
            return;
        }

        while (true) {
            Object msg = null;
            try {
                msg = receive.readObject();
            } catch (Exception e) {
                System.err.println("Error code: " + Codes.ERROR_RECEIVE_MSG + this.getLogin() + ", ip: " + this.getIP());
                mediator.disconnet(this);
                return;
            }

            mediator.receiveMsg(msg);
        }

    }

    @Override
    public void sendMensage(Mensage msg) {
        try {
            sendMensage.writeObject(msg);
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_SEND_MSG + " from " + this.getLogin() + ", ip: " + this.getIP());
        }
    }

    @Override
    public synchronized boolean checkOutputStream() {
        return (sendMensage != null);
    }
}

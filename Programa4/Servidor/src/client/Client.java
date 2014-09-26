/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
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
    private final ObjectInputStream receive;

    public Client(Socket socket, String login, ObjectInputStream receive) {
        super(socket, login);
        this.receive = receive;
        try {
            sendMensage = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
            System.err.println("Error code: " + Codes.ERROR_CREATE_OUTPUTSTREAM + " from: " + this.getLogin() + ", ip: " + this.getIP());
            sendMensage = null;
        }
    }

    @Override
    public void run() {
        while (true) {
            Object msg = null;
            try {
                msg = receive.readObject();

                //System.out.println("Lido algo " + a.getCode());
            } catch (Exception e) {
                System.err.println("Error code: " + Codes.ERROR_RECEIVE_MSG + " from: " + this.getLogin() + ", ip: " + this.getIP());
                mediator.pop(this);
                return;
            }

            mediator.receiveMsg(msg);
            
            Mensage a = (Mensage) msg;
            if (a.getCode() == Codes.DISCONNECT) {
                return;
            }
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
    public boolean checkOutputStream() {
        return (sendMensage != null);
    }

    @Override
    public void disconnect() throws IOException {

        this.sendMensage(new Mensage(Codes.DISCONNECT_SUCCESS, this.getLogin(), null));
        this.clientSocket.close();
    }
    
}

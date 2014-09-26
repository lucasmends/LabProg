/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import client.Client;
import client.AbstractClient;
import java.io.ObjectInputStream;
import java.net.Socket;
import mediator.ConcreteConnection;
import mediator.Connection;
import msg.Codes;
import msg.Mensage;

/**
 *
 * @author lucas
 */
public class InterpreteMsgConcrete implements InterpreteMsg{

    private final Connection mediator;
    
    public InterpreteMsgConcrete(){
        this.mediator = ConcreteConnection.getInstance();
    }
    
    @Override
    public synchronized void parseMsg(Object msg, Socket socket, ObjectInputStream receive) {
        if(msg instanceof Mensage){
            Mensage mensage = (Mensage) msg;
            AbstractClient client = new Client(socket, mensage.getLogin(), receive);
            
            switch(mensage.getCode()){
                case Codes.LOGIN:                   
                    mediator.addClient(client);
                    break;
                case Codes.DISCONNECT:
                    mediator.disconnet(client);
                    break;
            }
        }
    }
    
}

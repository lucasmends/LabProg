/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import connection.ConnectionListener;
import java.net.ServerSocket;
import java.util.Scanner;
import mediator.ConcreteConnection;


/**
 *
 * @author lucas
 */
public class Server  {

    private ServerSocket socket;
    

    /**
     * Constructor using a defined port.
     *
     * @param port port to be used
     */
    public Server(int port) {
        try {
            socket = new ServerSocket(port);
        } catch (Exception ex) {
            System.out.println("Error creating socket in port " + port);
        }

        new Thread(new ConnectionListener(socket)).start();
        
        System.out.println("Digite quit para sair");
        Scanner in = new Scanner(System.in);
        while(!in.nextLine().equals("quit")){
            
        }
        
        ConcreteConnection.getInstance().close();
        try{
            socket.close();
        }catch(Exception e){
            System.err.println("Error when closing the server");
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mediator.ConcreteConnection;
import msg.Codes;
import msg.Mensage;

/**
 *
 * @author lucas
 */
public class Commands {
  
    private List<String> commands;
    private Scanner in;
    
    public Commands(){
        
        in = new Scanner(System.in);
        
        commands = new ArrayList<>();
        
        commands.add("\\quit");
        commands.add("\\msg");
        commands.add("\\list");
        commands.add("\\kick");
    }
    
    /**
     * The loop for the server's terminal.
     */
    public void terminal(){
        System.out.println("Digite \\help para a lista de comandos");
        String mensagem = new String();
        while(!mensagem.equals("\\quit")){
            switch (mensagem) {
                case "\\h":
                case "\\help":
                    this.help();
                    break;
                case "\\q":
                case "\\quit":
                    return;
                case "\\msg":
                    this.msg();
                    break;
                case "\\list":
                    this.list();
                    break;
                case "\\kick":
                    this.kick();
                    break;
            }
            System.out.print(":");
            mensagem = in.nextLine();
        }
    }

    /**
     * Help mensage.
     */
    private void help(){
        System.out.println("Comandos:");
        for(String command: commands)
            System.out.println(command);
        
    }

    /**
     * Send a mensage for everyone.
     */
    private void msg(){
        System.out.print("Mensagem: ");
        String mensagem = in.nextLine();
        ConcreteConnection.getInstance().sendMensage(new Mensage(Codes.MENSAGE_ALL, "Servidor", mensagem));
    }
    
    /**
     * List the connected users.
     */
    private void list(){
        System.out.println("Usuários conectados:");
        ConcreteConnection.getInstance().clientsList();
    }
    
    /**
     * Kick a client.
     */
    private void kick(){
        System.out.println("Nome do cliente:");
        String client = in.nextLine();
        ConcreteConnection mediator = ConcreteConnection.getInstance();
        
        if(!mediator.pop(client)){
            System.out.println("Cliente não existe.");
        }
    }
}

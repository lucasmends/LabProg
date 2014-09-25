/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import java.net.Socket;

/**
 *
 * @author lucas
 */
public interface InterpreteMsg {
    
    /**
     * Parser of the Object received
     * @param msg the object received
     * @param socket the socket where the Object came from
     */
    public void parseMsg(Object msg, Socket socket);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg;

/**
 *
 * @author lucas
 */
public class Codes {

    /**
     * Code for a mensage for all in the chat.
     */
    public static final int MENSAGE_ALL = 1;
    /**
     * Code for update the clients connected.
     */
    public static final int UPDATE_LIST = 2;
    /**
     * Code for a login mensage.
     */
    public static final int LOGIN = 3;
    /**
     * Code for disconnect.
     */
    public static final int DISCONNECT = 4;
    /**
     * Code for login success.
     */
    public static final int LOGIN_SUCCESS = 5;
    /**
     * Code for disconnect success.
     */
    public static final int DISCONNECT_SUCCESS = 6;
    /**
     * Error code when sending a mensage.
     */
    public static final int ERROR_SEND_MSG = -1;
    /**
     * Error code when creating the outputstream from a socket.
     */
    public static final int ERROR_CREATE_OUTPUTSTREAM = -2;
    /**
     * Error code when creating the inputstream from a socket.
     */
    public static final int ERROR_CREATE_INPUTSTREAM = -3;
    /**
     * Error code when receiving a mensage.
     */
    public static final int ERROR_RECEIVE_MSG = -4;
    /**
     * Error code when doing the login.
     */
    public static final int ERROR_LOGIN = -5;
    /**
     * Error, login already in use.
     */
    public static final int ERROR_LOGIN_IN_USE = -6;
    /**
     * Error in disconnecting.
     */
    public static final int ERROR_DISCONNECT = -7;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msg;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lucas
 */
public class Mensage implements Serializable{
    private final int code;
    private final String mensage;
    private final String login;
    private final String data;
    
    /**
     * Constructor
     * @param code the mensage's code
     * @param login the username
     * @param mensage the mensage
     */
    public Mensage(int code, String login, String mensage){
        this.code = code;
        this.login = login;
        this.mensage = mensage;
        this.data = String.format("%td/%<tm/%<tY as %<tT", new Date());
    }

    /**
     * @return the mensage
     */
    public String getMensage() {
        return mensage;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }
    
    
}

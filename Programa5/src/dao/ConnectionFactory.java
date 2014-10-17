/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucas
 */
public class ConnectionFactory {
    
    private String base;
    private String user;
    private String password;
    private String server;
    private String tcpport;
    
    public ConnectionFactory(){
        this.setServer("localhost");
        this.setTcpport("3306");
        this.setBase("dao");
        this.setUser("labprog");
        this.setPassword("labprog");
    }

    
    public Connection connection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        return (Connection) DriverManager.getConnection("jdbx:mysql://"+ getServer() + ":" + getTcpport() + 
                                                        "/" + getBase(), getUser(), getPassword());
    }
    
    /**
     * @return the base
     */
    public String getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * @return the tcpport
     */
    public String getTcpport() {
        return tcpport;
    }

    /**
     * @param tcpport the tcpport to set
     */
    public void setTcpport(String tcpport) {
        this.tcpport = tcpport;
    }
    
}

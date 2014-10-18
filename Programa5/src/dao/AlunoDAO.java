/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;

/**
 *
 * @author lucas
 */
public class AlunoDAO {
    
    private ConnectionFactory factory = new ConnectionFactory();
    
    public boolean insert(Aluno aluno) throws SQLException, ClassNotFoundException{
        Connection conn = factory.connection();
        boolean state = true;
                
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate("INSERT INTO Alunos VALUES ('"+ aluno.getPessoa() + "', "+ aluno.getCodigo().toString() +")");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            state = false;
        }finally{
            conn.close();
        }
        return state;
    }
    
    public boolean update(Aluno aluno) throws ClassNotFoundException, SQLException{
        Connection conn = factory.connection();
        boolean state = true;
                
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate("UPDATE Alunos SET pessoa='" + aluno.getPessoa() + 
                                             "'WHERE codigo=" + aluno.getCodigo().toString() + ";");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            state = false;
        }finally{
            conn.close();
        }
        return state;        
    }
    
    public boolean delete(Aluno aluno) throws SQLException, ClassNotFoundException{
        Connection conn = factory.connection();
        boolean state = true;
                
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate("DELETE FROM Alunos WHERE codigo=" + aluno.getCodigo().toString() + 
                                            " AND pessoa='" + aluno.getPessoa() + "';");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            state = false;
        }finally{
            conn.close();
        }
        return state;          
    }
    
    public Aluno findByCodigo( Integer codigo ) throws ClassNotFoundException, SQLException{
        Connection conn = factory.connection();
        
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos WHERE codigo=" + codigo.toString() + ";");
        if(rs.first()){
            return new Aluno(rs.getString("pessoa"), rs.getInt("codigo"));
        }
        return null;
    }
    
    public List<Aluno> listAll() throws ClassNotFoundException, SQLException {
        ArrayList<Aluno> lista = new ArrayList<>();
        
        Connection conn = factory.connection();
        Statement stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM Alunos;");
        
        while(rs.next()){
            lista.add(new Aluno(rs.getString("pessoa"), rs.getInt("codigo")));
        }
        
        conn.close();
        
        return lista;
    }
}

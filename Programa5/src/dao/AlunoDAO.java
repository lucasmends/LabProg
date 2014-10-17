/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
    
    public static List<Aluno> listar() throws ClassNotFoundException, SQLException {
        ArrayList<Aluno> lista = new ArrayList<>();
        ConnectionFactory factory = new ConnectionFactory();
        Statement stmt = factory.connection().createStatement();
        
        ResultSet rs = stmt.executeQuery("SELECT * FROM Aluno;");
        
        while(rs.next()){
            lista.add(new Aluno(rs.getString("pessoa"), rs.getInt("codigo")));
        }
        
        return lista;
    }
}

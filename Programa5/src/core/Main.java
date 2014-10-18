/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import dao.AlunoDAO;
import java.sql.SQLException;
import java.util.List;
import model.Aluno;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      
        
        AlunoDAO dao = new AlunoDAO();
                
        imprimirLista(dao.listAll());
        System.out.println("");
        
        dao.insert(new Aluno("alberto", 12001));
        imprimirLista(dao.listAll());
        System.out.println("");
        
        dao.insert(new Aluno("carlos", 12002));
        dao.insert(new Aluno("caio", 12003));
        dao.insert(new Aluno("ramide", 12004));
        imprimirLista(dao.listAll());
        System.out.println("");
        
        imprimir(dao.findByCodigo(12003));
        System.out.println("");
        
        imprimir(dao.findByCodigo(12009));
        System.out.println("");
        
        dao.delete(new Aluno("caio", 12003));
        imprimir(dao.findByCodigo(12003));
        System.out.println("");
        
        dao.update(new Aluno("edimar", 12004));
        imprimir(dao.findByCodigo(12004));
        System.out.println("");
        
        imprimirLista(dao.listAll());
        
        
    }
    
    
    private static void imprimirLista(List<Aluno> lista){
        for(Aluno aluno: lista){
            imprimir(aluno);
        }
    }
    
    private static void imprimir(Aluno aluno){
        if(aluno != null)
            System.out.println("Aluno, nome: " + aluno.getPessoa() +", codigo: " +aluno.getCodigo().toString());
    }
}

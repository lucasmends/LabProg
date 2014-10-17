/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lucas
 */
public class Aluno {
    
    private String pessoa;
    private Integer codigo;
    
    public Aluno(){
        
    }

    public Aluno(String pessoa, Integer codigo){
        this.setPessoa(pessoa);
        this.setCodigo(codigo);
    }    
    
    /**
     * @return the pessoa
     */
    public String getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    
}

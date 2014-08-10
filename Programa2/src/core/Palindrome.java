/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

/**
 *
 * @author Lucas
 */
public abstract class Palindrome {
    /**
     * Método de checagem a ser implentado nas classes filhas.
     * @param frase frase a ser verificada 
     */
    public abstract void palindrome(String frase);
    
    
    /**
     * Imprimi se a frase é palindromo ou não.
     * @param frase frase a ser imprimida na tela
     * @param palindromo informando se é palindromo
     */
    protected void imprimir(String frase, boolean palindromo){
        if(palindromo)
            System.out.println(frase + " é palindrome");
        else
            System.out.println(frase + " não é palindrome");
    }
}

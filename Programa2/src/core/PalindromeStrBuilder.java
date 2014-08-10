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
public class PalindromeStrBuilder extends Palindrome{

    @Override
    public void palindrome(String frase) {
        StringBuilder reverso = new StringBuilder(frase.trim()).reverse();
        this.imprimir(frase, frase.trim().equals(reverso.toString()));
    }
   
}

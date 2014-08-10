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
public class PalindromeStrIt extends Palindrome{

    @Override
    public void palindrome(String frase) {
        String verificar = frase.trim();
        int j = verificar.length() - 1;
       for(int i = 0; i < (verificar.length()/2); i++, j--){
           if(verificar.charAt(i) != verificar.charAt(j))
               break;
       }
        imprimir(frase, (j <= 0) );
    }

    
}

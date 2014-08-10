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
public class PalindromeStrRec extends Palindrome{

    @Override
    public void palindrome(String frase) {
        imprimir(frase, recursao(frase.trim()));
    }
    
    /**
     * Metódo que usa recursão.
     * @param frase verifica se a frase
     * @return retorna verdade se é palindromo e falso se não
     */
    private boolean recursao(String frase){
        int n = frase.length() - 1;
        if(n <= 0)
            return true;
        if(frase.charAt(0) == frase.charAt(n))
            return recursao(frase.substring(1, n - 1));
        return false;
    }
}

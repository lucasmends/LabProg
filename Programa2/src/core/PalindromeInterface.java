/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class PalindromeInterface {

    private Palindrome checar;
    private final Map<String, Class<?>> lista = new HashMap<>();
    private String frase;

    /**
     * Construtor default que já contém três métodos de checagem de palindromos.
     */
    public PalindromeInterface() {
        checar = null;
        lista.put("StringBuilder", PalindromeStrBuilder.class);
        lista.put("String Iterativo", PalindromeStrIt.class);
        lista.put("String Recursivo", PalindromeStrRec.class);
        frase = new String();
    }

    /**
     * Setter da frase.
     *
     * @param frase A frase a ser checada
     */
    public void setFrase(String frase) {
        this.frase = frase;
    }

    /**
     * Pergunta au usuário qual modo de checar ele deseja.
     * @return Retorna o próprio objeto para fazer chamada em cascada
     */
    public PalindromeInterface escolherMetodo() {
        int i = 1;
        System.out.println("Qual método você deseja usar?");

        for (String tipo : lista.keySet()) {
            System.out.println(i++ + ": " + tipo);
        }
        i = new Scanner(System.in).nextInt();
        Iterator iterar = lista.entrySet().iterator();
        Map.Entry<String, Class<?>> chave = (Map.Entry<String, Class<?>>) iterar.next();
        for (int j = 1; j < i; j++) {
            chave = (Map.Entry<String, Class<?>>) iterar.next();
        }

        try {
            checar = (Palindrome) chave.getValue().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            checar = null;
            System.err.println(ex.getMessage());
        }
        return this;
    }
    
    /**
     * Mostra se a frase setada é palindromo, se não foi colocada nenhuma frase e não foi escolhido nenhum método de checagem esta função não faz nada.
     */
    public void mostra(){
        if(checar != null && frase != ""){
            checar.palindrome(frase);
        }
    }

}

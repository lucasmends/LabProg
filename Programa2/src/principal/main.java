package principal;

import core.PalindromeInterface;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lucas
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        PalindromeInterface palindro = new PalindromeInterface();

        Scanner in = new Scanner(System.in);
        System.out.println("Digite a frase que você deseja verificar se é palindrome");
        palindro.setFrase(in.nextLine());
        palindro.escolherMetodo().mostra();
        
    }

}

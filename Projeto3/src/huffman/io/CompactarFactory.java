/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman.io;

import java.io.File;

/**
 *
 * @author lucas
 */
public class CompactarFactory {
    
    /**
     * Cria uma instância do MendesCompactar.
     * 
     * @param arquivo arquivo a ser compactado
     * @param n número de blocos
     * @return o arquivo compactado
     */
    public static Compactar criarCompactador(File arquivo, int n){
        return new MendesCompactar(arquivo, n);
    }
    
}

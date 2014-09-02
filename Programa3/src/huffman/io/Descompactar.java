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
public interface Descompactar {
    
    /**
     * Função para descompactar o arquivo.
     * 
     * @param arquivo arquivo a ser descompactado
     * @return o arquivo salvo
     */
    public File descompactar(File arquivo);
}

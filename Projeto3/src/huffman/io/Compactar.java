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
public interface Compactar {
    
    /**
     * Compactar usando o huffman.
     *
     * @return retorna o arquivo salvo
     */    
    public File compactar();

    /**
     * Compactar usando o huffman.
     *
     * @param nome o nome do arquivo de destino
     * @return retorna o arquivo salvo
     */    
    public File compactar(String nome);
    
}

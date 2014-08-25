/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman.lista;

import huffman.tree.TreeAbstract;

/**
 *
 * @author lucas
 */
public interface ListaFreq {
    
    
    /**
     * Retira o menor elemento da lista de frequencia. 
     * @return retorna o elemento
     */
    public TreeAbstract retiraMenor();
    
    /**
     * Inserir um elemento na lista
     * @param elemento insere o elemento
     */
    public void inserir(TreeAbstract elemento);
    
    public int tamanho();
}

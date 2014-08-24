/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman.tree;

/**
 *
 * @author lucas
 */
public class TreeLeaf extends TreeAbstract{
    
    private final byte[] valor;

    /**
     * Para criar a folha da árvore de huffman.
     * @param valor O valor da folha
     */
    public TreeLeaf(byte[] valor) {
        super(null, null);
        this.valor = valor;
    }
    
    /**
     * Pegar os bytes originais da folha
     * @return retorna os bytes da folha
     */
    public byte[] getValue(){
        return this.valor;
    }      

}

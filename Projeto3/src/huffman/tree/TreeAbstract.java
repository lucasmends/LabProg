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
public abstract class TreeAbstract implements Comparable<TreeAbstract>{
    private final TreeAbstract left;
    private final TreeAbstract right;
    protected int peso;
    
    /**
     * Cria a árvore de huffman.
     * @param left O filho a esquerda
     * @param right O filho a direita
     */
    public TreeAbstract(TreeAbstract left, TreeAbstract right){
        this.left = left;
        this.right = right;
    }
    
    /**
     * Altera o peso do nó.
     * @param peso o valor do peso
     */
    public void setPeso(int peso){
        this.peso = peso;
    }
    
    /**
     * Pega o peso.
     * @return retorna o peso do nó
     */
    public int peso(){
        return this.peso;
    }

    /**
     * @return the left
     */
    public TreeAbstract getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public TreeAbstract getRight() {
        return right;
    }

    @Override
    public int compareTo(TreeAbstract o) { 
        if(peso > o.peso )
           return 1; 
        if(peso < o.peso)
            return -1;
        return 0;
    }
}

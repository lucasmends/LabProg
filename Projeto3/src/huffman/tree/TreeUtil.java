/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.tree;

import java.util.Arrays;

/**
 *
 * @author lucas
 */
public class TreeUtil {

    /**
     * Para pegar o byte correspondente na árvore.
     *
     * @param arvore
     * @param valor
     * @return a lista com os bytes da árvore de huffman
     */
    public static String pegarNivel(TreeAbstract arvore, byte[] valor) {
        //String nivel;
        TreeAbstract aux = arvore;
        
        return encontrar(aux, new StringBuilder(), valor).toString();

    }
    
    /**
     * Método para encontrar elemento na árvore.
     * @param a a árvore
     * @param ini o valor da resposta
     * @param valor o valor a ser encontrado
     * @return  o nível do valor
     */
    private static StringBuilder encontrar(TreeAbstract a, StringBuilder ini, byte[] valor){
        if(a != null){
            if( a instanceof TreeLeaf){
                TreeLeaf b = (TreeLeaf) a;
                if( Arrays.equals(b.getValue(), valor)){
                    return ini;
                }
            }
            StringBuilder res = new StringBuilder(ini).append("1");
            StringBuilder resposta = encontrar(a.getLeft(), res, valor);
            if(resposta == null){
                res = new StringBuilder(ini).append("0");                
                resposta = encontrar(a.getRight(), res, valor);
            }
            return resposta;
        }
        return null;
    }
}

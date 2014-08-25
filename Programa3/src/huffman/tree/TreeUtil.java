/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.tree;

import huffman.lista.ListaDeFrequencia;
import huffman.lista.ListaFreq;
import java.io.File;
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
    
    /**
     * Função para montar a árvore de Huffman
     *
     * @param arquivo arquivo para se montar a árvore
     * @param n o número de blocos
     * 
     * @return retorna a árvore de Huffman
     */
    public static TreeAbstract montarArvore(File arquivo, int n) {
        //montando a árvore
        //pegando a lista de frequencia
        ListaFreq lista = new ListaDeFrequencia(n, arquivo);
        TreeAbstract arvore = null;
        //algoritmo para montar a árvore
        while (lista.tamanho() >= 2) {
            TreeAbstract x = lista.retiraMenor();
            TreeAbstract y = lista.retiraMenor();
            arvore = new TreeNode(x, y);
            arvore.setPeso(x.peso() + y.peso());
            lista.inserir(arvore);
        }
        return arvore;
    }    
}

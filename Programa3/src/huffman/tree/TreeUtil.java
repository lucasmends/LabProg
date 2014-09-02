/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.tree;

import huffman.lista.ListaDeFrequencia;
import huffman.lista.ListaFreq;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public static String pegarNivel(TreeAbstract arvore, Character valor) {
        //String nivel;
        TreeAbstract aux = arvore;

        return encontrar(aux, new StringBuilder(), valor).toString();

    }

    /**
     * Método para encontrar elemento na árvore.
     *
     * @param arvore a árvore
     * @param repostaSTR o valor da resposta
     * @param valor o valor a ser encontrado
     * @return o nível do valor
     */
    private static StringBuilder encontrar(TreeAbstract arvore, StringBuilder repostaSTR, Character valor) {
        if (arvore != null) {
            if (arvore instanceof TreeLeaf) {
                TreeLeaf b = (TreeLeaf) arvore;
                if ( b.getValue().equals(valor) ) {
                    return repostaSTR;
                }
            }
            StringBuilder res = new StringBuilder(repostaSTR).append("1");
            StringBuilder resposta = encontrar(arvore.getLeft(), res, valor);
            if (resposta == null) {
                res = new StringBuilder(repostaSTR).append("0");
                resposta = encontrar(arvore.getRight(), res, valor);
            }
            return resposta;
        }
        return null;
    }


    /**
     * Função para montar a árvore de Huffman.
     *
     * @param arquivo bytes do arquivo
     *
     * @return retorna a árvore de Huffman
     * @throws java.io.IOException
     */    
    public static TreeAbstract montarArvore(File arquivo) throws IOException{
        ListaFreq lista = new ListaDeFrequencia(arquivo);
        return montarArvore(lista);
    }
    
    /**
     * Função para montar a árvore de Huffman.
     * 
     * @param lista lista de frequência
     * @return 
     */
    public static TreeAbstract montarArvore(ListaFreq lista){
        TreeAbstract arvore;
         while (lista.tamanho() > 1) {
            TreeAbstract x = lista.retiraMenor();
            TreeAbstract y = lista.retiraMenor();
            arvore = new TreeNode(x, y);
            arvore.setPeso(x.peso() + y.peso());
            lista.inserir(arvore);
        }
        return lista.retiraMenor();         
    }
    
    /**
     * Função para mapear todos os elementos da Árvore de Huffman em um Map
     * @param arvore a árvore a ser mapeada
     * @return o Map montado
     */
    public static Map<Character, String> mapearArvore(TreeAbstract arvore) {
        Map<Character, String> resposta = new HashMap<>();
        percorrerArvore(resposta, "", arvore);
        return resposta;
    }
    
    
    /**
     * Função usada para mapear a árvore de Huffman.
     * @param mapa o Map a ser montado
     * @param valor o valor atual da folha
     * @param arvore a árvore a ser mapeada
     */
    
    private static void percorrerArvore(Map<Character, String> mapa, String valor, TreeAbstract arvore){
        
        if(arvore != null){
            if(arvore instanceof TreeLeaf){
                TreeLeaf folha = (TreeLeaf) arvore;
                mapa.put(folha.getValue(), valor);
            }
            else{
                percorrerArvore(mapa, valor.concat("1"), arvore.getLeft());
                percorrerArvore(mapa, valor.concat("0"), arvore.getRight());
            }
        }
    }
   
}

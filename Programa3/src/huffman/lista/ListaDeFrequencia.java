/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.lista;

import huffman.tree.TreeAbstract;
import huffman.tree.TreeLeaf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author lucas
 */
public class ListaDeFrequencia implements ListaFreq {

    private final PriorityQueue<TreeAbstract> lista;

    /**
     * Cosntrutor.
     *
     * @param file o arquivo a ser usado.
     */
    public ListaDeFrequencia(File file) throws IOException {
        lista = new PriorityQueue<>();
        criar(file);
    }

 
    /**
     * Construtor.
     */
    public ListaDeFrequencia(){
        lista = new PriorityQueue<>();
    }
    
    /**
     * Método para criar um map contendo os blocos e suas frequências onde será
     * usada para monta a lista de frequência
     *
     * @param bytesArquivo array de bytes do arquivo
     */
    private void criar(File arquivo) throws FileNotFoundException, IOException {
        Map<Character, Integer> list = new HashMap<>();
        BufferedReader in = new BufferedReader(new FileReader(arquivo));
        
        int atual = in.read();
        
        while(atual != -1){
            Character chave = (char) atual;
            if(list.containsKey(chave)){
                list.put(chave, list.get(chave) + 1);
            }else{
                list.put(chave, 1);
            }
            atual = in.read();
        }
        

        montarLista(list);
    }

    /**
     * Método chamado no criar para montar a lista.
     *
     * @param list A lista a ser usada
     */
    private void montarLista(Map<Character, Integer> list) {
        for (Map.Entry<Character, Integer> entry : list.entrySet()) {
            TreeAbstract folha = new TreeLeaf(entry.getKey());
            folha.setPeso(entry.getValue());
            lista.add(folha);
        }
    }

    @Override
    public TreeAbstract retiraMenor() {
        return lista.poll();
    }

    @Override
    public void inserir(TreeAbstract elemento) {
        lista.add(elemento);
    }

    @Override
    public int tamanho() {
        return lista.size();
    }
}

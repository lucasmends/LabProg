/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.lista;

import huffman.tree.TreeAbstract;
import huffman.tree.TreeLeaf;
import java.io.File;
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
    final int n;

    /**
     * Cosntrutor.
     * @param n o buffer de bytes.
     * @param file o arquivo a ser usado.
     */
    public ListaDeFrequencia(int n, File file) {
        this.n = n;
        lista = new PriorityQueue<>();
        criar(file);
    }

    /**
     * Construtor.
     * @param n o buffer de bytes.
     * @param byteArray  o array a ser usado. 
     */
    public ListaDeFrequencia(int n, byte[] byteArray) {
        this.n = n;
        lista = new PriorityQueue<>();
        criar(byteArray);
    }    
    
    
    /**
     * Método para criar um map contendo os blocos e suas frequências onde será
     * usada para monta a lista de frequência
     * @param bytesArquivo array de bytes do arquivo
     */
    private void criar(byte[] bytesArquivo){
        Map<byte[], Integer> list = new HashMap<>();        
            
            for (int i = 0; i + n < bytesArquivo.length; i = i + n) {
                byte[] aux = new byte[n];
                System.arraycopy(bytesArquivo, i, aux, 0, this.n);
                if (list.containsKey(aux)) {
                    list.put(aux, list.get(aux) + 1);
                } else {
                    list.put(aux, 1);
                }
            }           
            montarLista(list);
    }
    
    /**
     * Método chamado no main para criar a lista a partir de um arquivo.
     * @param file arquivo para se pegar os bytes
     */
    private void criar(File file) {
        try {
            byte[] arquivo = Files.readAllBytes(file.toPath());
            criar(arquivo);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Método chamado no criar para montar a lista.
     * @param list A lista a ser usada
     */
    private void montarLista(Map<byte[], Integer> list){
        for(Map.Entry<byte[], Integer> entry : list.entrySet()){
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

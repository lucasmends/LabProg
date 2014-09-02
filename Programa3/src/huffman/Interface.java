/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.io.File;
import huffman.io.Compactar;
import huffman.io.CompactarFactory;

/**
 *
 * @author lucas
 */
public class Interface {

    private final File arquivo;
    private final boolean janela;
    
    /**
     * Construtor que calcula o bloco autamaticamente.
     *
     * @param arquivo arquivo a ser compactado
     * @param janela se deseja a janela de progresso
     */
    public Interface(File arquivo, boolean janela) {
        this.arquivo = arquivo;
        this.janela = janela;
    }


    /**
     * Compactar usando o huffman.
     *
     * @param nome o nome do arquivo de destino
     * @return retorna o arquivo salvo
     */
    public File compactar(String nome) {
        Compactar compactador = CompactarFactory.criarCompactador2(arquivo, janela);
        return compactador.compactar(nome);
    }

    /**
     * Compactar usando o huffman, sem um nome definido para o arquivo
     * compactado.
     *
     * @return retorna o arquivo salvo
     */
    public File compactar() {
        return compactar(new StringBuilder(arquivo.getAbsolutePath()).append("0").toString());
    }

}

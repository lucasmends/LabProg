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
    private final int n;

    /**
     * Construtor que calcula o bloco autamaticamente.
     *
     * @param arquivo arquivo a ser compactado
     */
    public Interface(File arquivo) {
        this.arquivo = arquivo;

        if (this.arquivo.length() < 204800) {
            if (this.arquivo.length() < 102400) {
                if (this.arquivo.length() < 1024) {
                    this.n = 64;
                } else {
                    this.n = 1024;
                }
            } else {
                this.n = 102400;
            }
        } else {
            this.n = 204800;
        }
    }

    /**
     * Construtor com tamanho do bloco definido.
     *
     * @param arquivo arquivo a ser compactado
     * @param n tamanho do bloco
     */
    public Interface(File arquivo, int n) {
        this.arquivo = arquivo;
        this.n = n;
    }

    /**
     * Compactar usando o huffman.
     *
     * @param nome o nome do arquivo de destino
     * @return retorna o arquivo salvo
     */
    public File compactar(String nome) {
        Compactar compactador = CompactarFactory.criarCompactador(arquivo, n);
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

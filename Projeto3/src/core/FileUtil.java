/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import huffman.lista.ListaDeFrequencia;
import huffman.lista.ListaFreq;
import huffman.tree.TreeAbstract;
import huffman.tree.TreeLeaf;
import huffman.tree.TreeUtil;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author lucas
 */
public class FileUtil {

    /**
     * Escreve a lista de frequência.
     * @param out fluxo para escrever no arquivo
     * @param byteArquivo os bytes do arquivo
     * @param n bloco
     * @throws IOException 
     */
    public static void writeLista(FileOutputStream out, byte[] byteArquivo, int n) throws IOException {
        ListaFreq lista = new ListaDeFrequencia(n, byteArquivo);
        out.write(n);
        out.write(lista.tamanho());
        while (lista.tamanho() > 0) {
            TreeLeaf a = (TreeLeaf) lista.retiraMenor();
            out.write(a.getValue());
        }
    }

    /**
     * Pega o a codificação em bytes do bloco informado e escreve no arquivo.
     * @param out fluxo para escrever no arquivo
     * @param arvore a árvore de huffman do arquivo
     * @param bloco o bloco a ser usado
     * @throws IOException 
     */
    public static void writeTree(FileOutputStream out, TreeAbstract arvore, byte[] bloco) throws IOException {
        StringBuilder bytes = new StringBuilder();
        bytes.append(TreeUtil.pegarNivel(arvore, bloco));
        if (bytes.length() > 7) {
            byte[] resposta = new byte[(bytes.length() / 7) + 1];
            for (int z = 0, x = 0; z < bytes.length(); z = z + 7, x++) {
                byte b;
                if (z + 7 < bytes.length()) {
                    b = Byte.parseByte(bytes.substring(z, z + 7), 2);
                } else {
                    b = Byte.parseByte(bytes.substring(z), 2);
                }
                resposta[x] = b;
            }
            out.write(resposta);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import huffman.lista.ListaDeFrequencia;
import huffman.lista.ListaFreq;
import huffman.tree.TreeAbstract;
import huffman.tree.TreeLeaf;
import huffman.tree.TreeUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author lucas
 */
public class FileUtil {

    /**
     * Escreve a lista de frequência.
     *
     * @param out fluxo para escrever no arquivo
     * @param arquivo arquivo a ser usado
     * @throws IOException
     */
    public static void writeLista(FileOutputStream out, File arquivo) throws IOException {
        ListaFreq lista = new ListaDeFrequencia(arquivo);
        out.write(lista.tamanho());
        while (lista.tamanho() > 0) {
            TreeLeaf a = (TreeLeaf) lista.retiraMenor();
            out.write(a.getValue());
            out.write(a.peso());
        }
    }

    /**
     * Pega o a codificação em bytes do bloco informado e escreve no arquivo.
     *
     * @param out fluxo para escrever no arquivo
     * @param arvore a árvore de huffman do arquivo
     * @param bloco o bloco a ser usado
     * @throws IOException
     */
    public static void writeTree(FileOutputStream out, TreeAbstract arvore, Character bloco) throws IOException {
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

    /**
     * Função que encontra o valor correspondente do bloco no mapa e escreve se
     * for necessário
     *
     * @param out o Stream para o arquivo a ser salvo
     * @param mapa o mapa a ser usado
     * @param bloco o bloco correspondente
     * @param bytes a codificação achada anteriormente
     * @throws IOException
     */
    public static void writeTree(FileOutputStream out, Map<Character, String> mapa, Character bloco, StringBuilder bytes) throws IOException {

        bytes.append(mapa.get(bloco));
        while (bytes.length() > 8) {
            String byteGravar = bytes.substring(0, 8);
            try {
                out.write(converterStringtoByte(byteGravar));
                bytes = new StringBuilder(bytes.substring(8));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Função para converter uma String de 0s e 1s para o byte correspondente.
     *
     * @param bits a String contendo os bits
     * @return retorna a conversão para byte
     * @throws Exception gera exceção se a string for tiver mais de 8 membros ou
     * conter elementos diferentes de 0 ou 1
     */
    public static byte converterStringtoByte(String bits) throws Exception {
        if (bits.length() > 8) {
            throw new Exception("Byte contendo mais de oito bits");
        }
        if (!(bits.contains("0") | bits.contains("1"))) {
            throw new Exception("String contém caracteres diferentes de 0 ou 1");
        }

        byte resposta = 0;
        for (int i = 0; i < bits.length(); i++) {
            if (i != 0) {
                resposta = (byte) (resposta << 1);
            }
            if (bits.charAt(i) == '1') {
                resposta = (byte) (resposta | 1);
            }
        }
        return resposta;
    }

}

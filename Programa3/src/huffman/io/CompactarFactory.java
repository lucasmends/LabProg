/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.io;

import java.io.File;

/**
 *
 * @author lucas
 */
public class CompactarFactory {


    /**
     * Cria uma instância do MendesCompactara.
     *
     * @param arquivo arquivo a ser compactado
     * @param janela se deseja a janela de progresso
     * @return a instancia do compactador
     */
    public static Compactar criarCompactador(File arquivo, boolean janela) {
        return new MendesCompactar(arquivo, janela);
    }
    
    /**
     * Cria uma instância do Mendes2Compactar.
     * @param arquivo arquivo a ser compactado
     * @param janela se deseja a janela
     * @return a instancia do compactador
     */
    public static Compactar criarCompactador2(File arquivo, boolean janela){
        return new Mendes2Compactar(arquivo, janela);
    }
}

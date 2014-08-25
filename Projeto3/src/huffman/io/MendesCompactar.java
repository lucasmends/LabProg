/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.io;

import gui.TempoRestante;
import huffman.FileUtil;
import huffman.tree.TreeAbstract;
import huffman.tree.TreeUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.Timer;

/**
 *
 * @author lucas
 */
public class MendesCompactar implements Compactar {

    private final File arquivo;
    private final int n;   
    
    /**
     * Construtor.
     * 
     * @param arquivo arquivo a ser compactado
     * @param n número de blocos
     */
    public MendesCompactar(File arquivo, int n){
        this.arquivo = arquivo;
        this.n = n;
    }
    
    @Override
    public File compactar() {
        return compactar(new StringBuilder(arquivo.getAbsolutePath()).append("0").toString());
    }

    @Override
    public File compactar(String nome) {
        //montando a árvore
        final TreeAbstract arvore = TreeUtil.montarArvore(arquivo, n);

        final File arquivoCompactado = new File(nome);

        try {
            //escrever a árvore de huffman
            treeHuffmantoWrite(arvore, arquivoCompactado);
            return arquivoCompactado;
        } catch (IOException ex) {
            System.err.println("Erro em gerar o arquivo");
        }
        return null;
    }

    /**
     * Grava o arquivo.
     *
     * @param arvore a árvore a ser usada
     * @param salvar o arquivo a ser salvo
     */
    private void treeHuffmantoWrite(final TreeAbstract arvore, final File salvar) throws IOException {
        //criando o arquivo
        salvar.createNewFile();
        final FileOutputStream out = new FileOutputStream(salvar);
        //todos os bytes do arquivo
        final byte[] byteArquivo = Files.readAllBytes(arquivo.toPath());
        //a janela com o progresso
        final TempoRestante janelaVisual = new TempoRestante(null, true);
        Thread janelaThread = new Thread(janelaVisual);
        janelaThread.start();

        new Thread() {
            @Override
            public void run() {
                int totalB = byteArquivo.length / n;
                try {
                    FileUtil.writeLista(out, byteArquivo, n);
                    for (int i = 0, j = 1; i + n < byteArquivo.length; i = i + n, j++) {
                        byte[] aux = new byte[n];
                        System.arraycopy(byteArquivo, i, aux, 0, n);
                        FileUtil.writeTree(out, arvore, aux);
                        janelaVisual.setProgresso((j * 100) / totalB);
                    }
                } catch (IOException ex) {
                    System.err.println("Erro em gerar o arquivo na conversão de bytes");
                }
                float taxa = ((float) salvar.length() * (float) 100) / (float) arquivo.length();
                janelaVisual.setMensagem("Taxa de compressão: " + taxa + "%");
                Timer timer = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        janelaVisual.setVisible(false);
                        janelaVisual.dispose();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }.start();

    }    
    
}

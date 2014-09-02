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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.Timer;

/**
 *
 * @author lucas
 */
public class MendesCompactar implements Compactar {

    private final File arquivo;
    ;
    private final boolean janela;

    /**
     * Construtor.
     *
     * @param arquivo arquivo a ser compactado
     */
    public MendesCompactar(File arquivo) {
        this.arquivo = arquivo;
        this.janela = true;
    }

    /**
     * Construtor.
     *
     * @param arquivo arquivo a ser compactado
     * @param janela se deseja mostrar a janela de progresso
     */
    public MendesCompactar(File arquivo, boolean janela) {
        this.arquivo = arquivo;
        this.janela = janela;
    }

    @Override
    public File compactar() {
        return compactar(new StringBuilder(arquivo.getAbsolutePath()).append("0").toString());
    }

    @Override
    public File compactar(String nome) {

        final File arquivoCompactado = new File(nome);

        try {
            //montando a árvore
            final TreeAbstract arvore = TreeUtil.montarArvore(arquivo);
            //escrever a árvore de huffman
            if (janela) {
                writeJanela(arvore, arquivoCompactado);
            } else {
                write(arvore, arquivoCompactado);
            }
            return arquivoCompactado;
        } catch (IOException ex) {
            System.err.println("Erro em gerar o arquivo");
        }
        return null;
    }

    /**
     * Grava o arquivo com a janela de progresso.
     *
     * @param arvore a árvore a ser usada
     * @param salvar o arquivo a ser salvo
     * @param byteArquivo os bytes do arquivo usado
     */
    private void writeJanela(final TreeAbstract arvore, final File salvar) throws IOException {
        //criando o arquivo
        salvar.createNewFile();
        final FileOutputStream out = new FileOutputStream(salvar);
        //a janela com o progresso
        final TempoRestante janelaVisual = new TempoRestante(null, true);
        Thread janelaThread = new Thread(janelaVisual);
        janelaThread.start();

        new Thread() {
            @Override
            public void run() {

                int totalB = (int) arquivo.length();
                int j = 0;
                try {
                    BufferedReader in = new BufferedReader(new FileReader(arquivo));
                    int atual = in.read();

                    FileUtil.writeLista(out, arquivo);

                    while (atual != -1) {
                        Character bloco = (char) atual;
                        FileUtil.writeTree(out, arvore, bloco);
                        j += 2;
                        janelaVisual.setProgresso((j * 100) / totalB);
                        atual = in.read();
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

    /**
     * Grava o arquivo sem a janela de progresso.
     *
     * @param arvore a árvore a ser usada
     * @param salvar o arquivo a ser salvo
     * @param byteArquivo os bytes do arquivo usado
     */
    private void write(final TreeAbstract arvore, final File salvar) throws IOException {
        //criando o arquivo
        salvar.createNewFile();
        final FileOutputStream out = new FileOutputStream(salvar);
        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));
            int atual = in.read();

            FileUtil.writeLista(out, arquivo);

            while (atual != -1) {
                Character bloco = (char) atual;
                FileUtil.writeTree(out, arvore, bloco);
                atual = in.read();
            }
        } catch (IOException ex) {
            System.err.println("Erro em gerar o arquivo na conversão de bytes");
        }
        float taxa = ((float) salvar.length() * (float) 100) / (float) arquivo.length();
        System.out.println("Taxa de compressão: " + taxa + "%");
    }

}

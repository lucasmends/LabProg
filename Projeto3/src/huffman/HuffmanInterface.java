/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import core.FileUtil;
import gui.TempoRestante;
import huffman.lista.ListaDeFrequencia;
import huffman.lista.ListaFreq;
import huffman.tree.TreeAbstract;
import huffman.tree.TreeNode;
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
public class HuffmanInterface {

    private final File arquivo;
    private final int n;

    /**
     * Construtor que calcula o bloco autamaticamente.
     *
     * @param arquivo arquivo a ser compactado
     */
    public HuffmanInterface(File arquivo) {
        this.arquivo = arquivo;
        if (this.arquivo.length() < 204800) {
            if (this.arquivo.length() < 102400) {
                this.n = 1024;
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
    public HuffmanInterface(File arquivo, int n) {
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
        //montando a árvore
        final TreeAbstract arvore = montarArvore();

        final File salvar = new File(nome);

        try {
            //escrever a árvore de huffman
            treeHuffmantoWrite(arvore, salvar);
            return salvar;
        } catch (IOException ex) {
            System.err.println("Erro em gerar o arquivo");
        }
        return null;
    }

    /**
     * Compactar usando o huffman.
     *
     * @return retorna o arquivo salvo
     */
    public File compactar() {
        return compactar(new StringBuilder(arquivo.getAbsolutePath()).append("0").toString());
    }

    /**
     * Função para montar a árvore de Huffman
     *
     * @return retorna a árvore de Huffman
     */
    private TreeAbstract montarArvore() {
        //montando a árvore
        //pegando a lista de frequencia
        ListaFreq lista = new ListaDeFrequencia(n, arquivo);
        TreeAbstract arvore = null;
        //algoritmo para montar a árvore
        while (lista.tamanho() >= 2) {
            TreeAbstract x = lista.retiraMenor();
            TreeAbstract y = lista.retiraMenor();
            arvore = new TreeNode(x, y);
            arvore.setPeso(x.peso() + y.peso());
            lista.inserir(arvore);
        }
        return arvore;
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

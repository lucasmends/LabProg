/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman.io;

import gui.TempoRestante;
import huffman.FileUtil;
import static huffman.FileUtil.converterStringtoByte;
import huffman.tree.TreeAbstract;
import huffman.tree.TreeUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import javax.swing.Timer;

/**
 *
 * @author lucas
 */
public class Mendes2Compactar implements Compactar {

    private final File arquivo;
    private final boolean janela;

    /**
     * Construtor.
     *
     * @param arquivo arquivo a ser compactado
     * @param janela se deseja janela
     */
    public Mendes2Compactar(File arquivo, boolean janela) {
        this.arquivo = arquivo;
        this.janela = janela;
    }

    /**
     * Construtor.
     *
     * @param arquivo arquivo a ser salvo
     */
    public Mendes2Compactar(File arquivo) {
        this.arquivo = arquivo;
        this.janela = true;
    }

    @Override
    public File compactar() {
        return compactar(new StringBuilder(arquivo.getAbsolutePath()).append("0").toString());
    }

    @Override
    public File compactar(String nome) {

        final File arquivoCompactado = new File(nome);
        try {
            final TreeAbstract arvore = TreeUtil.montarArvore(arquivo);

            final Map<Character, String> mapa = TreeUtil.mapearArvore(arvore);

            final FileOutputStream out = new FileOutputStream(arquivoCompactado);
            FileUtil.writeLista(out, arquivo);
            if (janela) {
                writeJanela(mapa, out, arquivoCompactado);
            } else {
                write(mapa, out);
            }

        } catch (IOException ex) {
            System.out.println("Erro na criação do arquivo");
        }

        return arquivoCompactado;
    }

    /**
     * Função que utiliza a janela de progresso.
     *
     * @param mapa o mapa a ser utilizado
     * @param out o Stream do arquivo a ser salvo
     * @param byteArquivo os bytes do arquivo
     * @param salvar o arquivo salvo
     * @throws IOException
     */
    private void writeJanela(final Map<Character, String> mapa, final FileOutputStream out, final File salvar) throws IOException {

        final TempoRestante janelaVisual = new TempoRestante(null, true);
        Thread janelaThread = new Thread(janelaVisual);
        janelaThread.start();

        new Thread() {
            @Override
            public void run() {

                int totalB = (int) arquivo.length();
                int j = 0;
                try {
                    StringBuilder valor = new StringBuilder();
                    BufferedReader in = new BufferedReader(new FileReader(arquivo));
                    int atual = in.read();

                    while (atual != -1) {
                        Character bloco = (char) atual;
                        j++;
                        valor.append(mapa.get(bloco));
                        while (valor.length() > 8) {
                            String byteGravar = valor.substring(0, 8);
                            try {
                                out.write(converterStringtoByte(byteGravar));
                                valor = new StringBuilder(valor.substring(8));
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        janelaVisual.setProgresso((j * 100) / totalB);
                        atual = in.read();
                    }
                    janelaVisual.setProgresso((j * 100) / totalB);

                    if (valor.length() > 0) {
                        try {
                            out.write(FileUtil.converterStringtoByte(valor.toString()));
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
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
     * Função que utiliza a janela de progresso.
     * 
     * @param mapa mapa a ser usado
     * @param out o Stream do arquivo a ser salvo
     * @throws IOException 
     */
    private void write(final Map<Character, String> mapa, final FileOutputStream out) throws IOException {

        StringBuilder valor = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(arquivo));
        int atual = in.read();

        while (atual != -1) {
            Character bloco = (char) atual;

            valor.append(mapa.get(bloco));
            while (valor.length() > 8) {
                String byteGravar = valor.substring(0, 8);
                try {
                    out.write(converterStringtoByte(byteGravar));
                    valor = new StringBuilder(valor.substring(8));
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

            atual = in.read();
        }
        if (valor.length() > 0) {
            try {
                out.write(FileUtil.converterStringtoByte(valor.toString()));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}

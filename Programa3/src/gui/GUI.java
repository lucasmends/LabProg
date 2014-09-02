/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import huffman.Interface;
import huffman.io.Descompactar;
import huffman.io.DescompactarMendes;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author lucas
 */
public class GUI extends JFrame{

    /**
     * Construtor do GUI.
     */
    
    private File arquivo;
    
    public GUI() {
        arquivo = null;
        initComponents();
    }

    /**
     * Chamado pelo construtor para povoar o GUI.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Mensagem = new javax.swing.JLabel();
        Painel = new javax.swing.JPanel();
        Arquivo = new javax.swing.JButton();
        Compactar = new javax.swing.JButton();
        Descompactar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Árvore de Huffman");
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        Mensagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Mensagem.setText("Selecione um arquivo");
        getContentPane().add(Mensagem);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5);
        flowLayout1.setAlignOnBaseline(true);
        Painel.setLayout(flowLayout1);

        Arquivo.setText("Arquivo");
        Arquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ArquivoActionPerformed(evt);
            }
        });
        Painel.add(Arquivo);

        Compactar.setText("Compactar");
        Compactar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompactarActionPerformed(evt);
            }
        });
        Painel.add(Compactar);

        Descompactar.setText("Descompactar");
        Descompactar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescompactarActionPerformed(evt);
            }
        });
        Painel.add(Descompactar);

        getContentPane().add(Painel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /**
     * Abre uma janela para selecionar o arquivo.
     * @param evt evento.
     */
    private void ArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ArquivoActionPerformed
        JFileChooser selecionarArquivo = new JFileChooser();
        int retorno = selecionarArquivo.showOpenDialog(this);
        if(retorno == JFileChooser.APPROVE_OPTION){
            this.arquivo = selecionarArquivo.getSelectedFile();
            Mensagem.setText("Selecionado o arquivo " + this.arquivo.getName());
        }
    }//GEN-LAST:event_ArquivoActionPerformed

    /**
     * Se tiver um arquivo selecionado, compacta ele.
     * @param evt evento
     */
    private void CompactarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompactarActionPerformed
        if(this.arquivo != null){

            Interface huff = new Interface(this.arquivo, true);
            huff.compactar();
            this.arquivo = null;
            Mensagem.setText("Selecione um arquivo");
        }
    }//GEN-LAST:event_CompactarActionPerformed

    /**
     * Se tiver um arquivo selecionado, descompacta ele
     * @param evt evento
     */
    private void DescompactarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescompactarActionPerformed
        if(this.arquivo != null){
            Descompactar des = new DescompactarMendes();
            des.descompactar(this.arquivo);
            this.arquivo = null;
            Mensagem.setText("Selecione um arquivo");
        }
    }//GEN-LAST:event_DescompactarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Arquivo;
    private javax.swing.JButton Compactar;
    private javax.swing.JButton Descompactar;
    private javax.swing.JLabel Mensagem;
    private javax.swing.JPanel Painel;
    // End of variables declaration//GEN-END:variables
}

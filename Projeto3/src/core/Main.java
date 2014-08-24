/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import gui.GUI;
import huffman.HuffmanInterface;
import java.io.File;

/**
 *
 * @author lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            GUI dialog = new GUI();
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        } else {
            System.out.println("Compactando");
            new HuffmanInterface(new File(args[0])).compactar();
        }

    }

}

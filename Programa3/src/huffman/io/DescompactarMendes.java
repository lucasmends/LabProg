/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package huffman.io;

import huffman.lista.ListaDeFrequencia;
import huffman.lista.ListaFreq;
import huffman.tree.TreeAbstract;
import huffman.tree.TreeLeaf;
import huffman.tree.TreeUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author lucas
 */
public class DescompactarMendes implements Descompactar{

    @Override
    public File descompactar(File arquivo) {
        try {

            FileInputStream in = new FileInputStream(arquivo);           
            int atual = in.read();
            int total = atual;
            
            File arquivoDescompactado = new File(arquivo.getPath().concat("1"));
            FileOutputStream out = new FileOutputStream(arquivoDescompactado);
            
            ListaFreq listaFrequencia = new ListaDeFrequencia();
            for(int i = 0; i < total; i++){
                TreeLeaf folha;
                atual = in.read();
                Character bloco = (char) atual;
                folha = new TreeLeaf(bloco);
                folha.setPeso(in.read());
                listaFrequencia.inserir(folha);
            }
            
            TreeAbstract arvore = TreeUtil.montarArvore(listaFrequencia);
            TreeAbstract per = arvore;
            //byte[] b = new byte[1];
            //in.read(b);
            atual = in.read();
            
            StringBuilder bloco = new StringBuilder();
            while(atual != -1){
                StringBuilder pegar = new StringBuilder(Integer.toString(atual, 2));
                while(pegar.length()< 8){
                    pegar.insert(0, "0");
                }
                bloco.append(pegar);
                while( bloco.length() > 0 ){
                    if(bloco.charAt(0) == '1')
                        per = per.getLeft();
                    else
                        per = per.getRight();
                    if(per instanceof TreeLeaf){
                        TreeLeaf folha = (TreeLeaf) per;
                        out.write(folha.getValue());
                        bloco = bloco.deleteCharAt(0);
                        per = arvore;
                        continue;
                    }
                    if(bloco.length() > 0)
                        bloco = bloco.deleteCharAt(0);
                }
                atual = in.read();
            }
            out.close();
            
        } catch (IOException ex) {
            System.out.println("Erro na descompactação");
        }
        return null;
    }
    
}

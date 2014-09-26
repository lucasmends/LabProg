/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

/**
 * Classe para manipular a exibição.
 * @author rafaelpaiva
 */
public class ChatList {
    private final List<String> room;
    private final JTextArea saida;
    
    public ChatList(JTextArea s, String nick) {
	room = new ArrayList<>();
	room.add(nick);
	saida = s;
    }
    
    public void Inserir(String s) {
	room.add(s);
    }
    
    public void Exibir() {
	saida.setText(room.get(0));
	for (int i = 1; i < room.size(); i++)
	    saida.append('\n'+room.get(i));
    }
    
    public void Remover(String s) {
	room.remove(s);
    }
    
    public void Limpar() {
	room.clear();
	saida.setText("");
    }
}

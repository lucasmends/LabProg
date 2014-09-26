/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import gui.ClienteGUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import msg.*;

/**
 * Classe para receber as mensagens do servidor. Deve ser rodado de uma Thread.
 *
 * @author rafaelpaiva
 */
public class Escuta implements Runnable {

    private final ObjectInputStream entrada;
    private final StyledDocument saida;
    private final ChatList chat;
    
    private final ClienteGUI parent;

    /**
     * Construtor. É chamado pela classe Cliente.
     *
     * @param doc Referência ao documento do JTextPane, para exibir as
     * mensagens.
     * @param chat Referência ao JTextArea que recebe os nomes dos que estão na
     * sala.
     * @param nick Nickname do usuário.
     * @throws IOException Excessão no GetListener().
     */
    Escuta(StyledDocument doc, JTextArea chat, ClienteGUI parent, String nick) throws IOException {
	entrada = Cliente.GetListener();
	saida = doc;
	this.chat = new ChatList(chat, nick);
	this.parent = parent;
    }

    @Override
    public void run() {
	try {
	    while (true) {
		Mensage msg = (Mensage) entrada.readObject();
		switch (msg.getCode()) {
		    case Codes.MENSAGE_ALL:
			saida.insertString(saida.getLength(), "Em ", null);
			saida.insertString(saida.getLength(), msg.getData(), saida.getStyle("date"));
			saida.insertString(saida.getLength(), ", ", null);
			saida.insertString(saida.getLength(), msg.getLogin(), saida.getStyle("nick"));
			saida.insertString(saida.getLength(), " disse:\n" + msg.getMensage() + "\n\n", null);
			break;
		    case Codes.LOGIN_SUCCESS:
			String[] sala = msg.getMensage().split("\n");
			for (String camarada : sala) {
			    chat.Inserir(camarada);
			}
			chat.Exibir();
			break;
		    case Codes.LOGIN:
			chat.Inserir(msg.getLogin());
			chat.Exibir();
			break;
		    case Codes.DISCONNECT:
			chat.Remover(msg.getLogin());
			chat.Exibir();
			break;
		    case Codes.ERROR_LOGIN_IN_USE:
			Cliente.Desconectar();
			throw new Exception("Login em uso.");
		    case Codes.ERROR_LOGIN:
			Cliente.Desconectar();
			throw new Exception("Erro no login.");
		    case Codes.DISCONNECT_SUCCESS:
			System.out.println("Desconectando");
			if (!Cliente.StartedDisconnect())
			    JOptionPane.showMessageDialog(parent, "Servidor fechou.", "Tchau", JOptionPane.INFORMATION_MESSAGE);
			parent.DisableComponents();
			Cliente.FimDesconectar();
			return;
		}
	    }
	} catch (IOException | ClassNotFoundException | BadLocationException ex) {
	    Logger.getLogger(Escuta.class.getName()).log(Level.SEVERE, null, ex);
	} catch (Exception ex) {
	    try {
		JOptionPane.showMessageDialog(parent, ex.getLocalizedMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		parent.DisableComponents();
		Cliente.FimDesconectar();
	    } catch (IOException ex1) {
		Logger.getLogger(Escuta.class.getName()).log(Level.SEVERE, null, ex1);
	    }
	}
    }
}

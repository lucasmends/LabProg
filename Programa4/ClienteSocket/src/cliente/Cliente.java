/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

import gui.ClienteGUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JTextArea;
import javax.swing.text.StyledDocument;

import msg.*;

/**
 * Classe principal para a comunicação com o servidor.
 * Utiliza a ideia de Singleton, já que apenas uma conexão é feita com o servidor. Logo, não é possível
 * acessar a instância, os acessos são obtidos da própria classe.
 * @author rafaelpaiva
 */
public class Cliente {
    private final Socket socket;
    private final String nick;
    
    private Boolean startDisc;
    
    private final ObjectOutputStream saida;
    
    private static Cliente instance;
    
    private Cliente(String ip, int porta, String name) throws IOException {
	socket = new Socket(ip, porta);
	/*(if (socket.isConnected())
	    System.out.println("Conectado com: "+socket.getInetAddress().getHostAddress());
	if (socket.isClosed())
	    System.out.println("EUHEIN");*/
	nick = name;
	//entrada = new ObjectInputStream(socket.getInputStream());
	saida = new ObjectOutputStream(socket.getOutputStream());
	//System.out.println("Ate aqui, td ok");
	startDisc = false;
    }
    
    /**
     * Função para enviar mensagem para o servidor.
     * @param texto Texto da mensagem
     * @throws IOException 
     */
    public static void Enviar(String texto) throws IOException {
	Mensage msg = new Mensage(Codes.MENSAGE_ALL, instance.nick, texto);
	instance.saida.writeObject(msg);
    }
    
    /**
     * Função para iniciar escuta. A escuta será rodada em uma Thread separada, ela receberá
     * as mensagens oriundas do servidor e exibirá nos componentes corretos.
     * @param doc Referência ao documento do JTextPane, onde são exibidas as mensagens.
     * @param chat Referência a JTextArea, onde são exibidos os nomes das pessoas conectadas ao servidor.
     * @param parent Referência a janela pai.
     * @throws IOException Excessão na criação do InputStream do Socket.
     */
    public static void IniciarEscuta(StyledDocument doc, JTextArea chat, ClienteGUI parent) throws IOException{
	new Thread(new Escuta(doc, chat, parent, instance.nick)).start();
    }
    
    /**
     * Conectar a um servidor.
     * @param ip O IP do servidor.
     * @param porta O número da porta.
     * @param name O nome do usuário.
     * @throws IOException Excessão no envio da mensagem de login ao servidor.
     * @throws ClassNotFoundException Excessão no envio do objeto.
     * @throws Exception Excessão própria, para erro no login.
     */
    public static void Conectar(String ip, int porta, String name) throws IOException, ClassNotFoundException, Exception {
	instance = new Cliente(ip, porta, name);
	Mensage msg = new Mensage(Codes.LOGIN, instance.nick, "");
	instance.saida.writeObject(msg);
    }
    
    /**
     * Desconectar do servidor.
     * @throws IOException Excessão no envio da mensagem de desconexão.
     */
    public static void Desconectar() throws IOException {
	System.out.println("Fechando socket.");
	Mensage msg = new Mensage(Codes.DISCONNECT, instance.nick, "");
	instance.saida.writeObject(msg);
	instance.startDisc = true;
    }
    
    /**
     * Finalizar a "desconexão". Essa função é chamada pela escuta.
     * @throws IOException Fechamento do Socket.
     */
    static synchronized void FimDesconectar() throws IOException {
	instance.socket.close();
	instance = null;
    }
    
    /**
     * Retorna o ObjectInputStream referente ao socket em uso.
     * @return O ObjectInputStream referente ao socket.
     * @throws IOException 
     */
    static ObjectInputStream GetListener() throws IOException {
	return new ObjectInputStream(instance.socket.getInputStream());
    }
    
    /**
     * Verifica se está conectado.
     * @return True se está conectado, False caso contrário.
     */
    public static Boolean IsConected() {
	return (instance != null);
    }
    
    /**
     * Verifica se o Cliente iniciou a "desconexão"
     * @return True se sim.
     */
    static Boolean StartedDisconnect() {
	return instance.startDisc;
    }
}

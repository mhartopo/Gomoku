package network;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import entity.GomokuGame;
import entity.Parser;
import entity.Player;
import gui.ClientUI;

public class Client {
	 BufferedReader in;
	    public static PrintWriter out;
	    JFrame frame = new JFrame("Client");
	    JTextField textField = new JTextField(40);
	    JTextArea messageArea = new JTextArea(8, 40);
		private Socket socket;
		private GomokuGame game;
		public static boolean myTurn = false;
		public static int myID = -99;
	    public Client(GomokuGame game) {
	        // Layout GUI
	    	this.game = game;
	        textField.setEditable(false);
	        messageArea.setEditable(false);
	        frame.getContentPane().add(textField, "North");
	        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
	        frame.pack();

	        // Add Listeners
	        textField.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                out.println(textField.getText());
	                textField.setText("");
	            }
	        });
	    }
	    private String getServerAddress() {
	        return JOptionPane.showInputDialog(
	            frame,
	            "Enter IP Address of the Server:",
	            "Welcome to the Chatter",
	            JOptionPane.QUESTION_MESSAGE);
	    }
	    private String getName() {
	        return JOptionPane.showInputDialog(
	            frame,
	            "Choose a screen name:",
	            "Screen name selection",
	            JOptionPane.PLAIN_MESSAGE);
	    }
	    public void run() throws IOException {
	        // Make connection and initialize streams
	        String serverAddress = getServerAddress();
	        socket = new Socket(serverAddress, 9001);
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream(), true);
	        // Process all messages from server, according to the protocol.
	        Parser parser = new Parser();
	        while (true) {
	            String line = in.readLine();
	            if (line.startsWith("SUBMITNAME")) {
	            	out.println(getName());
	            } else if (line.startsWith("NAMEACCEPTED")){
	            	textField.setEditable(true);
	            } else if (line.startsWith("MESSAGE")) {
	                messageArea.append(line.substring(8) + "\n");
	            } else if(line.startsWith("PLAYERS")){
	            	String players = line.substring(8);
	            	ArrayList<Player> lp = parser.parsePlayers(players);
	            	game.setPlayers(lp);
	            } else if(line.startsWith("BOARD")) {
	            	String S = line.substring(6);
	            	int[][] b = parser.parseBoard(S);
	            	game.setBoard(b);
	            	for(int i = 0; i < b.length; i++) {
	            		for(int j = 0; j < b.length; j++) {
	            			if(b[i][j] != -1) {
	            				ClientUI.buttons[i][j].setPlayer(b[i][j]);
	            				ClientUI.buttons[i][j].paint();
	            			}		
	            		}
	            	}
	            }else if(line.startsWith("ID")){
	            	String S = line.substring(3);
	            	myID = Integer.parseInt(S);
	            	System.out.println(myID);
	            } else if(line.startsWith("ROOM")) {
	            	String room = line.substring(5);
	            	ClientUI.lblRoom.setText("Room : "+room);
	            } else{
	            	Message m = new Message(line);
	            	if(m.getType() == 0) {
	            		game.makeMove(m.getuserID(),m.getX(), m.getY());
	            		ClientUI.buttons[m.getX()][m.getY()].setPlayer(m.getuserID());
        				ClientUI.buttons[m.getX()][m.getY()].paint();
	            	} else if(m.getType() == 1) {
	            		myTurn = myID == m.getuserID();
	            		ClientUI.lblGiiliran.setText("Giliran "+ game.getPalyers().get(m.getuserID()).getName());
	            	} else if(m.getType() == 2) {
	            		ClientUI.lblGiiliran.setText(game.getPalyers().get(m.getuserID()).getName() + " menang!");
	            	} else if(m.getType() == 3) {
	            		game.addPlayer(m.getContent());
	            		ClientUI.lblGiiliran.setText("Player baru : "+m.getContent());
	            	}
	            }
	            messageArea.append(line+"\n");
	            
	        }
	    }
	    public static void main(String[] args) throws Exception {
	        Client client = new Client(ClientUI.game);
	        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        client.frame.setVisible(true);
	        client.run();
	    }

}

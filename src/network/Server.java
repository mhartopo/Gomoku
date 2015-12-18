package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;

import entity.GomokuGame;
import gui.UI;

public class Server extends Thread{
	private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public static Hashtable<Integer,String> names = new Hashtable<Integer,String>();
    public static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
    private GomokuGame game;
    public Server(Socket socket, GomokuGame game) {
    	this.game = game;
    	this.socket = socket;
    }
    public Server(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        try {
            // Create character streams for the socket.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            while (true) {
                out.println("SUBMITNAME");
                name = in.readLine();
                if (name == null) {
                    return;
                }
                synchronized (names) {
                    if (!names.contains(name)) {
                    	Sender sd2 = new Sender(writers);
                        int size = game.getPalyers().size();
                        Message newpl = new Message(3,size,name);
                    	sd2.sendBoardcast(newpl.toString());
                        names.put(size, name);
                        game.addPlayer(name);
                        break;
                    }
                }
            }
            
            out.println("NAMEACCEPTED");
            writers.add(out);
            out.println("ID "+ Integer.toString(game.getPalyers().size()-1));
            out.println("BOARD "+game.getBoardState());
            out.println("PLAYERS "+game.getPlayerStr());
            out.println("ROOM "+game.getRoomName());
            Sender sd = new Sender(writers);
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                //add action to input
                Message m = new Message(input);
                int win = -1;
                if(m.getType() == 0) {
                	game.makeMove(m.getuserID(), m.getX(), m.getY());
                	UI.buttons[m.getX()][m.getY()].setPlayer(m.getuserID());
                	UI.buttons[m.getX()][m.getY()].paint();
                	win = game.checkWin();
                	if(win >= 0) {
                		UI.lblGiiliran.setText(game.getPalyers().get(win).getName() + " menang !");
                		Message mes = new Message(2,game.checkWin());
        				sd.sendBoardcast(mes.toString());
                	}
                }
                if(win < 0) {
                	//cari pemain selanjutnya
                	Message m2 = new Message(1,game.getTurn());
                	System.out.print("giliran = ");
                	System.out.println(m2.toString());
                	//kirim ke client
                	sd.sendBoardcast(input);
                	sd.sendBoardcast(m2.toString());
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (name != null) {
                names.remove(name);
            }
            if (out != null) {
                writers.remove(out);
            }
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
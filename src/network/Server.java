<<<<<<< HEAD
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
                        names.put(game.getPalyers().size(), name);
                        game.addPlayer(name, "Maros");
                        break;
                    }
                }
            }
            
            out.println("NAMEACCEPTED");
            writers.add(out);
            out.println("ID "+ Integer.toString(game.getPalyers().size()-1));
            out.println("BOARD "+game.getBoardState());
            out.println("PLAYERS "+game.getPlayerStr());
            
            Sender sd = new Sender(writers);
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                //add action to input
                Message m = new Message(input);
                if(m.getType() == 0) {
                	game.makeMove(m.getuserID(), m.getX(), m.getY());
                	UI.buttons[m.getX()][m.getY()].setPlayer(m.getuserID());
                	UI.buttons[m.getX()][m.getY()].paint();
                	int win = game.checkWin();
                	if(win >= 0) {
                		UI.lblGiiliran.setText(game.getPalyers().get(win) + " menang !");
                		Message mes = new Message(2,game.checkWin());
        				sd.sendBoardcast(mes.toString());
                	}
                }
                //cari pemain selanjutnya
                Message m2 = new Message(1,game.getTurn());
                System.out.print("giliran = ");
                System.out.println(m2.toString());
                //kirim ke client
                sd.sendBoardcast(input);
                sd.sendBoardcast(m2.toString());
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
=======
package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
<<<<<<< HEAD
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
                        names.put(game.getPalyers().size(), name);
                        game.addPlayer(name, "Maros");
                        break;
                    }
                }
            }
            
            out.println("NAMEACCEPTED");
            writers.add(out);
            out.println("ID "+ Integer.toString(game.getPalyers().size()-1));
            out.println("BOARD "+game.getBoardState());
            out.println("PLAYERS "+game.getPlayerStr());
            while (true) {
                String input = in.readLine();
                if (input == null) {
                    return;
                }
                //add action to input
                Message m = new Message(input);
                if(m.getType() == 0) {
                	game.makeMove(m.getuserID(), m.getX(), m.getY());
                	UI.buttons[m.getX()][m.getY()].setPlayer(m.getuserID());
                	UI.buttons[m.getX()][m.getY()].paint();
                }
                //cari pemain selanjutnya
                Message m2 = new Message(1,game.getTurn());         
                //kirim ke client
                for (PrintWriter writer : writers) {
                    writer.println(input);
                }
                for (PrintWriter writer : writers) {
                    writer.println(m2.toString());
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

=======
import java.util.HashSet;
public class Server {
    private static final int PORT = 9001;
    private static HashSet<String> names = new HashSet<String>();
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        public Handler(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }
                out.println("NAMEACCEPTED");
                writers.add(out);
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
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
}
>>>>>>> origin/master
>>>>>>> origin/master

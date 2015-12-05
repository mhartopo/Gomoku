package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Sender {
	private ArrayList<PrintWriter> writers;
	public Sender(ArrayList<PrintWriter> writers) {
		this.writers = writers;
	}
	public Sender() {
		this.writers = new ArrayList<PrintWriter>();
	}
	public void sendBoardcast(String message) {
		for (PrintWriter writer : writers) {
            writer.println(message);
        }
	}
	public void send(Socket sock, String message) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(sock.getOutputStream(), true);
			pw.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void send(PrintWriter pw, String message) {
		pw.println(message);
	}
}

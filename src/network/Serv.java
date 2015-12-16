package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import entity.GomokuGame;

public class Serv extends Thread{
	private ServerSocket ss;
	private GomokuGame game;
	public Serv(ServerSocket ss, GomokuGame gg) {
		this.ss = ss;
		game = gg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
	         	Socket socket = ss.accept();
	             new Server(socket,game).start();
	           
	        }
		} catch (Exception e){
			
		}finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

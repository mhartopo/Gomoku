package network;

import java.io.IOException;

import javax.swing.JFrame;

import entity.GomokuGame;

public class ClientNet extends Thread{
	private Client cl;
	public ClientNet(GomokuGame game) {
		cl = new Client(game);
	}
	@Override
	public void run() {
		cl.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cl.frame.setVisible(true);
		try {
			cl.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

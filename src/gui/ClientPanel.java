package gui;
/**
 * @author Faisal Prabowo
 * 13513096
 * */
import javax.swing.JButton;

import network.Client;
import network.Message;
import network.Sender;
import entity.GomokuGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClientPanel extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x_pos;
	public int y_pos;
	private int player;
	private GomokuGame game;
	private boolean click;
	public ClientPanel(GomokuGame g){
		x_pos = 0;
		y_pos = 0;
		player = 0;
		game = g;
		click = false;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			if(Client.myTurn) {
				game.makeMove(Client.myID, x_pos, y_pos);
				player = Client.myID;
				
				Message m = new Message(0,Client.myID,x_pos,y_pos);
				System.out.println(m);
				Sender S = new Sender();
				S.send(Client.out, m.toString());
				Client.myTurn = false;		
		}
	}
	public void paint() {
		switch(player){
    	case 0  : setBackground(Color.blue);
    			break;
    	case 1  : setBackground(Color.green);
    			break;
    	case 2  : setBackground(Color.red);
    			break;
    	case 3  : setBackground(Color.yellow);
    			break;
    	case 4  : setBackground(Color.black);
    			break;
    	default : setIcon(null);; break; 
		}
	}

	public int getX_pos() {
		return x_pos;
	}

	public void setX_pos(int x_pos) {
		this.x_pos = x_pos;
	}

	public int getY_pos() {
		return y_pos;
	}

	public void setY_pos(int y_pos) {
		this.y_pos = y_pos;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}
	
	public boolean isClicked(){
		return click;
	}
	
}
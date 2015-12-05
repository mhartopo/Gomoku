package gui;
/**
 * @author Faisal Prabowo
 * 13513096
 * */
import javax.swing.JButton;

import network.Message;
import network.Sender;
import network.Server;
import entity.GomokuGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class panelButton extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x_pos;
	public int y_pos;
	private int player;
	private GomokuGame game;
	private boolean click;
	public panelButton(GomokuGame g){
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
		Sender sd = new Sender(Server.writers);
		if(click == false && game.checkWin() < 0 && game.getPalyers().size() > 0) {
			player = game.getTurn();
			if(game.getTurn() == UI.servID) {
				game.makeMove(game.getTurn(), x_pos, y_pos);
			}
			click = true;
			paint();
			if(game.checkWin() >= 0) {
				UI.lblGiiliran.setText(game.getPalyers().get(game.checkWin()).getName() + " menang !");
				Message m = new Message(2,game.checkWin());
				sd.sendBoardcast(m.toString());
			} else {
				UI.lblGiiliran.setText("Giliran "+game.getPalyers().get(game.getTurn()).getName());
				Message m = new Message(0,player,x_pos,y_pos);
				sd.sendBoardcast(m.toString());
				Message m2 = new Message(1,game.getTurn());
				sd.sendBoardcast(m2.toString());
			}
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
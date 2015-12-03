package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class panelButton extends JButton implements ActionListener{
	public int x_pos;
	public int y_pos;
	private int player;
	private ImageIcon a,b,c,d,f;
	
	public panelButton(){
		x_pos = 0;
		y_pos = 0;
		player = 0;
		this.addActionListener(this);
		a = new ImageIcon(this.getClass().getResource("black.png"));
		b = new ImageIcon(this.getClass().getResource("blue.png"));
		c = new ImageIcon(this.getClass().getResource("red.png"));
		d = new ImageIcon(this.getClass().getResource("green.png"));
		f = new ImageIcon(this.getClass().getResource("yellow.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(x_pos+" "+y_pos);
		switch(player){
    	case 1  : setIcon(a);
    			break;
    	case 2  : setIcon(b);
    			break;
    	case 3  : setIcon(c);
    			break;
    	case 4  : setIcon(d);
    			break;
    	case 5  : setIcon(f);
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
	
	
	
}
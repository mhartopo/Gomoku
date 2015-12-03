package gui;


import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	panelButton buttons[][] = new panelButton[20][20];
	JPanel p = new JPanel();

  public static void main(String[] args) {
	  new GUI();
  }
  
  public GUI (){
  	super("GUI");
  	int player = 1;
    setSize(600,600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    p.setLayout(new GridLayout(20, 20));

    for (int i = 0; i < 20 ;i++) {
    	for (int j = 0; j<20; j++){
    		buttons[i][j] = new panelButton();
    		p.add(buttons[i][j]);
    		buttons[i][j].setX_pos(i);
    		buttons[i][j].setY_pos(j);
    		buttons[i][j].setPlayer(player);
    	}
    	getContentPane().add(p);
    }
  }
}
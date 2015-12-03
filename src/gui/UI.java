package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import java.awt.Color;

public class UI {

	private JFrame frmGomoku;
	panelButton buttons[][] = new panelButton[20][20];

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmGomoku.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		int player = 1;
		frmGomoku = new JFrame();
		frmGomoku.setTitle("GOMOKU");
		frmGomoku.setBounds(10, 10, 1000, 670);
		frmGomoku.setResizable(false);
		frmGomoku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLayeredPane layeredPane = new JLayeredPane();
		frmGomoku.getContentPane().add(layeredPane);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBackground(Color.BLUE);
		layeredPane_1.setLayout(new GridLayout(20, 20,5,5));
		layeredPane_1.setBounds(10, 60, 550, 570);
		
		for (int i = 0; i < 20 ;i++) {
	    	for (int j = 0; j<20; j++){
	    		buttons[i][j] = new panelButton();
	    		layeredPane_1.add(buttons[i][j]);
	    		buttons[i][j].setX_pos(i);
	    		buttons[i][j].setY_pos(j);
	    		buttons[i][j].setPlayer(player);
	    	}
		}
		
		layeredPane.add(layeredPane_1);
		
		JLabel lblGomoku = DefaultComponentFactory.getInstance().createTitle("G . O . M . O . K . U");
		lblGomoku.setFont(new Font("Roboto", Font.BOLD, 20));
		lblGomoku.setBounds(425, 11, 316, 38);
		layeredPane.add(lblGomoku);
	}
}

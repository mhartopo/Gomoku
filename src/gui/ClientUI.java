package gui;
/**
 * @author Muhtar H
 * 13513068
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;

import network.ClientNet;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import entity.GomokuGame;

import java.awt.Font;
import java.awt.Color;

public class ClientUI {

	public JFrame frmGomoku;
	public static ClientPanel buttons[][] = new ClientPanel[20][20];
	private JLayeredPane layeredPane_1;
	public static GomokuGame game = new GomokuGame();
	private JLayeredPane layeredPane = new JLayeredPane();
	public static JLabel lblGiiliran;
	public static JLabel lblRoom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientUI window = new ClientUI();
					(new ClientNet(game)).start();
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
	public ClientUI() {
		initialize();
		UpdateEvent();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmGomoku = new JFrame();
		frmGomoku.setTitle("GOMOKU");
		frmGomoku.setBounds(150, 20, 1000, 670);
		frmGomoku.setResizable(false);
		frmGomoku.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmGomoku.getContentPane().add(layeredPane);
		setLayeredPane();
		JLabel lblGomoku = DefaultComponentFactory.getInstance().createTitle("G . O . M . O . K . U");
		lblGomoku.setFont(new Font("Roboto", Font.BOLD, 20));
		lblGomoku.setBounds(425, 11, 205, 38);
		layeredPane.add(lblGomoku);
		
		lblGiiliran = DefaultComponentFactory.getInstance().createLabel("");
		lblGiiliran.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiiliran.setBounds(689, 122, 257, 22);
		layeredPane.add(lblGiiliran);
		
		lblRoom = DefaultComponentFactory.getInstance().createLabel("");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoom.setBounds(689, 70, 257, 22);
		layeredPane.add(lblRoom);
	}
	
	public void UpdateEvent() {
		
	}

	private void setLayeredPane() {
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setLayout(new GridLayout(20, 20,0,0));
		layeredPane_1.setBounds(10, 60, 550, 570);
		for (int i = 0; i < 20 ;i++) {
	    	for (int j = 0; j<20; j++){
	    		buttons[i][j] = new ClientPanel(game);
	    		layeredPane_1.add(buttons[i][j]);
	    		buttons[i][j].setX_pos(i);
	    		buttons[i][j].setY_pos(j);
	    		buttons[i][j].setBackground(Color.WHITE);
	    	}
		}
		
		layeredPane.add(layeredPane_1);
	} 
}

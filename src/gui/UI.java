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

import network.Serv;
import network.Server;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import entity.GomokuGame;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.io.IOException;
import java.net.ServerSocket;

public class UI{

	private static final int PORT = 9001;
	public JFrame frmGomoku;
	private Button button;
	public static panelButton buttons[][] = new panelButton[20][20];
	private JLayeredPane layeredPane_1;
	public static GomokuGame game = new GomokuGame();
	private JLayeredPane layeredPane = new JLayeredPane();
	TextField textField_1;
	TextField textField;
	static Server servHandler;
	public static ServerSocket listener = null;
	public static JLabel lblGiiliran;
	public static int servID;
	public static int myID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frmGomoku.setVisible(true);
					Serv s = new Serv(listener,game);
					s.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public UI() throws IOException {
		initialize();
		UpdateEvent();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
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
		
		textField = new TextField();
		textField.setBounds(764, 27, 182, 22);
		layeredPane.add(textField);
		
		textField_1 = new TextField();
		textField_1.setBounds(764, 74, 182, 22);
		layeredPane.add(textField_1);
		
		button = new Button("Tambah pemain");
		button.setBackground(Color.WHITE);
		
		button.setBounds(764, 106, 105, 22);
		layeredPane.add(button);
		Label label = new Label("Nama");
		label.setBounds(689, 27, 62, 22);
		layeredPane.add(label);		
		Label label_1 = new Label("Alamat");
		label_1.setBounds(689, 74, 62, 22);
		layeredPane.add(label_1);
		
		lblGiiliran = DefaultComponentFactory.getInstance().createLabel("");
		lblGiiliran.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGiiliran.setBounds(689, 162, 257, 22);
		layeredPane.add(lblGiiliran);
		listener = new ServerSocket(PORT);
	}
	
	public void UpdateEvent() {
		//add new player
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String name = textField.getText();
				String addr = textField_1.getText();
				if(name.compareTo("") != 0) {
					game.addPlayer(name, addr);
					textField.setText("");
					textField_1.setText("");
					myID = game.getPalyers().size()-1;
					if(game.getPalyers().size() == 1) {
						lblGiiliran.setText("Giliran "+game.getPalyers().get(game.getTurn()).getName());
						textField.setEnabled(false);
						textField_1.setEnabled(false);
						button.setEnabled(false);
						servID = 0;
					}
				}
			}
		});
		
	}

	private void setLayeredPane() {
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setLayout(new GridLayout(20, 20,0,0));
		layeredPane_1.setBounds(10, 60, 550, 570);
		for (int i = 0; i < 20 ;i++) {
	    	for (int j = 0; j<20; j++){
	    		buttons[i][j] = new panelButton(game);
	    		layeredPane_1.add(buttons[i][j]);
	    		buttons[i][j].setX_pos(i);
	    		buttons[i][j].setY_pos(j);
	    		buttons[i][j].setBackground(Color.WHITE);
	    	}
		}
		
		layeredPane.add(layeredPane_1);
	}

	
}
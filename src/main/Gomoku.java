package main;

import gui.ClientUI;
import gui.UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;

import java.awt.BorderLayout;

import javax.swing.JButton;

import network.ClientNet;
import network.Serv;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gomoku {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gomoku window = new Gomoku();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gomoku() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnClient = new JButton("CLIENT");
		btnClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ClientUI window = new ClientUI();
							(new ClientNet(ClientUI.game)).start();
							window.frmGomoku.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnClient.setBounds(68, 104, 108, 45);
		desktopPane.add(btnClient);
		
		JButton btnNewButton = new JButton("Server");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UI window = new UI();
							window.frmGomoku.setVisible(true);
							Serv s = new Serv(UI.listener,UI.game);
							s.start();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(237, 104, 108, 45);
		desktopPane.add(btnNewButton);
	}
}

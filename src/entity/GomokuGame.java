package entity;

import java.util.ArrayList;

public class GomokuGame {
	private Board board;
	private ArrayList<Player> players;
	private int numPlayer;
	private int lastMove;
	public GomokuGame() {
		board = new Board();
		players = new ArrayList<Player>();
		numPlayer = 0;
		lastMove = 0;
	}
	
	public void AddPlayer(String name, String address) {
		numPlayer++;
		Player p = new Player(numPlayer, name, address);
		players.add(p);
		lastMove = p.getId();
	}
	
	public void makeMove(Player p, int row, int col) {
		board.setElmt(p.getId(), row, col);
	}
	
	public int nextTurn() {
		int idTurn;
		idTurn = (lastMove % numPlayer) + 1;
		return idTurn;
	}
}

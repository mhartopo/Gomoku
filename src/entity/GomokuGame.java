package entity;

import java.util.ArrayList;

public class GomokuGame {
	private Board board;
	private ArrayList<Player> players;
	private int numPlayer;
	private int nextMove;
	public GomokuGame() {
		board = new Board();
		players = new ArrayList<Player>();
		numPlayer = 0;
		nextMove = -1;
	}
	
	public void addPlayer(String name, String address) {
		numPlayer++;
		Player p = new Player(numPlayer, name, address);
		players.add(p);
		nextMove = nextTurn();
	}
	
	public void makeMove(Player p, int row, int col) {
		board.setElmt(p.getId(), row, col);
	}
	public void makeMove(int id, int row, int col) {
		board.setElmt(id, row, col);
	}
	public int nextTurn() {
		int idTurn;
		idTurn = (nextMove+1) % numPlayer;
		return idTurn;
	}
	public void clear() {
		board.clear();
		nextMove = -1;
	}
	public void play() {
		
	}
}

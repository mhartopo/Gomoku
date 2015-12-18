package entity;

import java.util.ArrayList;

public class Parser {
	public int[][] parseBoard(String S) {
		String[] val = S.split("/");
		System.out.println(val.length);
		int[][] board = new int[20][20];
		for(int i = 0; i < 20; i++) {
			String[] element = val[i].split(",");
			for(int j = 0; j < 20; j++) {
				board[i][j] = Integer.parseInt(element[j]);
			}
		}
		return board;
	}
	public ArrayList<Player> parsePlayers(String S) {
		String val[] = S.split(",");
		ArrayList<Player> lp = new ArrayList<Player>();
		for(int i = 0; i < val.length-1; i++) {
			lp.add(new Player(i, val[i],true));
		}
		return lp;
	}
}

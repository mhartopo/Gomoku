package entity;

public class Board {
	private int[][] boardElmt;
	private Point lastMove;
	private int size;
	public Board() {
		size = 20;
		boardElmt = new int[size][size];
		//inisialisasi tabel
		for(int i = 0; i < 20; i++) {
			for(int j =0; j < 20; j++) {
				boardElmt[i][j] = 0;
			}
		}
		lastMove = new Point(-1,-1);
	}
	public boolean setElmt(int id, int row, int col) {
		if(boardElmt[col][col] != 0) {
			return false;
		} else {
			boardElmt[row][col] = id;
			setLastMove(row,col);
			return true;
		}
	}
	public int getElmt(int i, int j) {
		return boardElmt[i][j];
	}
	public int getWinner() {
		return 0;
	}
	public Point getLastMove() {
		return lastMove;
	}
	public void setLastMove(int x, int y) {
		lastMove.x = x;
		lastMove.y = y;
	}
	public int checkWin() {
		if(lastMove.isEqual(-1,-1)) {
			return -1;
		} else {
			int lastID = boardElmt[lastMove.x][lastMove.y];
			String pattern = "";
			for(int i = 0; i < 5; i++) {
				pattern += Integer.toString(lastID);
			}
			String S = getRow(lastMove.x);
			if(S.matches("(.*)"+pattern+"(*.)")) {
				return lastID;
			}
			S = getCol(lastMove.y);
			if(S.matches("(.*)"+pattern+"(*.)")) {
				return lastID;
			}
			S = getLeftDiagonal(lastMove.x, lastMove.y);
			if(S.matches("(.*)"+pattern+"(*.)")) {
				return lastID;
			}
			S = getRightDiagonal(lastMove.x, lastMove.y);
			if(S.matches("(.*)"+pattern+"(*.)")) {
				return lastID;
			} else {
				return 0;
			}
		}
		
	}
	
	public String getRow(int row) {
		String S = "";
		for(int j = 0; j < size; j++) {
			S += Integer.toString(boardElmt[row][j]);
		}
		return S;
	}
	public String getCol(int col) {
		String S = "";
		for(int i = 0; i < size; i++) {
			S += Integer.toString(boardElmt[i][col]);
		}
		return S;
	}
	
	public String getRightDiagonal(int row, int col) {
		String S = "";
		int cons = col + row;
		int r = 0;
		
		for(int c = cons; c < size; c--) {
			S += Integer.toString(boardElmt[r][c]);
			r++;
		}
		return S; 
	}
	
	public String getLeftDiagonal(int row, int col) {
		String S = "";
		int cons = row - col;
		int r = 0;
		for(int c = cons; c < size; c++) {
			S += Integer.toString(boardElmt[r][c]);
			r++;
		}
		
		return S; 
	}
	
	//inner class point
	public class Point {
		private  int x;
		private int y;
		public Point() {
			x = 0;
			y = 0;
		}
		public Point(int _x, int _y) {
			x = _x;
			y = _y;
		}
		public boolean isEqual(int x, int y) {
			return (this.x == x && this.y == y);
		}
		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public void setX(int _x) {
			x = _x;
		}
		public void setY(int _y) {
			y = _y;
		}
		public boolean isInBoard() {
			return y >= 0 && x >= 0;
		}
	}

}

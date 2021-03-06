package entity;
/**
 * @author Muhtar H
 * 13513068
 * */
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
				boardElmt[i][j] = -1;
			}
		}
		lastMove = new Point(-1,-1);
	}
	public void setBoard(int[][] b) {
		for(int i =0 ; i < boardElmt.length; i++) {
			for(int j = 0; j < boardElmt.length; j++) {
				boardElmt[i][j] = b[i][j];
			}
		}
	}
	
	public boolean setElmt(int id, int row, int col) {
		if(boardElmt[row][col] != -1) {
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
	
	public Point getLastMove() {
		return lastMove;
	}
	public void setLastMove(int x, int y) {
		lastMove.x = x;
		lastMove.y = y;
	}
	public int checkWin() {
		if(lastMove.isEqual(-1,-1)) {
			return -2;
		} else {
			int lastID = boardElmt[lastMove.x][lastMove.y];
			String pattern = "";
			for(int i = 0; i < 5; i++) {
				pattern += Integer.toString(lastID);
			}
			String S = getRow(lastMove.x);
			if(S.matches("(.*)"+pattern+"(.*)")) {
				return lastID;
			}
			S = getCol(lastMove.y);
			if(S.matches("(.*)"+pattern+"(.*)")) {
				return lastID;
			}
			S = getLeftDiagonal(lastMove.x, lastMove.y);
			if(S.matches("(.*)"+pattern+"(.*)")) {
				return lastID;
			}
			S = getRightDiagonal(lastMove.x, lastMove.y);
			if(S.matches("(.*)"+pattern+"(.*)")) {
				return lastID;
			} else {
				return -1;
			}
		}
		
	}
	
	public String getRow(int row) {
		String S = "";
		for(int j = 0; j < size; j++) {
			if(boardElmt[row][j] == -1) {
				S += "-";
			}else {
				S += Integer.toString(boardElmt[row][j]);
			}
		}
		return S;
	}
	public String getCol(int col) {
		String S = "";
		for(int i = 0; i < size; i++) {
			if(boardElmt[i][col] == -1) {
				S += "-";
			}else {
				S += Integer.toString(boardElmt[i][col]);
			}
		}
		return S;
	}
	
	public String getRightDiagonal(int row, int col) {
		String S = "";
		int cons = col + row;
		if(cons >= size) {
			int c = size-1;
			int r;
			for(r = cons - c; r < size; r++) {
				if(boardElmt[r][c] == -1) {
					S += "-";
				}else {
					S += Integer.toString(boardElmt[r][c]);
				}
				c--;
			}
		} else {
			int r = 0;
			for(int c = cons; c > 0; c--) {
				if(boardElmt[r][c] == -1) {
					S += "-";
				}else {
					S += Integer.toString(boardElmt[r][c]);
				}
				r++;
			}
		}
		return S; 
	}
	
	public String getLeftDiagonal(int row, int col) {
		String S = "";
		int r;
		int cons = Math.abs(row - col);
		if((row - col) > 0) {
			r = row - col;
			int c = 0;
			for(r = row-col; r  < size; r++) {
				if(boardElmt[r][c] == -1) {
					S += "-";
				} else {
					S += Integer.toString(boardElmt[r][c]);
				}
				c++;
			}
		} else {
			r = 0;
			for(int c = cons; c < size; c++) {
				if(boardElmt[r][c] == -1) {
					S += "-";
				} else {
					S += Integer.toString(boardElmt[r][c]);
				}
				r++;
			}
		}
		
		return S; 
	}
	
	public void clear() {
		for(int i = 0; i < size; i++) {
			for(int j =0; j < size; j++) {
				boardElmt[i][j] = -1;
			}
		}
		lastMove = new Point(-1,-1);
	}
	public void print() {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(boardElmt[i][j]);
			}
			System.out.println("\n");
		}
	}
	public int getSize() {
		return size;
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

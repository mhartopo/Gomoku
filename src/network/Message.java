package network;
/**
 * @author Muhtar H
 * */
public class Message {
	private int type; //tipe pesan untuk bergerak type = 0. pemain selanjutnya = 1. Untuk status kemenangan = 2.
	private int userId; //id user
	private int posX; //posisi X, jika kosong nilainya -1
	private int posY; //posisi Y, jika kosong nilainya -1
	private String content; //konten pesan, jika kosong nilainya -
	
	//pesan status permainan
	public Message(int _type, int id) {
		type = _type;
		userId = id;
		posX = -1;
		posY = -1;
		content = "-";
		
	}
	//pesan pergerakan user
	public Message(int _type, int id, int x, int y) {
		type = _type;
		userId = id;
		posX = x;
		posY = y;
		content = "-";
	}
	// pesan yang berisi konten terternu (untuk chat)
	public Message(int _type, int id, String _content) {
		type = _type;
		userId = id;
		posX = -1;
		posY = -1;
		content = _content;
	}
	
	//parse dari string
	public Message(String m) {
		parseMessage(m);
	}
	
	public void parseMessage(String s) {
		String val[] = s.split(",");
		type = Integer.parseInt(val[0]);
		userId = Integer.parseInt(val[1]);
		posX = Integer.parseInt(val[2]);
		posY = Integer.parseInt(val[3]);
		content = val[4];
	}
	
	@Override
	public String toString() {
		String S = "";
		S += Integer.toString(type) + ",";
		S += Integer.toString(userId) + ",";
		S += Integer.toString(posX) + ",";
		S += Integer.toString(posY) + ",";
		S += content;
		return S;
	}
	public int getType() {
		return type;
	}
	public int getuserID() {
		return userId;
	}
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public String getContent() {
		return content;
	}
}

package entity;
/**
 * @author Muhtar H
 * 13513068
 * */
public class Player {
	private int id;
	private String name;
	private boolean status;
	
	public Player(int id, String name, boolean status) {
		this.setId(id);
		this.setName(name);
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}

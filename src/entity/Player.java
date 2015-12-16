package entity;
/**
 * @author Muhtar H
 * 13513068
 * */
public class Player {
	private int id;
	private String name;
	private String address;
	
	public Player(int id, String name, String address) {
		this.setId(id);
		this.setName(name);
		this.setAddress(address);
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}

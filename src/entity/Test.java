package entity;

public class Test {
	public static void main(String[] args){
		GomokuGame go = new GomokuGame();
		go.addPlayer("Muhtar", "Maros");
		go.addPlayer("Faisal", "Semarang");
		go.addPlayer("Fikri", "Bengkalis");
		go.play();
	}
	
}


public class Card {
	
	private String rank;
	private String suite;
	private int value;
	
	public Card(String rank, String suite, int value) {
		this.suite = suite;
		this.value = value;
		this.rank = rank;
		
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getRank() {
		return rank;
	}
	
	
	
	public String toString() {
		return rank +" of "+suite;
	}
	
	public void makeSoft() {
		if(value == 11 && rank.equals("Ace"))
			this.value = 1;
	}
	
	public void makeHard() {
		if(value == 1 && rank.equals("Ace")) {
			this.value = 11;
		}
	}
}

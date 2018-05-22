import java.util.*;
public class Deck {
	
	private Card[] deck; 
	private int top;
	
	public Deck()
	{
		deck = new Card[52];
		top = 0;
		
		String[] ranks = {"Ace","King","Queen","Jack","Ten","Nine","Eight","Seven","Six","Five","Four","Three","Two"};
		String[] suits ={"Hearts","Spades","Diamonds","Clubs"};
		int[] values ={11,10,10,10,10,9,8,7,6,5,4,3,2};

		for(int i=0; i<deck.length; i++)
		{
			deck[i] = new Card(ranks[i%13], suits[i%4], values[i%13]);  // assigns all values of 
		}
		shuffle(true);
	}
	
	public void shuffle(boolean again) {
		for(int i = 0; i < deck.length; i++) {
			int swap = (int) (Math.random()*deck.length);
			Card temp = deck[i];
			deck[i] = deck[swap];
			deck[swap] = temp;
		}
		if(again)
			shuffle(false);
	} 
	 
	public void resetAces() {
		for(int i = 0; i < 52; i++) {
			if(deck[i].getValue() == 1) {
				deck[i].makeHard();
			}
		}
	}
	
	public void printDeck() {
		for(Card card: deck) {
			System.out.println(card);
		}
	}
	
	public void resetTop() {
		top = 0;
	}
	
	public Card getTop() {
		return deck[top++];
	}
	

	
}

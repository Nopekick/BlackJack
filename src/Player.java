import java.util.*;

public class Player {

	private boolean isDealer;
	private boolean isDoubledDown = false;
	private ArrayList<Card> hand;
	private int money = 100;
	private int bet = 0;
	
	
	public Player(boolean isDealer) {
		this.isDealer = isDealer;
		hand = new ArrayList<Card>();
		
	}
	
	public void resetDD() {
		isDoubledDown = false;
	}
	
	public void doubleDown() {
		isDoubledDown = true;
	}
	
	public boolean isDD() {
		return isDoubledDown;
	}
	
	/*
	 * returns the value of the hand
	 */
	public int handValue() {
		int val = 0;
		for(Card card: hand) {
			val += card.getValue();
		}
		if(val > 21) {
			if(checkOverflow()) {
				return val - 10;
			}
			return val;
			
		}
		else
			return val;
	}
	
	
	/*
	 * after adding card, returns the new value of the hand
	 */
	public int addCard(Card card) {
		hand.add(card);
		if(!isDealer) {
			System.out.println("Dealt a " + card);
		}
		return handValue();
	}
	
	public ArrayList<Card> getHand(){
		if(!isDealer)
			return hand;
		return null;
	}
	
	/*
	 * returns top card in hand to be shown to other player
	 */
	public Card showTop() {
		return hand.get(0);
	}
	
	/*
	 * changes money amount by parameter
	 */
	public void changeMoney(int change){
		money +=change;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getBet() {
		return bet;
	}
	
	/*
	 * changes bet variable, amount the player is betting
	 */
	public void changeBet(int change) {
		this.bet = change;
	}
	
	/*
	 * checks if the value is greater than 21, if there is an ace, set value to 1 instead of 11
	 */
	public boolean checkOverflow() {
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getValue() == 11) {
				hand.get(i).makeSoft();
				System.out.println("The value of the " + hand.get(i) +" was changed to 1");
				return true;
			}
		}
		return false;
		
	}
	
	/*
	 * returns true if handvalue is over 21
	 */
	public boolean isBust() {
		if(handValue() > 21) {
			return true;
		}
		return false;
	}
	
	public void clearHand() {
		hand.clear();
	}
	

	
}

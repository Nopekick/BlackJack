
import java.util.*;

public class BlackJack {

	static Player human = new Player(false);
	static Player dealer = new Player(true);
	static Scanner input = new Scanner(System.in);
	
	static Deck deck = new Deck();
	static int numwins = 0;
	static int numlosses = 0;
	
	
	public static void main(String[] args) {
		System.out.println("Welcome to Blackjack. You will be playing against a dealer. First to 21 wins.");
		System.out.println("After each round, type in 'quit' if you wish to finish playing");
		game();
	
	}
	
	public static void game() {
		System.out.println("Current money balance: "+human.getMoney());	
		System.out.println();
		ArrayList<String> valid;
				
		while(human.getMoney() >= 5) {
			System.out.println(numwins+" wins, "+numlosses+" losses");
			boolean stay = false;
			boolean win = false;
			String response = "";
			deck.shuffle(true);
			bet();
			dealTwo(human);
			dealTwo(dealer);
			showCard();
			dealerTurn();
			while(!stay && !human.isBust()) {
				System.out.println(); 
				printHand();
				valid = getValid();
				System.out.println("Your valid moves are: "+valid);
				response = input.next();
				switch(response) {
				case "h": hit(); break;
				case "dd": 
					if(!human.isDD()) {
						human.doubleDown();
						doubleDown();
						hit();
						stay = true;
						break;
					}
				case "st": stay = true; break;
				case "su": surrender(); stay = true; break;
				case "thomas": human.changeMoney(human.getBet()*100); break;
				case "quit": System.exit(1);
				default: 
				}
			}
			if((human.handValue() <= 21 && (human.handValue() > dealer.handValue() || dealer.handValue() > 21)) 
					|| human.handValue() <= 21 && human.getHand().size() >= 5)
				win = true;
			if(human.handValue() == dealer.handValue()) {
				System.out.println("Push. Dealer hand: "+dealer.handValue()+ " . Your hand: "+human.handValue());
			}
			else if(win) {
				System.out.println("You won the round. Dealer hand: "+dealer.handValue()+ " . Your hand: "+human.handValue());
				human.changeMoney(human.getBet());
				numwins++;
			} else {
				System.out.println("You lost the round. Dealer hand: "+dealer.handValue()+" . Your hand: "+human.handValue());
				human.changeMoney(-human.getBet());
				numlosses++;
			}
			resetGame();
			
		}
		System.out.println("Out of money. Come back tomorrow!");
	}
	
	public static ArrayList<String> getValid(){
		ArrayList<String> moves = new ArrayList<String>();
			moves.add("Stay (st)");
		if(dealer.showTop().getRank().equals("Ace") && human.getHand().size() == 2)
			moves.add("Surrender (su)");
		if(human.handValue() < 21) {
			moves.add("Hit (h)");
		}
		if(!human.isDD() && human.handValue() < 21) {
			moves.add("Double down (dd)");
		}
			moves.add("Quit (quit)");
		return moves;
	}
	
	public static void resetGame() {
		deck.resetTop();
		deck.resetAces();
		human.resetDD();
		human.clearHand();
		dealer.clearHand();
	}
	
	/*
	 * change bet amount at the beginning of each round
	 */
	public static void bet() {
		if(human.getMoney() >= 5) {
		  int bet = 0;
		while(bet < 5 || bet > human.getMoney()) {
			System.out.println("Place a bet, from " + 5 +" to "+human.getMoney());
			bet = input.nextInt();
		}
		human.changeBet(bet);
		}
	}
	
	public static void showCard() {
		System.out.println("The Dealer is showing a " + dealer.showTop());
	}
	
	public static void printHand() {
		System.out.print("Bet: "+human.getBet() +"  Balance: "+ human.getMoney() + "  Hand Value: " + human.handValue() + "  Your cards: ");
		for(Card card : human.getHand()) {
			System.out.print(card+", ");
		}
		
		System.out.println();
	}
	
	public static void dealTwo(Player player) {
		player.addCard(deck.getTop());
		player.addCard(deck.getTop());
	}
	
	public static void hit() {
			human.addCard(deck.getTop());
	}
	
	public static void dealerTurn() {
		while(dealer.handValue() < 17) {
			dealer.addCard(deck.getTop());	
		}
	}
	
	/*
	 * before the round ends, doubledown to double your bet and hit once,
	 * but you can't hit anymore
	 */
	public static boolean doubleDown() {
		System.out.println("Are you sure you want to double down? (y/n)");
		String decision = input.next();
		if(decision.equals("y")) {
			human.changeBet(human.getBet()*2);
			return true;
		}
		return false;
	}
	
	/*
	 * if the dealer shows an ace, you can surrender, meaning you lose the round and half you bet
	 * so you only lose half the money you would have
	 */
	public static boolean surrender() {
		if(dealer.showTop().getRank().equals("Ace")) {
			System.out.println("The Dealer is showing an ace. Do you want to surrender and halve your bet? (y/n)");
			String decision = input.next();
			if(decision.equals("y")) {
				human.changeMoney(-human.getBet()/2);
				return true;
			}
			return false;
		}
		System.out.println("You can't surrender. Wrong card conditions");
		return false;
	}

}

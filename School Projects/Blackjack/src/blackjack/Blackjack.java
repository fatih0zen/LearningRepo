package blackjack;
import java.util.*;

public class Blackjack implements BlackjackEngine {
	
	/**
	 * Constructor you must provide.  Initializes the player's account 
	 * to 200 and the initial bet to 5.  Feel free to initialize any other
	 * fields. Keep in mind that the constructor does not define the 
	 * deck(s) of cards.
	 * @param randomGenerator
	 * @param numberOfDecks
	 */
	
	private int account = 200;
	private int bet = 5;
	
	private Random randomGenerator;
	private int numberOfDecks;
	
	private int gameStatus;
	
	private ArrayList<Card> deckOfCards;
	private ArrayList<Card> playerCards;
	private ArrayList<Card> dealerCards;
	
	private int playerMaxTotal;
	private int dealerMaxTotal;
	
	public Blackjack(Random randomGenerator, int numberOfDecks) {
	    this.randomGenerator = randomGenerator;
	    this.numberOfDecks = numberOfDecks;
	}
	
	public int getNumberOfDecks() {
		return numberOfDecks;
	}
	
	public void createAndShuffleGameDeck() {
		deckOfCards = new ArrayList<Card>();
		
		for (CardSuit suit : CardSuit.values()) {
			for (CardValue value : CardValue.values()) {
				deckOfCards.add(new Card(value, suit));
			}
		}
		
		Collections.shuffle(deckOfCards, randomGenerator);
	}
	
	public Card[] getGameDeck() {
		Card[] gameDeck = new Card[deckOfCards.size()];
		
		for (int i = 0; i < deckOfCards.size(); i++)
			gameDeck[i] = deckOfCards.get(i);
		
		return gameDeck;
	}
	
	public void deal() {	
		playerCards = new ArrayList<Card>();
		dealerCards = new ArrayList<Card>();
		
		this.gameStatus = Blackjack.GAME_IN_PROGRESS;
		this.account -= this.bet;
		
		createAndShuffleGameDeck();
		
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				playerCards.add(deckOfCards.get(i));
			} else {
				if (i == 1)
					deckOfCards.get(i).setFaceDown();
				
				dealerCards.add(deckOfCards.get(i));
			}
			
		}
		
		for (int i = 0; i < 4; i++)
			deckOfCards.remove(0);
		
		if (getPlayerCardsEvaluation() == Blackjack.BLACKJACK)
			gameStatus = Blackjack.PLAYER_WON;

	}
		
	public Card[] getDealerCards() {
		Card[] dealerDeck = new Card[dealerCards.size()];
		
		for (int i = 0; i < dealerCards.size(); i++)
			dealerDeck[i] = dealerCards.get(i);
		
		return dealerDeck;
	}

	public int[] getDealerCardsTotal() {
		boolean containsAce = false;
		int[] total;
		
		for (Card card : dealerCards)
			if (card.getValue().getIntValue() == 1)
				containsAce = true;
		
		if (containsAce)
			total = new int[2];
		else
			total = new int[1];
		
		for (Card card : dealerCards) {
			total[0] += card.getValue().getIntValue();
		}
		
		if (containsAce) {
			for (Card card : dealerCards) {
				if (card.getValue().getIntValue() == 1)
					total[1] += 11;
				else
					total[1] += card.getValue().getIntValue();
			}
		}
		
		if (total[0] > 21)
			return null;
		else if (total.length == 2 && total[1] > 21) {
			int[] newTotal = new int[1];
			newTotal[0] = total[0];
			return newTotal;
		}
		else
			return total;
	}

	public int getDealerCardsEvaluation() {
		int[] total = getDealerCardsTotal();
		
		if (total == null)
			return Blackjack.BUST;
		
		if (total.length == 2)
			dealerMaxTotal = total[1];
		else
			dealerMaxTotal = total[0];
		
		if (dealerMaxTotal < 21)
			return Blackjack.LESS_THAN_21;
		else if (isBlackjack(dealerCards))
			return Blackjack.BLACKJACK;
		else
			return Blackjack.HAS_21;
	}
	
	public Card[] getPlayerCards() {
		Card[] playerDeck = new Card[playerCards.size()];
		
		for (int i = 0; i < playerCards.size(); i++)
			playerDeck[i] = playerCards.get(i);
		
		return playerDeck;
	}
	
	public int[] getPlayerCardsTotal() {
		boolean containsAce = false;
		int[] total;
		
		for (Card card : playerCards)
			if (card.getValue().getIntValue() == 1)
				containsAce = true;
		
		if (containsAce)
			total = new int[2];
		else
			total = new int[1];
		
		for (Card card : playerCards) {
			total[0] += card.getValue().getIntValue();
		}
		
		if (containsAce) {
			for (Card card : playerCards) {
				if (card.getValue().getIntValue() == 1)
					total[1] += 11;
				else
					total[1] += card.getValue().getIntValue();
			}
		}
		
		if (total[0] > 21)
			return null;
		else if (total.length == 2 && total[1] > 21) {
			int[] newTotal = new int[1];
			newTotal[0] = total[0];
			return newTotal;
		}
		else
			return total;
	}
		
	public int getPlayerCardsEvaluation() {
		int[] total = getPlayerCardsTotal();
		
		if (total == null)
			return Blackjack.BUST;
		
		if (total.length == 2)
			playerMaxTotal = total[1];
		else
			playerMaxTotal = total[0];
		
		if (isBlackjack(playerCards))
			return Blackjack.BLACKJACK;
		else if (playerMaxTotal == 21)
			return Blackjack.HAS_21;
		else
			return Blackjack.LESS_THAN_21;
	}
	
	public void playerHit() {
		playerCards.add(deckOfCards.get(0));
		deckOfCards.remove(0);
		
		if (getPlayerCardsEvaluation() == Blackjack.BUST)
			gameStatus = Blackjack.DEALER_WON;
		
		if (getPlayerCardsEvaluation() == Blackjack.BLACKJACK)
			gameStatus = Blackjack.PLAYER_WON;
	}
	
	public void playerStand() {
		dealerCards.get(0).setFaceUp();
		
		if (getDealerCardsEvaluation() == Blackjack.BUST) {
			gameStatus = Blackjack.PLAYER_WON;
			account += (bet * 2);
		}
		
		while (getDealerCardsEvaluation() != Blackjack.BUST && dealerMaxTotal < 16) {
			dealerCards.add(deckOfCards.get(0));
			deckOfCards.remove(0);
		}
		
		if (getDealerCardsEvaluation() == Blackjack.BUST) {
			gameStatus = Blackjack.PLAYER_WON;
			account += (bet * 2);
		}
		else if (playerMaxTotal > dealerMaxTotal) {
			gameStatus = Blackjack.PLAYER_WON;
			account += (bet * 2);
		}
		else if (dealerMaxTotal > playerMaxTotal) {
			gameStatus = Blackjack.DEALER_WON;
		}
		else {
			gameStatus = Blackjack.DRAW;
			account += bet;
		}
	}
	
	public int getGameStatus() {
		return this.gameStatus;
	}
		
	public void setBetAmount(int amount) {
		if (account > 0) {
			bet = amount;
			account -= amount;
		}
	}
	
	public int getBetAmount() {
		return bet;
	}
	
	public void setAccountAmount(int amount) {	
		account = amount;
	}
	
	public int getAccountAmount() {
		return account;
	}
	
	/* Feel Free to add any private methods you might need */
	
	private boolean isBlackjack(ArrayList<Card> cards) {
		boolean hasAce = false;
		boolean hasTen = false;
		
		for (Card card : cards) {
			if (card.getValue().getIntValue() == 1)
				hasAce = true;
			
			if (card.getValue().getIntValue() == 10)
				hasTen = true;
		}
		
		return hasAce && hasTen;
	}
}
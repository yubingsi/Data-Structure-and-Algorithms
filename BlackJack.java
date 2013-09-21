import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implement blackjack with generic deck of cards
 */

enum Suit {
	Club(0), Dimand(1), Heart(2), Spade(3);
	
	private int value;
	Suit(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public Suit getSuitFromValue(int value) throws Exception {
		switch(value) {
			case 0 : return Suit.Club; 
			case 1 : return Suit.Dimand;
			case 2 : return Suit.Heart;
			case 3: return Suit.Spade;
			default: throw new Exception("no such suit");
		}
	}
}

class Deck<T extends Card> {
	ArrayList<T> cards = new ArrayList<T>();
	int dealtIndex = 0; // marks first undealt card
	
	public void setDeckOfCard(ArrayList<T> cards) {
		//
	}
	
	public void shuffle(ArrayList<T> cards) {
		Collections.shuffle(cards);
	}
	
}

abstract class Card {
	protected int faceValue;
	protected Suit suit;
	private boolean isAvailable;
	
	public Card(int faceValue, Suit suit) {
		this.faceValue = faceValue;
		this.suit = suit;
	}
	
	public abstract int value();
	
	public boolean isAvailabel() {
		return isAvailable;
	}
	
	public void markUnAvailable() {
		this.isAvailable = false;
	}
	
	public void markAvailable() {
		this.isAvailable = true;
	}	
}

class Hand<T extends Card> {
	ArrayList<T> cards = new ArrayList<T>();
	
	public void addCard(T card) {
		cards.add(card);
	}
	
	public void removeCard(T card) {
		cards.remove(card);
	}
	
	public int score() {
		int score = 0;
		for(T card : cards) {
			score += card.value();
		}
		return score;
	}
}

class BlackJackCard extends Card {
	public BlackJackCard(int faceValue,Suit suit) {
		super(faceValue,suit);
	}

	@Override
	public int value() {
		if(this.isAce()) {
			return 1;
		}
		if(faceValue >10 && faceValue < 13) {
			return 10;
		}
		return faceValue;
	}
	
	public int getMinValue() {
		if(isAce()) {
			return 1;
		}
		return value();
	}
	
	public int getMaxValue() {
		if(isAce()) {
			return 11;
		}
		return value();
	}
	
	public boolean isAce() {
		if(this.faceValue == 1) {
			return true;
		}
		return false;
	}
	
}

class BlackJackHand extends Hand<BlackJackCard> {
	public int Score() {
		ArrayList<Integer> scores = possibleScores();
		
		int minOver = Integer.MIN_VALUE;
		int maxUnder = Integer.MAX_VALUE;
		
		for(Integer score : scores) {
			if(score > 21 && score < minOver) {
				minOver = score;
			} if(score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		return maxUnder == Integer.MAX_VALUE ? minOver : maxUnder;
	}
	
	public ArrayList<Integer> possibleScores() {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		int baseScore = 0;
		int counter = 0;
		for(BlackJackCard card :cards) {
			if(card.isAce()) {
				counter ++;
			} else {
				baseScore += card.value();
			}
		}
		possibleScoreHelper(scores,0,counter,0);
		for(Integer score : scores) {
			score += baseScore;
		}
		return scores;
	}
	
	private void possibleScoreHelper(ArrayList<Integer> scores,int level, int depth,int score) {
		if(level == depth) {
			scores.add(score);
		}
		for(int i=1; i<=11; i=i+10) {
			possibleScoreHelper(scores,level + 1, depth, score + i);
		}
		
	}
	
	public boolean isBusted() {
		return score() > 21;
	}
	
	public boolean is21() {
		return score() == 21;
	}
}
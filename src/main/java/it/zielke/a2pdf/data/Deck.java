package it.zielke.a2pdf.data;

import java.util.List;
import java.util.Vector;

/**
 * A deck of learning cards.
 * 
 */
public class Deck {
	private List<Card> cards;

	public Deck() {
		this.cards = new Vector<Card>();
	}

	public void addCard(Card c) {
		this.cards.add(c);
	}

	/**
	 * Add a new card to the deck by specifying its front and back side content.
	 * 
	 * @param front
	 *            front side content
	 * @param back
	 *            back side content
	 */
	public void addCardSides(String front, String back) {
		Card c = new Card();
		c.setFront(front);
		c.setBack(back);
		addCard(c);
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	/**
	 * Returns the content of all front sides or all back sides of decks in the
	 * deck.
	 * 
	 * @param frontOrBack
	 *            0: front side, 1: back side
	 * @return content of all cards
	 */
	public List<String> getCardSideContent(int frontOrBack) {
		List<String> sideContent = new Vector<String>();
		for (Card card : cards) {
			sideContent.add(card.getSide(frontOrBack));
		}
		return sideContent;
	}

	public List<String> getCardFrontSideContent() {
		return getCardSideContent(0);
	}

	public List<String> getCardBackSideContent() {
		return getCardSideContent(1);
	}

	/**
	 * Returns a single card by specifying its position within the deck.
	 * 
	 * @param i
	 *            0-based position
	 * @return the card if found, null if not found.
	 */
	public Card getCard(int i) {
		try {
			return this.cards.get(i);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

}

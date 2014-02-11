package it.zielke.a2pdf.data;

/**
 * A learning card with front side and back side.
 * 
 */
public class Card {
	private String[] text;

	public Card() {
		this.text = new String[2];
	}

	public String getSide(int i) {
		return this.text[i];
	}

	public String getFront() {
		return getSide(0);
	}

	public String getBack() {
		return getSide(1);
	}

	public void setSide(int i, String s) {
		this.text[i] = s;
	}

	public void setFront(String s) {
		setSide(0, s);
	}

	public void setBack(String s) {
		setSide(1, s);
	}
}

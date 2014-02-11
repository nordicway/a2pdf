package it.zielke.a2pdf;

import it.zielke.a2pdf.data.Card;
import it.zielke.a2pdf.data.Deck;
import it.zielke.a2pdf.util.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

/**
 * Formats a deck of cards by applying a template from a file.
 * 
 */
public class HTMLFormatter {

	private String templateName = "template";
	private int side;
	private String template;

	/**
	 * @param templateName
	 *            file name of the template to use.
	 * @param side
	 *            card side. 0: front side, 1: back side
	 */
	public HTMLFormatter(String templateName, int side) {
		this.templateName = templateName;
		this.side = side;
	}

	/**
	 * Sets the content of the template file as a string. Will look for
	 * templateName_front.txt or templateName_back.txt and return their contents
	 * based on which side of the card to format. If no template file can be
	 * found, will return an HTML formatted error.
	 * 
	 * @return
	 */
	private void setTemplate() {
		String sideName = (side == 0 ? "front" : "back");
		File templateFile = new File(Util.getJARFileLocation(), String.format(
				"%s_%s.txt", templateName, sideName));
		System.out.println("Looking for template file: "
				+ templateFile.getAbsolutePath());

		try {
			this.template = FileUtils.readFileToString(templateFile, "UTF-8");
		} catch (IOException e) {
			this.template = "<html><body>Error: template file not found in directry</body></html>";
		}
	}

	/**
	 * Fills the template by replacing template variables $$FRONT$$ (front side)
	 * and $$BACK$$ (back side).
	 * 
	 * @param front
	 *            front side content
	 * @param back
	 *            back side content
	 * @return template including the content
	 */
	private String fillTemplate(String front, String back) {
		return template.replace("$$FRONT$$", front).replace("$$BACK$$", back);
	}

	/**
	 * Returns a list of HTML formatted sides of all cards in a deck. Either
	 * front side or back side.
	 * 
	 * @param deck
	 *            a deck to format
	 * @param side
	 *            side to format. 0: front side, 1: back side
	 * @return HTML formatted sides
	 */
	public List<String> format(Deck deck, int side) {
		List<String> htmlContent = new Vector<String>();
		setTemplate();
		for (int i = 0; i < deck.getCards().size(); i++) {
			Card c = deck.getCard(i);
			htmlContent.add(fillTemplate(c.getFront(), c.getBack()));
		}
		return htmlContent;
	}
}

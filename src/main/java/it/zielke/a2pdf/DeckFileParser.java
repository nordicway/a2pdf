package it.zielke.a2pdf;

import it.zielke.a2pdf.data.Deck;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * Parses Anki deck export files and returns the decks found.
 * 
 */
public class DeckFileParser {

	private File deckFile;
	private Deck deck;

	private DeckFileParser() {
		deck = new Deck();
	}

	/**
	 * @param deckFile
	 *            an Anki TXT deck file
	 */
	public DeckFileParser(File deckFile) {
		this();
		this.deckFile = deckFile;
	}

	/**
	 * Parses the deck from the given text file and return its object
	 * representation.
	 * 
	 * @return deck in object representation
	 * @throws IOException
	 *             if the deck text file could not be read
	 */
	public Deck processDeck() throws IOException {
		parse(deckFile);
		return deck;
	}

	private void parse(File f) throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(deckFile),
				"UTF-8");
		for (String line : lines) {
			parseLine(line);
		}
	}

	private void parseLine(String line) {
		String[] parts = line.split("\t");
		// Anki sometimes uses non-breakable spaces. We want text to break so it
		// does not overflow.
		// TODO make this configurable
		deck.addCardSides(parts[0], parts[1].replace("&nbsp;", " "));
	}
}

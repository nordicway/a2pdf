package it.zielke.a2pdf.data;

import java.io.File;

/**
 * Runtime settings
 * 
 */
public class Settings {

	private File deckFile;
	private File outputFile;
	private String baseURI;

	public File getDeckFile() {
		return deckFile;
	}

	public void setDeckFile(File deckFile) {
		this.deckFile = deckFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public String getBaseURI() {
		return baseURI;
	}

	public void setBaseURI(String baseURI) {
		this.baseURI = baseURI;
	}

}

package it.zielke.a2pdf;

/*
 * Copyright (c) 2013, Bjoern Zielke
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

import it.zielke.a2pdf.data.Deck;
import it.zielke.a2pdf.data.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 * A2PDF can convert Anki SRS cards to PDF.
 * 
 */
public class Anki2PDF {

	private Settings settings;
	private Deck deck;

	public Anki2PDF() {
		this.settings = new Settings();
		this.deck = new Deck();
	}

	/**
	 * Get a filename of the actual output file based on the current side. If
	 * the user specified filename is "C:/anki.pdf", then the resulting output
	 * file will either be "C:/anki_0.pdf" or "C:/anki_1.pdf" based on the
	 * current side.
	 * 
	 * @param side
	 *            current side, 0: front side, 1: back side.
	 * @return resulting file name
	 */
	private File getCurrentOutputFile(int side) {
		return new File(String.format("%s_%s.pdf", FilenameUtils
				.removeExtension(this.settings.getOutputFile()
						.getAbsolutePath()), side));
	}

	public void run() {
		try {
			this.deck = new DeckFileParser(this.settings.getDeckFile())
					.processDeck();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// generate different PDFs for each side. 0: front side, 1: back side
		for (int i = 0; i <= 1; i++) {
			try {
				// TODO make template name configurable. This requires an actual
				// command-line parser, see main method.
				HTMLFormatter htmlFormatter = new HTMLFormatter("template", i);
				List<String> currentSideContent = htmlFormatter.format(
						this.deck, i);

				generatePDF(currentSideContent, getCurrentOutputFile(i));
			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Entry point. TODO use a fully featured command-line parser.
	 * 
	 * @param args
	 *            arg0: deck export file location (required). arg1: output file
	 *            location (optional). arg2: base directory; can be specified if
	 *            not equal to the deck export directory
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Anki2PDF anki2PDF = new Anki2PDF();

		// check for invalid deck file
		if (args.length < 1 || !new File(args[0]).canRead()) {
			error("Please specify a valid deck export file");
			return;
		} else {
			anki2PDF.settings.setDeckFile(new File(args[0]));
		}

		// check for invalid output file
		if (args.length < 2) {
			File tempFile = File.createTempFile(anki2PDF.settings.getDeckFile()
					.getName(), ".pdf");
			warn("Output directory not specified or not writable. Creating temporary file in this directory instead: "
					+ tempFile);
			anki2PDF.settings.setOutputFile(tempFile);
		} else {
			anki2PDF.settings.setOutputFile(new File(args[1]));
		}

		// set a valid base URI for images to display correctly
		anki2PDF.settings.setBaseURI(String.format("file:///%s/",
				anki2PDF.settings.getDeckFile().getParentFile()
						.getAbsolutePath().replace("\\", "/")));

		System.out.println("Base URI: " + anki2PDF.settings.getBaseURI());

		anki2PDF.run();

	}

	public void generatePDF(List<String> content, File outputFile)
			throws DocumentException, IOException {

		OutputStream os = null;
		try {
			os = new FileOutputStream(outputFile);
			ITextRenderer renderer = new ITextRenderer();

			renderer.setDocumentFromString(content.get(0),
					settings.getBaseURI());
			renderer.layout();
			renderer.createPDF(os, false);

			for (String input : content) {
				if (content.get(0).equals(input))
					continue;
				renderer.setDocumentFromString(input, settings.getBaseURI());
				renderer.layout();
				renderer.writeNextDocument();
			}

			// complete the PDF
			renderer.finishPDF();

			System.out.println("PDF File with " + content.size()
					+ " documents rendered as PDF to " + outputFile);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void error(String s) {
		System.out.println("Error: " + s);
	}

	public static void warn(String s) {
		System.out.println("Warning: " + s);
	}

}

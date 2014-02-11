## A2PDF

A2PDF is an unofficial PDF converter for Anki SRS learning cards.
It accepts Anki deck exports in text format and outputs two PDF files per deck:

- deckname_0.pdf for front side
- deckname_1.pdf for back side

Key features:

- convert Anki decks to PDF
- print Anki cards (using Adobe reader)
- customizable HTML templates

## Requirements

Java 6 or better

## Installation

Either

- download the JAR or
- build it yourself using Maven.

## Quick Start

### Create an export directory

Create a directory on your computer to use for deck exports.

### Export deck

Open Anki. choose File - Export - with the following settings:

- Export format: Cards in plain text
- Include: Your deck name

Export into the directory you just created.

If your deck contains any images you want to include, you have to copy those
 into the export directory manually before running the program.

### Run A2PDF

Start A2PDF on the command-line:

    java -jar a2pdf.jar PATH_TO_DECK_FILE PATH_TO_OUTPUT_FILE
	
with:

PATH_TO_DECK_FILE (required): the absolute path name of the deck file you just
 exported, eg. C:\anki\deckname.txt.

PATH_TO_OUTPUT_FILE (optional): the absolute path name of the target file.
 If you choose not to set this, A2PDF will save to your system's temporary
 directory.

### Print Anki cards

You can now open the resulting PDFs using Adobe Reader to print them.
 4 pages per sheet ("2 x 2") with landscape orientation is usually a good mix
 between readability and amount of paper.

Some duplex printers also allow you to automatically print two PDFs at the same
 time.

## Tweak PDF output

You may edit **template_front.txt** and **template_back.txt** in the application
 directory to change PDF appearance.

This requires a basic understanding of HTML and/or CSS. For example, you can
 resize the text on your cards by changing the **font-size** attribute.

## Limitations

While PDF export works great for simple cards, here are some limitations:

- Obviously, you won't be able to print audio and video cards.
- Cards with images are okay, but make sure they are not too big to print on a
 single page.
- LaTEX cards may or may not work since I never looked into those exports.
- Complex cards using third-party add-ons will prove to be problematic.

## Documentation

<a href="http://www.zielke.it/a2pdf/">http://www.zielke.it/a2pdf/</a>

## Contribution

Please feel welcome to fork this project on Github, send pull requests and
 report any bugs you come across.

## Attribution

This software makes heavy use of the Flying Saucer library which is available
 at https://code.google.com/p/flying-saucer/

## License

    A2PDF
    Copyright (C) 2013, Bjoern Zielke

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
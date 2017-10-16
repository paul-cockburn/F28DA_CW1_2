package F28DA_CW1_2;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/** Main class for the Word Locator program */
public class WordLocator {

	static final File textFilesFolder = new File("Text_Files");
	static final FileFilter commandFileFilter = (File file) -> file.getParent()==null;
	static final FilenameFilter txtFilenameFilter = (File dir, String filename) -> filename.endsWith(".txt");

	public static void main(String[] argv) {
		if (argv.length != 1 ) {
			System.err.println("Usage: WordLocator commands.txt");
			System.exit(1);
		}
		try{
			File commandFile = new File(argv[0]);
			if (commandFile.getParent()!=null) {
				System.err.println("Use a command file in current directory");
				System.exit(1);
			}

			// creating a command reader from a file
			FileWordReader commandReader = new FileWordReader(commandFile);

			// initialise map
			IWLMap wordLocsMap;
			// ...
			
			// reading the content of the command file
			while(commandReader.hasNextWord()) {
				// getting the next command
				String command = commandReader.nextWord().getWord();

				switch (command) {
				case "addall":
					assert(textFilesFolder.isDirectory());
					File[] listOfFiles = textFilesFolder.listFiles(txtFilenameFilter);
					Arrays.sort(listOfFiles);
					for (File textFile : listOfFiles) {
						FileWordReader wordReader = new FileWordReader(textFile);
						while (wordReader.hasNextWord()) {
							WLoc wordLoc = wordReader.nextWord();
							// adding word to the map
							// ...
						}
					}
					break;

				case "add":
					File textFile = new File(textFilesFolder, commandReader.nextWord().getWord()+".txt");
					FileWordReader wordReader = new FileWordReader(textFile);
					while (wordReader.hasNextWord()) {
						WLoc word = wordReader.nextWord();
						// adding word to the map
						// ...
					}
					break;

				case "search":
					int nb = Integer.parseInt(commandReader.nextWord().getWord());
					String word = commandReader.nextWord().getWord();
					// search for word entry in map
					// ...
					break;

				case "remove":
					File textFileToRemove = new File(textFilesFolder, commandReader.nextWord().getWord()+".txt");
					// remove word-locations
					// ...
					break;

				case "overview":
					// print overview
					// ...
					break;

				default:
					break;
				}

			}

		}
		catch (IOException e){ // catch exceptions caused by file input/output errors
			System.err.println("Check your file name");
			System.exit(1);
		}  
	}
}

package net.twaiku;

import java.io.IOException;
import java.util.ArrayList;

import edu.cmu.sphinx.linguist.dictionary.*;

public class HaikuDetector {
	private static final String WORDS_FILE = "src/main/resources/cmudict-0.7b";
    private static final String PHONES_FILE = "src/main/resources/cmudict-0.7b.phones";


	public static void megadog(ArrayList<String> words) throws IOException{
		System.out.println("FIRST LINE!");
		TextDictionary td = new TextDictionary();
		System.out.println("last line");
	}
}

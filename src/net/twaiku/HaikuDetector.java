package net.twaiku;

import java.io.IOException;
import java.util.ArrayList;

import edu.cmu.sphinx.linguist.dictionary.*;

public class HaikuDetector {
	public void megadog(ArrayList<String> words) throws IOException{
		TextDictionary td = new TextDictionary();
		td.allocate();
		System.out.println(td.getWordDictionaryFile().toString());
	}
}

package net.twaiku;

import java.util.ArrayList;

public class tweetSweeper {
	
	public static ArrayList<String> tweets(ArrayList<String> list, String tweets) {

		// fill the array with a string
		for (String word : tweets.split(" ")) {
			list.add(word);
		}

		// Create two while loops to check for the first index and last index of the
		// ArrayList
		while ((list.get(0)).contains("@") || list.get(0).contains("Http")) {
			list.remove(0);
		}
		while (list.get(list.size() - 1).contains("@") || list.get(list.size() - 1).contains("http")) {
			list.remove(list.get(list.size() - 1));
		}
		System.out.println(list);

		if (tweets.contains("@")) {
			list.clear();
		} 
		return list;
	}

}

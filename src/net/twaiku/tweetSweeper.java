package net.twaiku;

import java.io.IOException;
import java.util.ArrayList;

import net.twaiku.cmu.CmuDictionary;
import net.twaiku.rhymer.Rhymer;
import net.twaiku.rhymer.RhymeResult;

public class tweetSweeper {

	public static ArrayList<String> tweets(String tweets) {
		ArrayList<String> list = new ArrayList<String>();

		for (String word : tweets.split(" ")) {
			list.add(word);
		}

		while ((list.get(0)).contains("@") || list.get(0).contains("http")) {
			list.remove(0);
		}
		while (list.get(list.size() - 1).contains("@") || list.get(list.size() - 1).contains("http")) {
			list.remove(list.get(list.size() - 1));
		}

		if (list.contains("@") || list.contains("http")) {
			list.clear();
		}
		return list;
	}

	public static ArrayList<Integer> wordCheck(ArrayList<String> list) throws IOException {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		Rhymer rhymer = CmuDictionary.loadRhymer();
		for (int i = 0; i < list.size(); i++) {
			numList.add(rhymer.getSyllables(list.get(i)));
		}
		return numList;

	}

	public static int countNum(ArrayList<Integer> numList) {
		int totalCount = 0;

		for (int numCounter : numList) {
			totalCount = numCounter + totalCount;
			//System.out.println(totalCount);
		}
		return totalCount;

	}

}

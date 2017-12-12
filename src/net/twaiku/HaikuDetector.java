package net.twaiku;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import net.twaiku.cmu.CmuDictionary;
import net.twaiku.dto.TwaikuDTO;
import net.twaiku.rhymer.Rhymer;

public class HaikuDetector {

	public static int[] ifHaiku(String[] words, Rhymer rhymer) throws IOException {
		//Method to determine if array of (sanitized) words are a haiku, and if so return the index position of the line breaks
		
		int j = 0;
		int r = 0;
		int k = 0;//Still needed?
		int i = 0;
		int syllableCount = 0;
		final int line1 = 5;
		final int line2 = 12;
		final int line3 = 17;
		int firstSyllableNum = 0;
		int secondSyllableNum = 0;
		int thirdSyllableNum = 0;
		int[] haikuAndIndexes = {0, 0, 0, 0};

		// Starting point
		for (i = 0; syllableCount <= 17 && i < 5; i++) {
			if (words.length > i) {
				// Use placeholder vars such that we call getSyllables as few times as possible
				firstSyllableNum = rhymer.getSyllables(words[i]);

			}
			if (firstSyllableNum == 0) {

				System.out.println("This is not a haiku");
				
				return haikuAndIndexes;
			}
			if (syllableCount == line1) {

				for (j = i; syllableCount <= 17 && j < 12; j++) {
					if (words.length > j) {
						secondSyllableNum = rhymer.getSyllables(words[j]);

					}
					if (secondSyllableNum == 0) {
						// System.out.println("String" + words[j] + "not found");
						System.out.println("This is not a haiku");
						return haikuAndIndexes;
					}
					;
					if (syllableCount == line2) {

						// System.out.println("second 5 check");
						for (r = j; syllableCount <= 17 && r < 17; r++) {
							if (words.length > r) {
								thirdSyllableNum = rhymer.getSyllables(words[r]);
								if (thirdSyllableNum == 0) {
									// System.out.println("String" + words[r] + "not found");
									System.out.println("This is not a haiku");
									return haikuAndIndexes;
								}
								
							}
							// System.out.println("r =" + r);
							if (syllableCount == line3 && (words.length == r)) {
								System.out.println("This is a Haiku");
								
								formatToHaiku(i, j, r, words);
								haikuAndIndexes[0] = 1;
								haikuAndIndexes[1] = i;
								haikuAndIndexes[2] = j;
								haikuAndIndexes[3] = r;
								return haikuAndIndexes;
							} else if ((syllableCount + thirdSyllableNum) > line3 || thirdSyllableNum <= 0) {
								// System.out.println("failed second 5 check");
								System.out.println("This is not a haiku");
								return haikuAndIndexes;
							} else {
								syllableCount += thirdSyllableNum;
								// System.out.println(syllableCount);
							}
						}

					} else if ((syllableCount + secondSyllableNum) > line2) {
						// System.out.println("failed 7 check");
						System.out.println("This is not a haiku");
						return haikuAndIndexes;
					} else {
						syllableCount += secondSyllableNum;
						// System.out.println(syllableCount);
					}

				}

			} else if ((syllableCount + firstSyllableNum) > line1) {
				// System.out.println("failed line1 test");
				System.out.println("This is not a haiku");
				return haikuAndIndexes;
			} else {
				syllableCount += firstSyllableNum;
				// System.out.println(syllableCount);

			}
		}

		System.out.println("This is not a haiku");
		return haikuAndIndexes;
	}

	public static String formatToHaiku(int l, int m, int n, String[] words) {
		String[] formated = new String[words.length];
		int k = 0;
		String tempString = "";

		for (k = 0; k <= l-1 ; k++) {
			// System.out.print(words[k] + " ");
			formated[k] = words[k];

		}
		formated[l-1] = words[l-1] + "\n\n";

		for (k =k; k <= m-1 ; k++) {
			// System.out.print(words[k] + " ");
			formated[k] = words[k];
		}
		formated[m-1] = words[m-1] + "\n\n";

		for (k = k; k <= n-1 ; k++) {
			// System.out.print(words[k] + " ");
			formated[k] = words[k];

		}

		for (String temp : formated) {
			 tempString += temp + " ";
		}

		return tempString;
	}
	
	
}

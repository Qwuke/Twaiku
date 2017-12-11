package net.twaiku;

import java.io.IOException;
import java.lang.reflect.Array;

import net.twaiku.cmu.CmuDictionary;
import net.twaiku.rhymer.Rhymer;

public class HaikuSyllableCoutingFromArray {

	public static boolean count(String[] words, Rhymer rhymer) throws IOException {

		int j = 0;
		int r = 0;
		int k = 0;
		int i = 0;
		int syllableCount = 0;
		final int line1 = 5;
		final int line2 = 12;
		final int line3 = 17;
		int firstSyllableNum = 0;
		int secondSyllableNum = 0;
		int thirdSyllableNum = 0;

		// Starting point
		for (i = 0; syllableCount <= 17 && i < 5; i++) {
			if (words.length > i) {
				// Use placeholder vars such that we only
				firstSyllableNum = rhymer.getSyllables(words[i]);

			}
			if (firstSyllableNum == 0) {

				System.out.println("This is not a haiku");
				return false;
			}
			if (syllableCount == line1) {

				for (j = i; syllableCount <= 17 && j < 12; j++) {
					if (words.length > j) {
						secondSyllableNum = rhymer.getSyllables(words[j]);

					}
					if (secondSyllableNum == 0) {
						// System.out.println("String" + words[j] + "not found");
						System.out.println("This is not a haiku");
						return false;
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
									return false;
								}
								;
							}
							// System.out.println("r =" + r);
							if (syllableCount == line3 && (words.length == r)) {
								System.out.println("This is a Haiku");
								printer(i, j, r, words);
								return true;
							} else if ((syllableCount + thirdSyllableNum) > line3 || thirdSyllableNum <= 0) {
								// System.out.println("failed second 5 check");
								System.out.println("This is not a haiku");
								return false;
							} else {
								syllableCount += thirdSyllableNum;
								// System.out.println(syllableCount);
							}
						}

					} else if ((syllableCount + secondSyllableNum) > line2) {
						// System.out.println("failed 7 check");
						System.out.println("This is not a haiku");
						return false;
					} else {
						syllableCount += secondSyllableNum;
						// System.out.println(syllableCount);
					}

				}

			} else if ((syllableCount + firstSyllableNum) > line1) {
				// System.out.println("failed line1 test");
				System.out.println("This is not a haiku");
				return false;
			} else {
				syllableCount += firstSyllableNum;
				// System.out.println(syllableCount);

			}
		}

		System.out.println("This is not a haiku");
		return false;
	}

	public static String printer(int i, int j, int r, String[] words) {
		String[] formated = new String[words.length];
		int k = 0;

		for (k = 0; k <= i-1 ; k++) {
			// System.out.print(words[k] + " ");
			formated[k] = words[k];

		}
		formated[i-1] = words[i-1] + "\n\n";

		for (k =k; k <= j-1 ; k++) {
			// System.out.print(words[k] + " ");
			formated[k] = words[k];
		}
		formated[j-1] = words[j-1] + "\n\n";

		for (k = k; k <= r-1 ; k++) {
			// System.out.print(words[k] + " ");
			formated[k] = words[k];

		}

		for (String temp : formated) {
			System.out.print(temp + " ");
		}

		return formated.toString();
	}
}

package net.twaiku;

public class readSyl {

	public static void main(String[] args) {
		String H = "Shoreline";
		countSyllables(H);

	}

	protected static int countSyllables(String word) {
		String input = word.toLowerCase();
		int syllables = 0, numOfEInTheEnd = 0;

		int i = input.length() - 1;
		// count all the e's in the end
		while (i >= 0 && input.charAt(i) == 'e') {
			i--;
			numOfEInTheEnd++;
		}

		if (numOfEInTheEnd == 1) {
			syllables = 1;
		}

		boolean preVowel = false;
		while (i >= 0) {
			if (isVowel(input.charAt(i))) {
				if (!preVowel) {
					syllables++;
					preVowel = true;
				}
			} else {
				preVowel = false;
			}
			i--;
		}
		System.out.println(syllables);
		return syllables;
	}

	public static boolean isVowel(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'y') {
			return true;
		}
		return false;
	}
}

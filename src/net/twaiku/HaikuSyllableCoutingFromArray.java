package net.twaiku;

public class HaikuSyllableCoutingFromArray {

	public static void main(String[] args) {

		int[] arr = { 1, 1, 3, 3, 2, 1, 1, 1, 1, 3 };
		int i = 0;

		int sc = 0;
		int line1 = 5;
		int line2 = 12;
		int line3 = 17;

		// Starting point
		for (i = 0; sc <= 17; i++) {
			if (sc == line1) {

				System.out.println("i'm here");
				for (int j = i; sc <= 17; j++) {
					if (sc == line2) {

						System.out.println("Now I'm Here");
						for (int r = j; sc <= 17; r++) {
							if (sc == line3) {
								System.out.println("We FUCKING DID IT");
								//sc = 18 is a break statement to step all the way out
								sc = 18;
							} else if ((sc + arr[r]) > line3) {
								System.out.println("failed line 3 test");
							} else {
								sc += arr[r];
								System.out.println(sc);
							}
						}

					} else if ((sc + arr[j]) > line2) {
						System.out.println("failed line2 test");
					} else {
						sc += arr[j];
						System.out.println(sc);
					}

				}

			} else if ((sc + arr[i]) > line1) {
				System.out.println("failed line1 test");
			} else {
				sc += arr[i];
				System.out.println(sc);

			}
		}
	}
}
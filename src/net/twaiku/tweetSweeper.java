package net.twaiku;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

import net.twaiku.cmu.CmuDictionary;
import net.twaiku.rhymer.Rhymer;
import net.twaiku.rhymer.RhymeResult;

public class tweetSweeper {
	
	private static final String[] tensNames = {
		    "",
		    " ten",
		    " twenty",
		    " thirty",
		    " forty",
		    " fifty",
		    " sixty",
		    " seventy",
		    " eighty",
		    " ninety"
		  };

		  private static final String[] numNames = {
		    "",
		    " one",
		    " two",
		    " three",
		    " four",
		    " five",
		    " six",
		    " seven",
		    " eight",
		    " nine",
		    " ten",
		    " eleven",
		    " twelve",
		    " thirteen",
		    " fourteen",
		    " fifteen",
		    " sixteen",
		    " seventeen",
		    " eighteen",
		    " nineteen"
		  };

	public static String[] tweets(String tweets) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		
		

		if (tweets.contains("http")) {
			
			return list.toArray(new String[list.size()]);
			
		}
		for (String word : tweets.split(("[^a-zA-Z'@]"))) {
			if(!word.equals("")) {
				list.add(word);
			}
		}

		while ((list.get(0)).contains("@")) {
			
			list.remove(0);
		}
		while (list.get(list.size() - 1).contains("@") || list.get(list.size() - 1).contains("http")) {
			list.remove(list.get(list.size() - 1));
		}

		if (list.contains("@") || list.contains("http")) {
			list.clear();
		}
		return list.toArray(new String[list.size()]);
	}

	public static ArrayList<Integer> wordCheck(ArrayList<String> list) throws IOException {
		ArrayList<Integer> numList = new ArrayList<Integer>();
		Rhymer rhymer = CmuDictionary.loadRhymer();
		for (int i = 0; i < list.size(); i++) {
			numList.add(rhymer.getSyllables(list.get(i)));
			if (numList.get(i).equals(0)) {
				numList.remove(i);
			}
		}
		return numList;
	}

	public static int countNum(ArrayList<Integer> numList) {
		int totalCount = 0;

		for (int numCounter : numList) {
			totalCount = numCounter + totalCount;
			
		}
		return totalCount;

	}
	
	private tweetSweeper() {}

	  private static String convertLessThanOneThousand(int number) {
	    String soFar;

	    if (number % 100 < 20){
	      soFar = numNames[number % 100];
	      number /= 100;
	    }
	    else {
	      soFar = numNames[number % 10];
	      number /= 10;

	      soFar = tensNames[number % 10] + soFar;
	      number /= 10;
	    }
	    if (number == 0) return soFar;
	    return numNames[number] + " hundred" + soFar;
	  }
	  
	  public static String convert(long number) {
		    // 0 to 999 999 999 999
		    if (number == 0) { return "zero"; }

		    String snumber = Long.toString(number);

		    // pad with "0"
		    String mask = "000000000000";
		    DecimalFormat df = new DecimalFormat(mask);
		    snumber = df.format(number);

		    // XXXnnnnnnnnn
		    int billions = Integer.parseInt(snumber.substring(0,3));
		    // nnnXXXnnnnnn
		    int millions  = Integer.parseInt(snumber.substring(3,6));
		    // nnnnnnXXXnnn
		    int hundredThousands = Integer.parseInt(snumber.substring(6,9));
		    // nnnnnnnnnXXX
		    int thousands = Integer.parseInt(snumber.substring(9,12));

		    String tradBillions;
		    switch (billions) {
		    case 0:
		      tradBillions = "";
		      break;
		    case 1 :
		      tradBillions = convertLessThanOneThousand(billions)
		      + " billion ";
		      break;
		    default :
		      tradBillions = convertLessThanOneThousand(billions)
		      + " billion ";
		    }
		    String result =  tradBillions;

		    String tradMillions;
		    switch (millions) {
		    case 0:
		      tradMillions = "";
		      break;
		    case 1 :
		      tradMillions = convertLessThanOneThousand(millions)
		         + " million ";
		      break;
		    default :
		      tradMillions = convertLessThanOneThousand(millions)
		         + " million ";
		    }
		    result =  result + tradMillions;

		    String tradHundredThousands;
		    switch (hundredThousands) {
		    case 0:
		      tradHundredThousands = "";
		      break;
		    case 1 :
		      tradHundredThousands = "one thousand ";
		      break;
		    default :
		      tradHundredThousands = convertLessThanOneThousand(hundredThousands)
		         + " thousand ";
		    }
		    result =  result + tradHundredThousands;

		    String tradThousand;
		    tradThousand = convertLessThanOneThousand(thousands);
		    result =  result + tradThousand;

		    // remove extra spaces!
		    return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		  }

}

package net.twaiku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.twaiku.bot.TwaikuBotController;
import net.twaiku.cmu.CmuDictionary;
import net.twaiku.rhymer.Rhymer;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class SearchUserTimeLine {

	public static boolean searchMethod(String userName) {

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

		configurationBuilder.setTweetModeExtended(true);
		configurationBuilder.setOAuthConsumerKey(Credentials.CLIENT_ID)
				.setOAuthConsumerSecret(Credentials.CLIENT_SECRET).setOAuthAccessToken(Credentials.AccessToken_ID)
				.setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);

		Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();

		try {
			List<Status> statuses = new ArrayList<Status>();
			String user;
			if (userName.equalsIgnoreCase("twaikuGC")) {
				return false;
			}
			if (userName.length() >= 1) {
				user = userName;
				Paging page = new Paging(1, 100);
				statuses.addAll(twitter.getUserTimeline(user, page));
			} else {
				user = twitter.verifyCredentials().getScreenName();
				statuses.addAll(twitter.getUserTimeline(user));
			}
			// System.out.println("Showing @" + user + "'s user timeline.");
			for (Status status : statuses) {
				// goes through each status and checks to see if it contains a haiku
				onStatus(status);
				// System.out.println("@" + status.getUser().getScreenName() + " - " +
				// status.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to get timeline: " + e.getMessage());
			return false;
		}
		return true;
	}

	private static void onStatus(Status status) {

		
		if (status.getLang().toString().equals("en") && (status.getHashtagEntities().length == 0)
				&& (status.isRetweet() == false) && status.getInReplyToStatusId() <= 0
				&& status.getText().length() < 255 && status.getUser().getName() != "TwaikuGC") {

			try {
				Rhymer rhymer = CmuDictionary.loadRhymer();
				String[] wordsInTweet = tweetSweeper.sanitizeRawTweet(status.getText());
				if (wordsInTweet.length > 1) {
					int[] indexNum = (HaikuDetector.ifHaiku(wordsInTweet, rhymer));

					if (indexNum[0] == 1) {
						HomeController.updateTwaikuDatabaseTweets((status.getId()), status.getUser().getScreenName(),
								HomeController.regexChecker(HaikuDetector.formatToHaiku(indexNum[1], indexNum[2],
										indexNum[3], tweetSweeper.sanitizeRawTweet(status.getText()))),
								status.getUser().getProfileImageURLHttps(),
								HomeController.regexChecker(status.getUser().getName()));

						TwaikuBotController.botTweetPost(status,
								HomeController.replaceBreaks(HomeController.getLastTweet(status.getId())));
						;

						System.out.println("@" + status.getUser().getScreenName() + " Tweet LENGTH : "
								+ status.getText().length() + " - " + status.getText() + " - GETID:" + status.getId());
					}
				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

	}

}

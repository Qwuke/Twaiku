package net.twaiku.bot;

import net.twaiku.Credentials;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwaikuBotController {

	public static void botTweetPost(Status status) {
		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setOAuthConsumerKey(Credentials.CLIENT_ID).setOAuthConsumerSecret(Credentials.CLIENT_SECRET)
				.setOAuthAccessToken(Credentials.AccessToken_ID)
				.setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);
		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter twitter = tf.getInstance();

		try {

			status = twitter.updateStatus("TEST UPDATE From JAVA NEW Anothertwo");
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
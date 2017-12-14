package net.twaiku.bot;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import net.twaiku.Credentials;
import net.twaiku.util.HibernateUtil;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwaikuBotController {
	String message = "";
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public static void botTweetPost(Status status, String message) {
		ConfigurationBuilder cf = new ConfigurationBuilder();
		cf.setOAuthConsumerKey(Credentials.CLIENT_ID).setOAuthConsumerSecret(Credentials.CLIENT_SECRET)
				.setOAuthAccessToken(Credentials.AccessToken_ID)
				.setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);
		TwitterFactory tf = new TwitterFactory(cf.build());
		Twitter twitter = tf.getInstance();
		
		
		
		try {

			status = twitter.updateStatus(message);
			System.out.println("Successfully updated the status to [" + message + "].");
		} catch (TwitterException e) {
			
			e.printStackTrace();
		}

	}
}
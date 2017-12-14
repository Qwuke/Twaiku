package net.twaiku;

import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
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
	            List<Status> statuses;
	            String user;
	            if (userName.length() >= 1) {
	                user = userName;
	                statuses = twitter.getUserTimeline(user);
	            } else {
	                user = twitter.verifyCredentials().getScreenName();
	                statuses = twitter.getUserTimeline();
	            }
	            System.out.println("Showing @" + user + "'s user timeline.");
	            for (Status status : statuses) {
	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Failed to get timeline: " + e.getMessage());
	           return false;
	        }
		 return true;
	    }
	}


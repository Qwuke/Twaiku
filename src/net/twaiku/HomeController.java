package net.twaiku;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import net.twaiku.bot.TwaikuBotController;

@Controller
public class HomeController {
	public int tweetCount;
	public int postCount;

	@RequestMapping({ "/index", "/" })
	public String index() {
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		// ConfigurationBuilder cf = new ConfigurationBuilder();
		configurationBuilder.setTweetModeExtended(true);
		configurationBuilder.setOAuthConsumerKey(Credentials.CLIENT_ID)
				.setOAuthConsumerSecret(Credentials.CLIENT_SECRET).setOAuthAccessToken(Credentials.AccessToken_ID)
				.setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);

		// BOT post credentials must be different on a different configurationBUilder
		// cf.setOAuthConsumerKey(Credentials.CLIENT_ID).setOAuthConsumerSecret(Credentials.CLIENT_SECRET)
		// .setOAuthAccessToken(Credentials.AccessToken_ID)
		// .setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);
		// TwitterFactory tf = new TwitterFactory(cf.build());
		// Twitter twitter = tf.getInstance();
		// end of tweet bot test post

		TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
		// TwitterStream twitterStream = new TwitterStreamFactory().getInstance();

		StatusListener listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {
				// testing bot
				// if (postCount <= 0) {
				// try {
				// postCount++;
				// status = twitter.updateStatus("TEST UPDATE From JAVA post count Test");
				// System.out.println("Successfully updated the status to [" + status.getText()
				// + "].");
				// } catch (TwitterException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				// }

				// testing bot
				
				if (postCount <= 0) {
					postCount ++;
					TwaikuBotController.botTweetPost(status);					
				}

				if (status.getLang().toString().equals("en") && (status.getHashtagEntities().length == 0)
						&& (status.isRetweet() == false)) {
					tweetCount++;

					System.out.println("Tweet TXT ONLY " + status.getText());
					System.out.println("@" + status.getUser().getScreenName() + " Tweet LENGTH : "
							+ status.getText().length() + " - " + status.getText() + " - GETID:" + status.getId()
							+ " TweetCount" + tweetCount);

				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// System.out.println("Got a status deletion notice id:" +
				// statusDeletionNotice.getStatusId());
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:"
				// + upToStatusId);
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// System.out.println("Got stall warning:" + warning);
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}
		};
		twitterStream.addListener(listener);
		twitterStream.sample();
		// FilterQuery fq = new FilterQuery();
		// fq.language("en");
		// twitterStream.filter();
		return "index";
	}

}

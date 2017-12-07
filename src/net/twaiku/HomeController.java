package net.twaiku;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import net.twaiku.bot.TwaikuBotController;
import net.twaiku.cmu.CmuDictionary;
import net.twaiku.dto.TwaikuDAO;
import net.twaiku.dto.TwaikuDTO;
import net.twaiku.rhymer.Rhymer;
import net.twaiku.util.HibernateUtil;

@Controller
public class HomeController {
	public int tweetCount;
	public int postCount;
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	public String updateTwaikuDatabaseTweets(long longTweetId, String tweetName, String tweetString) {

		//Configuration config = new Configuration().configure("hibernate.cfg.xml");
		//SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TwaikuDTO newTwaikuDTO = new TwaikuDTO();

		newTwaikuDTO.setTweetID(longTweetId);
		newTwaikuDTO.setUserName(tweetName);
		newTwaikuDTO.setTweetString(tweetString);
		

		session.save(newTwaikuDTO);
		tx.commit();
		session.close();
		

		return "";
	}

	@RequestMapping({ "/index", "/" })
	public String index() throws IOException {

		/*
		 * ArrayList<String> words = new ArrayList<String>(); words.add("Hi");
		 * words.add("hello"); words.add("BYE");
		 */
		
		//working code below
//		Rhymer rhymer = CmuDictionary.loadRhymer();
//		System.out.println(rhymer.getSyllables("kkkkkkkk"));
//		System.out.println(rhymer.getSyllables("hello"));
//		System.out.println(rhymer.getSyllables("refrigerator"));
//		System.out.println(rhymer.getSyllables("shoreline"));

		/*
		 * for (RhymeResult result : results) { System.out.println("Results for " +
		 * result.variantNumber + ":");
		 * 
		 * System.out.println("  Strict matches:"); System.out.println("    " +
		 * Arrays.toString(result.strictRhymes)); System.out.println();
		 * System.out.println("  One-syllable matches:"); System.out.println("    " +
		 * Arrays.toString(result.oneSyllableRhymes)); System.out.println();
		 * System.out.println("  Two-syllable matches:"); System.out.println("    " +
		 * Arrays.toString(result.twoSyllableRhymes)); System.out.println();
		 * System.out.println("  Three-syllable matches:"); System.out.println("    " +
		 * Arrays.toString(result.threeSyllableRhymes)); }
		 */
		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

		configurationBuilder.setTweetModeExtended(true);
		configurationBuilder.setOAuthConsumerKey(Credentials.CLIENT_ID)
				.setOAuthConsumerSecret(Credentials.CLIENT_SECRET).setOAuthAccessToken(Credentials.AccessToken_ID)
				.setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);

		TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();

		StatusListener listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {

				

				if (postCount == 0) {
					postCount++;
					//TwaikuBotController.botTweetPost(status);
				}
			//	if (tweetCount <= 1) {
					if ( status.getLang().toString().equals("en") && (status.getHashtagEntities().length == 0)
							&& (status.isRetweet() == false) && status.getText().length() < 255) {
						tweetCount++;
						
						
						updateTwaikuDatabaseTweets(status.getId(),  status.getUser().getScreenName(), regexChecker(status.getText()));
					
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
	public static String regexChecker(String str2Check) {
        Pattern checkRegex = Pattern.compile("[a-zA-Z0-9\\t\\n ./<>?;:\"'`!@#$%^&*()\\[\\]{}_+=|\\\\-]");
        Matcher regexMatcher = checkRegex.matcher(str2Check);
        str2Check = "";
        while (regexMatcher.find()) {
            if(regexMatcher.group().length() !=0){
                //System.out.print(regexMatcher.group());
                str2Check+=regexMatcher.group();
        
        }
        }
        return str2Check;
    }

}

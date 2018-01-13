package net.twaiku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.twaiku.bot.TwaikuBotController;
import net.twaiku.cmu.CmuDictionary;
import net.twaiku.dto.TwaikuDAO;
import net.twaiku.dto.TwaikuDTO;

import net.twaiku.rhymer.Rhymer;
import net.twaiku.rhymer.RhymeResult;

import net.twaiku.util.HibernateUtil;

@Controller
public class HomeController {
	public String words = "Green one speckled legs, Hop on logs and 42 Splash in cool water.";

	public int tweetCount;
	public int postCount;
	public static String timeLineSearchName;
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public static String updateTwaikuDatabaseTweets(long longTweetId, String tweetName, String tweetString,
			String profileImageLink, String userDisplayName) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		TwaikuDTO newTwaikuDTO = new TwaikuDTO();

		newTwaikuDTO.setTweetID(longTweetId);
		newTwaikuDTO.setUserName(tweetName);
		newTwaikuDTO.setTweetString(tweetString);
		newTwaikuDTO.setProfileImageLink(profileImageLink);
		newTwaikuDTO.setUserDisplayName(userDisplayName);

		session.save(newTwaikuDTO);
		tx.commit();
		session.close();

		return "";
	}

	@RequestMapping("user")
	public String user() throws IOException {
		Rhymer rhymer = CmuDictionary.loadRhymer();

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

		configurationBuilder.setTweetModeExtended(true);
		configurationBuilder.setOAuthConsumerKey(Credentials.CLIENT_ID)
				.setOAuthConsumerSecret(Credentials.CLIENT_SECRET).setOAuthAccessToken(Credentials.AccessToken_ID)
				.setOAuthAccessTokenSecret(Credentials.AccessTokenSecret_ID);

		TwitterStream twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
		StatusListener listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {

				// System.out.println(status.getUser().getProfileImageURLHttps());
				// System.out.println(status.getUser().getName());

				if (status.getLang().toString().equals("en") && (status.getHashtagEntities().length == 0)
						&& (status.isRetweet() == false) && status.getInReplyToStatusId() <= 0 && status.getText().length() < 255 && status.getUser().getName()!= "TwaikuGC") {
					tweetCount++;
					try {

						String[] wordsInTweet = tweetSweeper.sanitizeRawTweet(status.getText());
						if (wordsInTweet.length > 1) {
							int[] indexNum = (HaikuDetector.ifHaiku(wordsInTweet, rhymer));

							if (indexNum[0] == 1) {
								updateTwaikuDatabaseTweets((status.getId()), status.getUser().getScreenName(),
										regexChecker(HaikuDetector.formatToHaiku(indexNum[1], indexNum[2], indexNum[3],
												tweetSweeper.sanitizeRawTweet(status.getText()))),
										status.getUser().getProfileImageURLHttps(),
										regexChecker(status.getUser().getName()));

								TwaikuBotController.botTweetPost(status, replaceBreaks(getLastTweet(status.getId())));
								;

								System.out.println("@" + status.getUser().getScreenName() + " Tweet LENGTH : "
										+ status.getText().length() + " - " + status.getText() + " - GETID:"
										+ status.getId() + " TweetCount" + tweetCount);
							}
						}

					} catch (IOException e) {

						e.printStackTrace();
					}

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

		return "user";
	}

	public static String regexChecker(String str2Check) {
		Pattern checkRegex = Pattern.compile("[a-zA-Z0-9\\t\\n ./<>?;:\"'`!@#$%^&*()\\[\\]{}_+=|\\\\-]");
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		str2Check = "";
		while (regexMatcher.find()) {
			if (regexMatcher.group().length() != 0) {

				str2Check += regexMatcher.group();

			}
		}
		return str2Check;
	}

	public String eachWord(String[] words) {
		String last = "";
		for (String s : words) {
			last += (s + ",");
		}
		return last;
	}

	public String stringArrayToString(String[] words) {
		String arrayToString = "";
		for (String temp : words) {
			arrayToString += temp + " ";
		}

		return arrayToString;

	}

	@RequestMapping({ "/index", "/" })
	public ModelAndView index(Model model) throws IOException {

		ArrayList<TwaikuDTO> list = getAllTweets();
		// Collections.reverse(list);

		return new ModelAndView("index", "tweetTable", list);
	}

	@RequestMapping("/about")
	public String about(Model model) throws IOException {

		return "about";
	}

	@RequestMapping(value = "Search" , method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("Search") String searchName, Model model) throws IOException {

		ArrayList<TwaikuDTO> list = getAllTweetsUserSearch(searchName);
		if ((SearchUserTimeLine.searchMethod(searchName) == false)) {
			list.clear();
		}

		if (list.size() == 0) {
			list.clear();
			list.addAll(get404Message());
			return new ModelAndView("search404", "search404", list);

		} else {
			return new ModelAndView("search", "search", list);
		}
	}

	public ArrayList<TwaikuDTO> get404Message() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(TwaikuDTO.class);
		crit.add(Restrictions.like("UserName", "Twaiku404"));

		ArrayList<TwaikuDTO> list = (ArrayList<TwaikuDTO>) crit.list();

		tx.commit();
		session.close();
		return list;

	}

	public ArrayList<TwaikuDTO> getAllTweetsUserSearch(String userSearchName) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(TwaikuDTO.class);
		crit.add(Restrictions.like("UserName", userSearchName));

		ArrayList<TwaikuDTO> list = (ArrayList<TwaikuDTO>) crit.list();

		Collections.reverse(list);

		tx.commit();
		session.close();
		return list;
	}

	private ArrayList<TwaikuDTO> getAllTweets() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria crit = session.createCriteria(TwaikuDTO.class);

		ArrayList<TwaikuDTO> list = (ArrayList<TwaikuDTO>) crit.list();
		Collections.reverse(list);

		ArrayList<TwaikuDTO> trueList = new ArrayList<TwaikuDTO>();
		for (int i = 0; i <= 14; i++)
			trueList.add(i, list.get(i));
		tx.commit();
		session.close();
		return trueList;
	}

	public static String getLastTweet(Long tweetID) {

		String botTweetHaiku = "";
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		// Criteria crit = session.createCriteria(TwaikuDTO.class);

		Query q2 = session.createQuery("select TweetID , UserName,  TweetString from TwaikuDTO WHERE id =" + tweetID);
		List l1 = q2.list();
		Iterator i = l1.iterator();
		while (i.hasNext()) {
			Object[] obj = (Object[]) i.next();
			long id = (long) obj[0];
			String name = (String) obj[1];
			botTweetHaiku = (String) obj[2];

			System.out.println(id);
			System.out.println(name);
			System.out.println(botTweetHaiku);
		}

		session.flush();

		tx.commit();

		session.close();

		return botTweetHaiku;

	}

	public static String replaceBreaks(String replaceText) {
		replaceText = replaceText.replaceAll("<br>", "\n");

		return replaceText;
	}


}

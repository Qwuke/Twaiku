package net.twaiku.dto;

public class TwaikuDTO {
	
	private long TweetID;
	private String UserName;
	private String TweetString;

	public TwaikuDTO() {
		
	}

	public TwaikuDTO(long tweetID, String userName, String tweetString) {
		
		TweetID = tweetID;
		UserName = userName;
		TweetString = tweetString;
	}

	public long getTweetID() {
		return TweetID;
	}

	public void setTweetID(long longTweetId) {
		TweetID = longTweetId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getTweetString() {
		return TweetString;
	}

	public void setTweetString(String tweetString) {
		TweetString = tweetString;
	}
	
	
}

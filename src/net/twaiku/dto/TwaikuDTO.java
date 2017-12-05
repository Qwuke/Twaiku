package net.twaiku.dto;

public class TwaikuDTO {
	
	private int TweetID;
	private String UserName;
	private String TweetString;

	public TwaikuDTO() {
		
	}

	public TwaikuDTO(int tweetID, String userName, String tweetString) {
		
		TweetID = tweetID;
		UserName = userName;
		TweetString = tweetString;
	}

	public int getTweetID() {
		return TweetID;
	}

	public void setTweetID(int tweetID) {
		TweetID = tweetID;
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

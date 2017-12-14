package net.twaiku.dto;

public class TwaikuDTO {

	private long TweetID;
	private String UserName;
	private String TweetString;
	private String ProfileImageLink;
	private String UserDisplayName;

	public TwaikuDTO() {

	}

	public TwaikuDTO(long tweetID, String userName, String tweetString, String profileImageLink, String userDisplayName) {

		TweetID = tweetID;
		UserName = userName;
		TweetString = tweetString;
		ProfileImageLink = profileImageLink;
		UserDisplayName = userDisplayName;
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

	public String getProfileImageLink() {
		return ProfileImageLink;
	}

	public void setProfileImageLink(String profileImageLink) {
		ProfileImageLink = profileImageLink;
	}

	public String getUserDisplayName() {
		return UserDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		UserDisplayName = userDisplayName;
	}

}

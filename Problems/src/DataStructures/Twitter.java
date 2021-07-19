package DataStructures;

import java.util.*;

class Tweet {
  int userId;
  int tweetId;

  public Tweet(int userId, int tweetId) {
    this.userId = userId;
    this.tweetId = tweetId;
  }

  @Override
  public String toString() {
    return "userId: " + userId + "; tweetId: " + tweetId;
  }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
class Twitter {
  List<Tweet> tweets = new ArrayList<>();
  Map<Integer, Set<Integer>> map = new HashMap<>();

  /**
   * Compose a new tweet.
   */
  public void postTweet(int userId, int tweetId) {
    tweets.add(0, new Tweet(userId, tweetId));
  }

  /**
   * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
   */
  public List<Integer> getNewsFeed(int userId) {
    List<Integer> feed = new ArrayList<>();

    for (int i = 0; i < tweets.size() && feed.size() < 10; i++) {
      Tweet tweet = tweets.get(i);
      if (userId == tweet.userId || (map.containsKey(userId) && map.get(userId).contains(tweet.userId))) {
        feed.add(tweet.tweetId);
      }
    }

    return feed;
  }

  /**
   * Follower follows a followee. If the operation is invalid, it should be a no-op.
   */
  public void follow(int followerId, int followeeId) {
    Set<Integer> followees = map.getOrDefault(followerId, new HashSet<>());
    followees.add(followeeId);
    map.put(followerId, followees);
  }

  /**
   * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
   */
  public void unfollow(int followerId, int followeeId) {
    Set<Integer> followees = map.getOrDefault(followerId, new HashSet<>());
    followees.remove(followeeId);
    map.put(followerId, followees);
  }
}


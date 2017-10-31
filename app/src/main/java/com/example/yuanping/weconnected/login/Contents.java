package com.example.yuanping.weconnected.login;

/**
 * Created by yuanping on 10/28/17.
 */

public class Contents {
    public static final String APP_KEY = "2676180418";
    //默认回调页
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
    //用户信息
    public static String userUrl = "https://api.weibo.com/2/users/show.json";
    //最新微博
    public static String weiboUrl = "https://api.weibo.com/2/statuses/home_timeline.json";
    //粉丝列表
    public static String followersUrl = "https://api.weibo.com/2/friendships/followers.json";
    //关注列表
    public static String friendsUrl = "https://api.weibo.com/2/friendships/friends.json";
    //获取当前登录用户及其所关注（授权）用户的最新微博
    public static String home_timeline = "https://api.weibo.com/2/statuses/home_timeline.json";
    //获取用户发布的微博
    public static String user_timeline = "https://api.weibo.com/2/statuses/user_timeline.json";
    //批量获取互粉数
    public static String getFollowerUid = "https://api.weibo.com/2/friendships/followers/ids.json";
    //用户关注列表
    public static String friendsUrl_list  = "https://m.weibo.cn/p/second?containerid=1005055539916070_-_FOLLOWERS";
    public static String followersUrl_list = "https://m.weibo.cn/p/second?containerid=1005055539916070_-_FANS";
    public static String prefiendsUrl = "https://m.weibo.cn/p/second?containerid=100505";
    public static String tailfriendUrl = "_-_FOLLOWERS";
    public static String tailfollowersUrl = "_-_FANS";
    //根据uid获取用户的主页
    public static String prehomaPageUrl = "https://m.weibo.cn/u/";
    //测试使用
    public static String userUid = "5539916070";
    public static String userAccessToken = "2.00APvuCGSmyGvCe5795281ad0Z2ICY";
}

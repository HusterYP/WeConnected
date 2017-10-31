package com.example.yuanping.weconnected.login.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuanping.weconnected.R;
import com.example.yuanping.weconnected.login.Contents;
import com.example.yuanping.weconnected.login.com.sina.weibo.sdk.openapi.UsersAPI;
import com.example.yuanping.weconnected.login.com.sina.weibo.sdk.openapi.models.User;
import com.example.yuanping.weconnected.login.test.Test_one;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.yuanping.weconnected.login.com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.u;

public class UserInfo extends AppCompatActivity {

    private CollapsingToolbarLayout imageToolbar;
    private TextView userDescriptionUserInfo;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private RecyclerView recyclerActivityFour;
    private Oauth2AccessToken mAccessToken;
    private ImageView userImage;
    private Bitmap userBitmap;
    //获取用户信息回调
    private RequestListener requestListener = new RequestListener() {
        @Override
        public void onComplete(String s) {
            if (!TextUtils.isEmpty(s)) {
                final User user = User.parse(s);
                if (null != user) {
                    userDescriptionUserInfo.setText(user.description);
                    (tabLayout.getTabAt(0)).setText(user.friends_count+"关注");
                    (tabLayout.getTabAt(1)).setText(user.followers_count+"粉丝");
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                URL url = new URL(user.profile_image_url);
                                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                InputStream in = connection.getInputStream();
                                userBitmap = BitmapFactory.decodeStream(in);
                                handler.sendEmptyMessage(0);
                            } catch (java.io.IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        init();
    }

    //刷新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(null!=userBitmap){
                userImage.setImageBitmap(userBitmap);
            }
        }
    };

    private void init() {
        imageToolbar = (CollapsingToolbarLayout) findViewById(R.id.image_toolbar);
        userDescriptionUserInfo = (TextView) findViewById(R.id.user_description_user_info);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        recyclerActivityFour = (RecyclerView) findViewById(R.id.recycler_activity_four);
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
        userBitmap = null;
        userImage = (ImageView) findViewById(R.id.user_image_user_info);

        getUserInfo();
    }

    //获取用户信息
    public void getUserInfo() {
        UsersAPI usersAPI = new UsersAPI(UserInfo.this, Contents.APP_KEY, mAccessToken);
        long uid = Long.parseLong(Contents.userUid);
        usersAPI.show(uid, requestListener);
    }


}

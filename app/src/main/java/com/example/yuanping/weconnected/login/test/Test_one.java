package com.example.yuanping.weconnected.login.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yuanping.weconnected.R;
import com.example.yuanping.weconnected.login.Contents;
import com.example.yuanping.weconnected.login.com.sina.weibo.sdk.openapi.UsersAPI;
import com.example.yuanping.weconnected.login.com.sina.weibo.sdk.openapi.models.User;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import static com.example.yuanping.weconnected.login.com.sina.weibo.sdk.openapi.legacy.CommonAPI.CAPITAL.u;

public class Test_one extends AppCompatActivity implements View.OnClickListener {

    private Button userInfo;
    private Oauth2AccessToken mAccessToken;
    private RequestListener requestListener = new RequestListener() {
        @Override
        public void onComplete(String s) {
            if(!TextUtils.isEmpty(s)){
                Log.d("@HusterYP", String.valueOf(s));
                User user = User.parse(s);
                if(null!=user){
                    Log.d("@HusterYP", String.valueOf("User Info..."+user.profile_image_url));
                    Log.d("@HusterYP", String.valueOf(user.avatar_large));
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
        setContentView(R.layout.activity_test_one);
        initView();
    }

    private void initView() {
        userInfo = (Button) findViewById(R.id.user_info);
        userInfo.setOnClickListener(this);
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_info:
                getUserInfo();
                break;
        }
    }

    public void getUserInfo() {
        UsersAPI usersAPI = new UsersAPI(Test_one.this,Contents.APP_KEY,mAccessToken);
        long uid = Long.parseLong(Contents.userUid);
        usersAPI.show(uid,requestListener);
    }
}

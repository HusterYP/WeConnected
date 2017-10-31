/*package com.example.yuanping.weconnected.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yuanping.weconnected.R;
import com.example.yuanping.weconnected.login.main.UserInfo;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;


public class LogIn extends AppCompatActivity {

    private SsoHandler handler;
    private Oauth2AccessToken mAccessToken;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        init();
    }

    private void init() {
        mActivity = this;
        WbSdk.install(this, new AuthInfo(this, Contents.APP_KEY, Contents.REDIRECT_URL, Contents.SCOPE));
        //创建微博实例
        AuthInfo info = new AuthInfo
                (this, Contents.APP_KEY, Contents.REDIRECT_URL, Contents.SCOPE);
        handler = new SsoHandler(mActivity);
    }

    //授权按钮响应
    public void LogIn(View view) {
        handler.authorize(new SelfWbAuthListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //SSO授权
        if (null != handler) {
            handler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class SelfWbAuthListener implements WbAuthListener {

*//*  @Override
        public void onComplete(Bundle bundle) {
            mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
            if (mAccessToken.isSessionValid()) {
                AccessTokenKeeper.writeAccessToken(LogIn.this, mAccessToken);
                Toast.makeText(LogIn.this, String.valueOf("取消授权！"), Toast.LENGTH_SHORT).show();
            }else{
                String code = bundle.getString("code");
                Toast.makeText(LogIn.this,String.valueOf("Error code :"+code),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(LogIn.this, String.valueOf("授权失败！"), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(LogIn.this, String.valueOf("取消授权！"), Toast.LENGTH_SHORT).show();
        }*//*

        @Override
        public void onSuccess(final Oauth2AccessToken oauth2AccessToken) {
            mAccessToken = oauth2AccessToken;
            if (mAccessToken.isSessionValid()) {
                Toast.makeText(LogIn.this, String.valueOf("授权成功！"), Toast.LENGTH_SHORT).show();
                //保存AcccessToken
                AccessTokenKeeper.writeAccessToken(LogIn.this, mAccessToken);
                Intent intent = new Intent(LogIn.this,UserInfo.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void cancel() {
            Toast.makeText(LogIn.this, String.valueOf("取消授权！"), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(LogIn.this, String.valueOf("授权失败！"), Toast.LENGTH_SHORT).show();
        }
    }
}*/
package com.example.yuanping.weconnected.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.yuanping.weconnected.R;
import com.example.yuanping.weconnected.login.test.Test_one;
import com.example.yuanping.weconnected.login.user.UserInfo;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LogIn extends AppCompatActivity {

    private SsoHandler handler;
    private Oauth2AccessToken mAccessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        init();
    }

    private void init() {
        WbSdk.install(this, new AuthInfo(this, Contents.APP_KEY, Contents.REDIRECT_URL, Contents.SCOPE));
        //创建微博实例
        handler = new SsoHandler(this);
    }

    //授权按钮响应
    public void LogIn(View view) {
        handler.authorizeClientSso(new SelfWbAuthListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //SSO授权
        if (null != handler) {
            handler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken oauth2AccessToken) {
            mAccessToken = oauth2AccessToken;
            if (mAccessToken.isSessionValid()) {
                Toast.makeText(LogIn.this, String.valueOf("授权成功！"), Toast.LENGTH_SHORT).show();
                //保存AcccessToken
                AccessTokenKeeper.writeAccessToken(LogIn.this, mAccessToken);
                Intent intent = new Intent(LogIn.this,UserInfo.class);
//                Intent intent = new Intent(LogIn.this, Test_one.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void cancel() {
            Toast.makeText(LogIn.this, String.valueOf("取消授权！"), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(LogIn.this, String.valueOf("授权失败！"), Toast.LENGTH_SHORT).show();
        }
    }
}

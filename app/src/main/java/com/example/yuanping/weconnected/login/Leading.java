package com.example.yuanping.weconnected.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.yuanping.weconnected.R;

import java.util.Timer;
import java.util.TimerTask;

public class Leading extends AppCompatActivity {

    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent(Leading.this,LogIn.class);
//            Intent intent = new Intent(Leading.this,UserInfo.class);
            startActivity(intent);
            finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leading);
        timer.schedule(timerTask,0);//3s
    }
}

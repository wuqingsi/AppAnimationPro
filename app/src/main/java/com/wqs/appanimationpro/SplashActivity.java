package com.wqs.appanimationpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private TextView mTvTimer;
    private CustomCountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVideoView = (FullScreenVideoView)findViewById(R.id.vv_play);
        mTvTimer = (TextView)findViewById(R.id.tv_splash_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });

        //加载视屏文件路径
        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +
                File.separator + R.raw.splash));

        //准备播放回调
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        //播放结束回调
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();//循环播放
            }
        });

        timer = new CustomCountDownTimer(3, new CustomCountDownTimer.ICountDownHandler() {
            @Override
            public void onTicker(int time) {
                mTvTimer.setText(time + "秒");
            }

            @Override
            public void onFinish() {
                //mTvTimer.setText("跳过");
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}

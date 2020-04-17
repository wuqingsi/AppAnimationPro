package com.wqs.appanimationpro;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import java.io.File;

public class SplashActivity extends AppCompatActivity {

    private VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVideoView = (FullScreenVideoView)findViewById(R.id.vv_play);

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
    }
}

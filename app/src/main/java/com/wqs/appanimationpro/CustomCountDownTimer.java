package com.wqs.appanimationpro;


import android.os.Handler;

public class CustomCountDownTimer implements Runnable {
    private final ICountDownHandler conntDownHandler;
    private int time;
    private int countDowntime;

    private final Handler handler;
    private boolean isRun;


    //1、实时回调这个时候的剩余时间，观察者设计模式
    //2、支持传入总时间
    //3、没过一秒，总时间-1
    //4、总时间倒计时为0时，要回调完成状态

    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler){
        handler = new Handler();
        this.time = time;
        this.countDowntime = time;
        this.conntDownHandler = countDownHandler;
    }

    @Override
    public void run() {
        if (isRun){
            if (conntDownHandler != null){
                conntDownHandler.onTicker(countDowntime);
            }
            if (countDowntime == 0){
                cancel();
                conntDownHandler.onFinish();
            }else{
                countDowntime = time--;
            }
            handler.postDelayed(this, 1000);
        }

    }
    
    //开启线程，倒计时
    public void start(){
        isRun = true;
        handler.post(this);
    }
    //跳出循环，终止
    public void cancel(){
        isRun = false;
        handler.removeCallbacks(this);
    }
    //回调接口
    public interface ICountDownHandler{
        void onTicker(int time);
        void onFinish();
    }
}

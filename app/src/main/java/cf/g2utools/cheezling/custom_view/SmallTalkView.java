package cf.g2utools.cheezling.custom_view;

import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cf.g2utools.cheezling.rec_item.PeopleSayView;

/**
 * Created by mlyg2 on 2017-12-17.
 */

public class SmallTalkView extends RelativeLayout implements GestureDetector.OnGestureListener {
    static final String TAG = "SmallTalkView";
    Context context;
    public ArrayList<PeopleSayView> peoplesayPool;
    double scroll = 0;
    double distance;
    double slopeRatio;
    GestureDetectorCompat gestureDetectorCompat;

    public SmallTalkView(Context context) {
        super(context);
    }

    public SmallTalkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmallTalkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SmallTalkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setSlopeRatio(double slopeRatio){
        this.slopeRatio = -slopeRatio;
        reDrawing();
    }

    public void setDistance(double distance){
        this.distance = distance;
        reDrawing();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void init(double distance, double slopeRatio){
        this.distance = distance;
        this.slopeRatio = -slopeRatio;
        gestureDetectorCompat = new GestureDetectorCompat(context, this);
        peoplesayPool = new ArrayList<>();
        for(int i = 0; 7 > i; i++){
            PeopleSayView peopleSayView = new PeopleSayView(getContext(), i%2==0 ? PeopleSayView.TYPE_LEFT : PeopleSayView.TYPE_RIGHT);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(64,64,64,64);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            peopleSayView.setLayoutParams(layoutParams);
            peoplesayPool.add(peopleSayView);
            addView(peopleSayView);
        }
        notifyDatasetChanged();
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Log.d("accel", "" + accel);
                if (-0.001 <= accel && accel <= 0.001) accel = 0;
                if (accel != 0) {
                    setScroll(scroll + accel);
                    accel *= 0.9;
                }
                return false;
            }
        });
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        };
        new Timer().schedule(timerTask,0,1000/60);
    }
    double accel = 0;
//    int datasize = 10;
//    int maxScroll = 9;
    public ArrayList<String> data = new ArrayList<>();
    public void setScroll(double scroll){
        if(scroll < 0) scroll = 0; else if(scroll > data.size()-1) scroll = data.size()-1;
        this.scroll = scroll;
        reDrawing();
    }

    public void dataBound(PeopleSayView tar, int idx){
        if( 0 <= idx && idx < data.size()){
            tar.setVisibility(VISIBLE);
            tar.setSay(data.get(idx));
        }else tar.setVisibility(INVISIBLE);

    }

    public void notifyDatasetChanged(){
        int s = (int)scroll;
        for(int i = 0 ; 7 > i ; i++)
            dataBound(peoplesayPool.get(i),i-1 + s);
    }

    int prevScroll = 0;
    public void reDrawing(){
        int s = (int)scroll;
        int ds = prevScroll - s;
        Log.d("할로", "scroll :" + scroll + ", s : " + s +", ds" + ds);
        if(ds < 0){
            dataBound(peoplesayPool.get(6),s+5);
            PeopleSayView p = peoplesayPool.get(0);
            peoplesayPool.remove(0);
            peoplesayPool.add(6,p);
        } else if (ds > 0){
            dataBound(peoplesayPool.get(0),s);
            PeopleSayView p = peoplesayPool.get(6);
            peoplesayPool.remove(6);
            peoplesayPool.add(0,p);
        }
        for(int i = 0; 7 > i; i++){
            setOneViewProcess( peoplesayPool.get(i),1 - ((double)(i-1) + 1-(scroll - (double)s))/5.0);
        }
        prevScroll = s;
    }

    public void setOneViewProcess(PeopleSayView tar, double process){

        //위치 구하기
        double dx = (0.8-process)*distance*Math.cos(slopeRatio * Math.PI / 180);
        double dy = (0.8-process)*distance*Math.sin(slopeRatio * Math.PI / 180);

        //배율 지정하기
        double scale = process + 0.2;

        //투명 지정하기
        double alpha;
        if(process <= 0.8) alpha = process * 5 / 4;
        else alpha = -5 * process + 5;
        Log.d(TAG, "proccess:" + process + ", dx:" + dx + ",dy:"+dy + ",scale:"+scale + ",alpha:" + alpha);

        //Z-index지정하기
        int z = (int)(process * 10);

        //적용하기
        tar.setTranslationX(((float) dx));
        tar.setTranslationY(((float) dy));
        tar.setZ(z);
        tar.setScaleX(((float) scale));
        tar.setScaleY(((float) scale));
        tar.setAlpha(((float) alpha));
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG,"onDown : " + motionEvent);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG,"onShowPress : " + motionEvent);
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG,"onSingleTapUp : " + motionEvent);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG,"onScroll : " + motionEvent+", "+motionEvent1+", "+v+", "+v1);
        setScroll(scroll - v1*0.005);
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG,"onLongPress : " + motionEvent);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.d(TAG,"onFling : " + motionEvent+", "+motionEvent1+", "+v+", "+v1);
        accel = v1 * 0.0001;
        return false;
    }
}

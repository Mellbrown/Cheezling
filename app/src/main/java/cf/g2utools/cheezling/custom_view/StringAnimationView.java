package cf.g2utools.cheezling.custom_view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mlyg2 on 2017-12-17.
 */

public class StringAnimationView extends LinearLayout{

    String split[];
    int speed;
    int cntLine;

    int seek = 0;
    int charSeek = 0;

    ArrayList<TextView> textViews = new ArrayList<>();

    public StringAnimationView(Context context) {
        super(context);
    }

    public StringAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StringAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StringAnimationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void StartDottedSlideUp(String src, final int speed, final int cntLine, final boolean repate){
        init(src, speed, cntLine);
        final Timer timer[] = new Timer[1];
        final Handler handler[] = new Handler[1];

        handler[0] = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(message.what == 1) for(TextView textView : textViews) textView.setText("");
                if(charSeek <= split[seek].length())
                    textViews.get(seek%cntLine).setText(split[seek].substring(0,charSeek++));
                else{
                    //그냥 한칸 다음
                    seek++;
                    charSeek =0;
                    // 끝냤냐?
                    if(seek >= split.length){//초기화
                        timer[0].cancel();
                        timer[0] = new Timer();
                        seek = 0;
                        if(repate){
                            for(TextView textView : textViews) textView.setText("");
                            timer[0].schedule(new TimerTask() {public void run() {handler[0].sendEmptyMessage(0);}},0,speed);
                        }
                    }else if(seek%cntLine == 0){ //여러칸 채움
                        timer[0].cancel();
                        timer[0] = new Timer();
                        TranslateAnimation translateAnimation = new TranslateAnimation(
                                TranslateAnimation.RELATIVE_TO_SELF,0,
                                TranslateAnimation.RELATIVE_TO_SELF,0,
                                TranslateAnimation.RELATIVE_TO_SELF,0,
                                TranslateAnimation.RELATIVE_TO_SELF,1
                        );
                        translateAnimation.setStartOffset(300);
                        translateAnimation.setDuration(300);
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                handler[0].sendEmptyMessage(1);
                                timer[0].schedule(new TimerTask() {public void run() {handler[0].sendEmptyMessage(0);}},0,speed);
                            }
                        },600);
                        for(TextView textView : textViews) textView.startAnimation(translateAnimation);
                    }
                }
                return true;
            }
        });
        timer[0] = new Timer();
        timer[0].schedule(new TimerTask() {public void run() {handler[0].sendEmptyMessage(0);}},100,speed);
    }

    public void StartJumpupDown(String src, int speed, int cntLine){
        init(src, speed, cntLine);
    }

    private void AddLine(){
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView textView = new TextView(getContext());
        textView.setTextSize(22);
        textView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,8,8,8);
        textView.setLayoutParams(layoutParams);
        frameLayout.addView(textView);
        addView(frameLayout);
        textViews.add(textView);
    }

    private void init(String src, int speed, int cntLine){
        setOrientation(LinearLayout.VERTICAL);
        split = src.split("\n");
        this.speed = speed;
        this.cntLine = cntLine;
        removeAllViews();
        for(int i = 0 ; cntLine > i; i++) AddLine();
    }
}

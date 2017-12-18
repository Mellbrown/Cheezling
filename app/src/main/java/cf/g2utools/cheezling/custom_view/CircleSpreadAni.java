package cf.g2utools.cheezling.custom_view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.ImageViewCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import cf.g2utools.cheezling.R;

/**
 * Created by mlyg2 on 2017-12-17.
 */

public class CircleSpreadAni extends RelativeLayout{
    public interface CircleSpreadAniEndListener{ void onCircleSpreadAniEnd(); }

    CircleSpreadAniEndListener circleSpreadAniEndListener;
    ImageView imageView;
    public CircleSpreadAni(Context context) {
        super(context);
    }

    public CircleSpreadAni(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleSpreadAni(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleSpreadAni(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void start(Activity activity, final CircleSpreadAniEndListener circleSpreadAniEndListener){
        this.circleSpreadAniEndListener = circleSpreadAniEndListener;
        imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_circle_white_50dp);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
        activity.addContentView(this,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,100,1,100,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(700);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                if (circleSpreadAniEndListener != null) {
                    circleSpreadAniEndListener.onCircleSpreadAniEnd();
                    final Handler handler = new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {
                            CircleSpreadAni.this.setVisibility(GONE);
                            return false;
                        }
                    });
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(0);
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(timerTask,300);
                } else{
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
                    alphaAnimation.setDuration(300);
                    alphaAnimation.setFillAfter(true);
                    imageView.startAnimation(alphaAnimation);
                }
            }
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(scaleAnimation);
    }
}

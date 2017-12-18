package cf.g2utools.cheezling.custom_view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.activity.MainPages.HomePage;
import cf.g2utools.cheezling.activity.MainPages.NewPost;
import cf.g2utools.cheezling.activity.MainPages.NewSpeed;
import cf.g2utools.cheezling.activity.MainPages.Notification;
import cf.g2utools.cheezling.activity.MainPages.SettingPage;

/**
 * Created by mlyg2 on 2017-12-18.
 */

public class BottomMenu extends RelativeLayout implements View.OnClickListener {

    Button home;
    Button newSpeed;
    Button writing;
    Button notify;
    Button setting;

    public enum Here { home, newSpeed, notify, setting }
    Activity activity;
    Here here;

    public BottomMenu(Context context) {
        super(context);
    }

    public BottomMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void Init(Activity activity, Here here){
        LayoutInflater.from(getContext()).inflate(R.layout.v_bottom_menu,this);

        this.activity = activity;
        this.here = here;

        home = findViewById(R.id.home);
        newSpeed = findViewById(R.id.newSpeed);
        writing = findViewById(R.id.writing);
        notify = findViewById(R.id.notify);
        setting = findViewById(R.id.setting);

        home.setOnClickListener(this);
        newSpeed.setOnClickListener(this);
        writing.setOnClickListener(this);
        notify.setOnClickListener(this);
        setting.setOnClickListener(this);

        switch (here){
            case home: home.setBackgroundResource(R.color.colorBackground);break;
            case notify:notify.setBackgroundResource(R.color.colorBackground);break;
            case setting:setting.setBackgroundResource(R.color.colorBackground);break;
            case newSpeed:newSpeed.setBackgroundResource(R.color.colorBackground);break;
        }
    }

    static final int toLEFT = 0;
    static final int toRIGHT = 1;
    public void MoveT0(Class c, int to){
        activity.startActivity(new Intent(activity, c));
        switch (to){
            case toLEFT: activity.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); break;
            case toRIGHT: activity.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right); break;
        }
        activity.finish();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:{
                switch (here){
                    case home:break;
                    case newSpeed:MoveT0(HomePage.class,toLEFT);break;
                    case notify:MoveT0(HomePage.class,toLEFT);break;
                    case setting:MoveT0(HomePage.class,toLEFT);break;
                }
            }break;
            case R.id.newSpeed:{
                switch (here){
                    case home:MoveT0(NewSpeed.class,toRIGHT);break;
                    case newSpeed:break;
                    case notify:MoveT0(NewSpeed.class,toLEFT);break;
                    case setting:MoveT0(NewSpeed.class,toLEFT);break;
                }
            }break;
            case R.id.writing:{
                new CircleSpreadAni(activity).start(activity, new CircleSpreadAni.CircleSpreadAniEndListener() {
                    @Override
                    public void onCircleSpreadAniEnd() {
                        activity.startActivity(new Intent(activity, NewPost.class));
                        activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    }
                });
            }break;
            case R.id.notify:{
                switch (here){
                    case home:MoveT0(Notification.class,toRIGHT);break;
                    case newSpeed:MoveT0(Notification.class,toRIGHT);break;
                    case notify:break;
                    case setting:MoveT0(Notification.class,toLEFT);break;
                }
            }break;
            case R.id.setting:{
                switch (here){
                    case home:MoveT0(SettingPage.class,toRIGHT);break;
                    case newSpeed:MoveT0(SettingPage.class,toRIGHT);break;
                    case notify:MoveT0(SettingPage.class,toRIGHT);break;
                    case setting:break;
                }
            }break;
        }
    }
}
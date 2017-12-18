package cf.g2utools.cheezling;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import cf.g2utools.cheezling.activity.MainPages.HomePage;
import cf.g2utools.cheezling.activity.MainPages.NewSpeed;
import cf.g2utools.cheezling.activity.scen_start.StartPage;
import cf.g2utools.cheezling.custom_view.CircleSpreadAni;
import cf.g2utools.cheezling.custom_view.StringAnimationView;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.lab.LabMenu;
import cf.g2utools.cheezling.util.Listener.LoadingListener;
import cf.g2utools.cheezling.util.MyAccount;
/*
 * 무조건 기본적으로 깔려가는 액티비티
 * 데이터를 로드를 시작을 해주고
 * 가입 유무에 따라 화면을 나눠준다.
 */
public class Base extends Activity {

    StringAnimationView stringAnimationView;
    ImageView siba_wating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_base);

        siba_wating = findViewById(R.id.siba_wating);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(siba_wating);
        Glide.with(this).load(R.drawable.siba_dog_waiting).into(gifImage);

        stringAnimationView = findViewById(R.id.stringAnimationView);
        stringAnimationView.StartDottedSlideUp("서비스에 연결중이예요 . . .\n데이터 로딩중이예요 . . .\n잠시만 기다려 주세요 . . .",80,3,true);
        //데이터 동기화 시작
        new RemoteData(this, new LoadingListener() {
            @Override
            public void onLoad() {
                //가입 유무 체크 시작
                new MyAccount(Base.this, new MyAccount.IsExsistAccountListener() {
                    @Override //이미 가입한 사용자 인지?
                    public void onIsExsistAccount(final boolean result) {
                            new CircleSpreadAni(Base.this)
                                    .start(Base.this, new CircleSpreadAni.CircleSpreadAniEndListener() {
                                @Override
                                public void onCircleSpreadAniEnd() {
                                    startActivity(new Intent(Base.this, result ? HomePage.class:StartPage.class));
                                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                    finish();
                                }
                            });
                    }
                });
            }
        });


    }
}

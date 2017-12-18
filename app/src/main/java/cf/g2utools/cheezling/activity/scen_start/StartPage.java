package cf.g2utools.cheezling.activity.scen_start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.CircleSpreadAni;
import cf.g2utools.cheezling.custom_view.StringAnimationView;
import cf.g2utools.cheezling.data.RemoteData;

public class StartPage extends AppCompatActivity implements View.OnClickListener {

    Button btn_start;
    StringAnimationView stringAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_start_page);

        //뷰객체 로드
        btn_start = findViewById(R.id.start);
        stringAnimationView = findViewById(R.id.stringAnimationView);

        //뷰객체 이벤트 연결
        btn_start.setOnClickListener(this);
        stringAnimationView.StartDottedSlideUp(getResources().getString(R.string.start_page_intro),150,2,true);
    }

    @Override //뷰객체 이벤트
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:{ //시작 버튼
                new CircleSpreadAni(this).start(this, new CircleSpreadAni.CircleSpreadAniEndListener() {
                    @Override
                    public void onCircleSpreadAniEnd() {
                        startActivity(new Intent(StartPage.this,SignUpPage.class));
                        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                        finish();
                    }
                });
            }break;
        }
    }
}

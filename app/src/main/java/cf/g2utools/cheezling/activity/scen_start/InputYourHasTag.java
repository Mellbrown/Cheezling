package cf.g2utools.cheezling.activity.scen_start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.TypingHashTag;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.util.MyAccount;

public class InputYourHasTag extends AppCompatActivity implements View.OnClickListener {

    TypingHashTag typingHashTag;
    Button complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_input_your_has_tag);

        typingHashTag = new TypingHashTag(this);
        ((FrameLayout) findViewById(R.id.frame)).addView(typingHashTag);
        complete = findViewById(R.id.complete);
        complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MyAccount.myAccountData.pushHasTag(typingHashTag.getResult()); //자신의 프로필을 태그 지정
        MyAccount.apply();
        RemoteData.rootData.getHashTagesData().pushHasTag(typingHashTag.getResult()); //새로운 태그 목록 추가
        RemoteData.commit();

        startActivity(new Intent(this,SignUpComplete.class));
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyAccount.Withdraw(); //중단하면 지워야지
    }
}

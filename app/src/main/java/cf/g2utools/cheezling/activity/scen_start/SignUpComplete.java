package cf.g2utools.cheezling.activity.scen_start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.activity.MainPages.HomePage;
import cf.g2utools.cheezling.activity.MainPages.NewSpeed;
import cf.g2utools.cheezling.custom_view.CircleSpreadAni;
import cf.g2utools.cheezling.custom_view.StringAnimationView;

public class SignUpComplete extends AppCompatActivity implements View.OnClickListener {

    ImageView dogGif;
    StringAnimationView stringAni;
    Button btnAck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sign_up_complete);

        dogGif = findViewById(R.id.dog_gif);
        stringAni = findViewById(R.id.stringAni);
        btnAck = findViewById(R.id.btn_ack);

        GlideDrawableImageViewTarget gifImg = new GlideDrawableImageViewTarget(dogGif);
        Glide.with(this).load(R.drawable.siba_dog_waiting).into(gifImg);

        stringAni.StartDottedSlideUp("가입이 완료되었어요!\n멍멍\n야옹?",100,3,false);

        btnAck.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        new CircleSpreadAni(this).start(this, new CircleSpreadAni.CircleSpreadAniEndListener() {
            @Override
            public void onCircleSpreadAniEnd() {
                startActivity(new Intent(SignUpComplete.this,HomePage.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }
}

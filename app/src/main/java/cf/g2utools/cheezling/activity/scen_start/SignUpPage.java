package cf.g2utools.cheezling.activity.scen_start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.Timer;
import java.util.TimerTask;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.StringAnimationView;
import cf.g2utools.cheezling.util.MyAccount;

public class SignUpPage extends AppCompatActivity {

    ImageView siba_wating;
    StringAnimationView stringAnimationView;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sign_up_page);

        siba_wating = findViewById(R.id.siba_wating);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(siba_wating);
        Glide.with(this).load(R.drawable.siba_dog_waiting).into(gifImage);

        stringAnimationView = findViewById(R.id.stringAnimationView);
        stringAnimationView.StartDottedSlideUp(getResources().getString(R.string.create_account),40, 5,false);

        MyAccount.SignUp();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SignUpPage.this,InputYourHasTag.class));
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                finish();
            }
        } ;
        timer = new Timer();
        timer.schedule(timerTask,5000);
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
        MyAccount.Withdraw(); //중단하면 지워야지
    }
}

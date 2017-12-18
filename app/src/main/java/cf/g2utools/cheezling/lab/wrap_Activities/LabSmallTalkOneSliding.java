package cf.g2utools.cheezling.lab.wrap_Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.SmallTalkView;

public class LabSmallTalkOneSliding extends AppCompatActivity {

    SeekBar seekBar;
    SmallTalkView smallTalkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_lab_small_talk_one_sliding);

        seekBar = findViewById(R.id.seek);
        smallTalkView = new SmallTalkView(this);
        smallTalkView.init(1500,-75);
        ((FrameLayout) findViewById(R.id.frame)).addView(smallTalkView);

        seekBar.setMax(180);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("Seek" , "i"+i);
                smallTalkView.setSlopeRatio(i*1.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}

package cf.g2utools.cheezling.lab.wrap_Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.TypingHashTag;
import cf.g2utools.cheezling.data.RemoteData;

public class LabEditGlobalTages extends AppCompatActivity implements View.OnClickListener {

    static final String TAG = "LabEditGlobalTages";

    //UI 객체
    TypingHashTag typingHashTag;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_lab_edit_global_tages);

        typingHashTag = new TypingHashTag(this);
        ((FrameLayout) findViewById(R.id.frame)).addView(typingHashTag);
        apply = findViewById(R.id.apply);
        apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.apply:{
                Log.d(TAG,"result : " + typingHashTag.getResult().toArray());
                RemoteData.rootData.getHashTagesData().pushHasTag(typingHashTag.getResult());
                RemoteData.commit();
                finish();
            }break;
        }
    }


}

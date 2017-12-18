package cf.g2utools.cheezling.lab.wrap_Activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.CheezeSayView;
import cf.g2utools.cheezling.custom_view.ZoomLayout;

public class LabZoomLayout extends AppCompatActivity {

    //UI 객체
    ZoomLayout zoomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_lab_zoom_layout);

        //UI 객체 로드
        zoomLayout = findViewById(R.id.zoomLayout);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        params.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        params.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        zoomLayout.addView(new CheezeSayView(this),params);
    }
}

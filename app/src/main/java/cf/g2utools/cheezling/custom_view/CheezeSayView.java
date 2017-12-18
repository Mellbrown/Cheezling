package cf.g2utools.cheezling.custom_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import cf.g2utools.cheezling.R;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class CheezeSayView extends FrameLayout{

    TextView title;

    public CheezeSayView(@NonNull Context context) {
        super(context);
        init();
    }

    public CheezeSayView(@NonNull Context context, String text) {
        super(context);
        init();
        setText(text);
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.v_cheeze,this);
        title = findViewById(R.id.text);
    }

    public void setText(String text){
        title.setText(text);
    }
}

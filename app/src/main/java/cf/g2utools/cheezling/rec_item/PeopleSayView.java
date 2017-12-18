package cf.g2utools.cheezling.rec_item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

import cf.g2utools.cheezling.R;

/**
 * Created by mlyg2 on 2017-12-17.
 */

public class PeopleSayView extends FrameLayout{
    public static final int TYPE_LEFT = 0;
    public static final int TYPE_RIGHT = 1;

    CardView sayView;
    WebView webView;
    int layout_type;

    public PeopleSayView(@NonNull Context context, int layout_type) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
        switch (layout_type){
            case TYPE_LEFT :  addView(LayoutInflater.from(context).inflate(R.layout.p_people_say_1, this,false)); break;
            case TYPE_RIGHT : addView(LayoutInflater.from(context).inflate(R.layout.p_people_say_2, this,false)); break;
        }
        this.layout_type = layout_type;
        sayView = findViewById(R.id.say);
        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    public void setSay(String innerHTML){
        webView.loadData(innerHTML,"text/html","utf-8");
    }
}

package cf.g2utools.cheezling.rec_item;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.data.ThreaData;
import cf.g2utools.cheezling.util.Listener.LikeOrCommentListener;

/**
 * Created by mlyg2 on 2017-12-19.
 */


public class ThreaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    WebView webView;
    TextView nickname;
    TextView date;
    ImageButton btn_coment;
    TextView cnt_coment;
    TextView cnt_like;
    ImageButton btn_like;
    TextView hashtag;

    ThreaData threaData;
    int pos;

    public LikeOrCommentListener likeOrCommentListener;

    public ThreaViewHolder(View itemView) {
        super(itemView);

        webView = itemView.findViewById(R.id.webView);
        nickname = itemView.findViewById(R.id.nickname);
        date = itemView.findViewById(R.id.date);
        btn_coment = itemView.findViewById(R.id.btn_coment);
        cnt_coment = itemView.findViewById(R.id.cnt_coment);
        cnt_like = itemView.findViewById(R.id.cnt_like);
        btn_like = itemView.findViewById(R.id.btn_like);
        hashtag = itemView.findViewById(R.id.hashtag);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        btn_coment.setOnClickListener(this);
        btn_like.setOnClickListener(this);
    }

    public static ThreaViewHolder fatory(ViewGroup parent, LikeOrCommentListener likeOrCommentListener){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_threa_card,parent,false);
        ThreaViewHolder threaViewHolder = new ThreaViewHolder(view);
        threaViewHolder.likeOrCommentListener = likeOrCommentListener;
        return threaViewHolder;
    }

    public void setDataBound(int pos, ThreaData threaData){
        this.threaData = threaData;
        nickname.setText(threaData.nickname);
        date.setText(new SimpleDateFormat("yyMMdd HH:mm:ss").format(new Date(threaData.timeStamp)));

        webView.loadData(threaData.innerText,"text/html", "utf-8");

        cnt_coment.setText(String.format("%02d",threaData.coment));
        cnt_like.setText(String.format("%02d",threaData.like));
        String s = "";
        for(String t : threaData.getTag())
            s += "#"+t+" ";
        hashtag.setText(s);

        this.pos = pos;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_coment: {

            }break;
            case R.id.btn_like: {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1,2,1,2,ScaleAnimation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(500);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1,0);
                scaleAnimation.setDuration(500);
                btn_like.setAnimation(scaleAnimation);
                btn_like.setAnimation(alphaAnimation);
                RemoteData.rootData.getThreadesData().getThreades().get(((int) threaData.threa_id)).like++;
                RemoteData.commit();
            }break;
        }
    }
}

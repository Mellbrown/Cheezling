package cf.g2utools.cheezling.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import cf.g2utools.cheezling.Base;
import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.activity.MainPages.SettingPage;
import cf.g2utools.cheezling.custom_view.CircleSpreadAni;
import cf.g2utools.cheezling.util.Listener.ConfirmListener;
import cf.g2utools.cheezling.util.MyAccount;

/**
 * Created by mlyg2 on 2017-12-18.
 */

public class SeriouslyWithdrawDialog extends Dialog{

    public SeriouslyWithdrawDialog(@NonNull Context context, final Activity activity) {
        super(context);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.d_seriously_withdraw);

        ImageView siba_wating = findViewById(R.id.doggif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(siba_wating);
        Glide.with(context).load(R.drawable.siba_dog_waiting).into(gifImage);

        setCancelable(false);

        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAccount.Withdraw();
                new CircleSpreadAni(activity)
                        .start(activity, new CircleSpreadAni.CircleSpreadAniEndListener() {
                            @Override
                            public void onCircleSpreadAniEnd() {
                                activity.startActivity(new Intent(activity,Base.class));
                                Toast.makeText(activity,"계정이 삭제되었습니다.",Toast.LENGTH_LONG).show();
                                activity.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                activity.finish();
                                SeriouslyWithdrawDialog.this.dismiss();
                            }
                        });
            }
        });
        findViewById(R.id.cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SeriouslyWithdrawDialog.this.dismiss();
            }
        });

        this.show();
    }
}

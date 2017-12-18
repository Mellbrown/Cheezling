package cf.g2utools.cheezling.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import cf.g2utools.cheezling.Base;
import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.CircleSpreadAni;
import cf.g2utools.cheezling.util.Listener.ConfirmCancleListener;
import cf.g2utools.cheezling.util.MyAccount;

/**
 * Created by mlyg2 on 2017-12-18.
 */

public class ConfirmCancleDialog extends Dialog {

    public ConfirmCancleDialog(final Activity activity, String strTitle, String strConent , final ConfirmCancleListener confirmCancleListener) {
        super(activity);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.d_seriously_withdraw);

        setCancelable(false);

        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        Button confirm = findViewById(R.id.confirm);
        Button cancle = findViewById(R.id.cancle);

        title.setText(strTitle);
        content.setText(strConent);
        confirm.setText("확인");
        cancle.setText("취소");

        ImageView siba_wating = findViewById(R.id.doggif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(siba_wating);
        Glide.with(activity).load(R.drawable.siba_dog_waiting).into(gifImage);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmCancleListener != null) {
                    confirmCancleListener.onConfirm();
                }
                ConfirmCancleDialog.this.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmCancleListener != null) {
                    confirmCancleListener.onCancle();
                }
                ConfirmCancleDialog.this.dismiss();
            }
        });

        this.show();
    }
}

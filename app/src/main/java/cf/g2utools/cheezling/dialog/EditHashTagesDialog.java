package cf.g2utools.cheezling.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.TypingHashTag;
import cf.g2utools.cheezling.util.Listener.DissmissStringArrayListener;

/**
 * Created by mlyg2 on 2017-12-18.
 */

public class EditHashTagesDialog extends Dialog{
    public EditHashTagesDialog(@NonNull Context context, ArrayList<String> loadTag, final DissmissStringArrayListener dissmissStringArrayListener) {
        super(context);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.d_edit_hash_tages);
        final TypingHashTag typingHashTag = findViewById(R.id.typingHashTag);
        if (loadTag != null) {
            typingHashTag.loadTags(loadTag);
        }

        ImageView siba_wating = findViewById(R.id.doggif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(siba_wating);
        Glide.with(context).load(R.drawable.siba_dog_waiting).into(gifImage);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (dissmissStringArrayListener != null) {
                    dissmissStringArrayListener.onDissmissStringArray(typingHashTag.getResult());
                }
            }
        });

        this.show();
    }
}

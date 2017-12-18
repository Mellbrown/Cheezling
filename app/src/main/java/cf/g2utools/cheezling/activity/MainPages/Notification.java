package cf.g2utools.cheezling.activity.MainPages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.BottomMenu;

public class Notification extends AppCompatActivity {
    BottomMenu bottomMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_notification);

        bottomMenu = findViewById(R.id.bot);
        bottomMenu.Init(this, BottomMenu.Here.notify);
    }
}

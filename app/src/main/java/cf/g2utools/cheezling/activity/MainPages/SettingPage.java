package cf.g2utools.cheezling.activity.MainPages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

import cf.g2utools.cheezling.Base;
import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.BottomMenu;
import cf.g2utools.cheezling.custom_view.CircleSpreadAni;
import cf.g2utools.cheezling.dialog.SeriouslyWithdrawDialog;
import cf.g2utools.cheezling.lab.LabMenu;
import cf.g2utools.cheezling.util.MyAccount;

public class SettingPage extends AppCompatActivity implements View.OnClickListener {

    BottomMenu bottomMenu;

    Button btn_reset;
    Button nick_btn;
    Button labMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        bottomMenu = findViewById(R.id.bot);
        bottomMenu.Init(this, BottomMenu.Here.setting);

        btn_reset = findViewById(R.id.btn_reset);
        nick_btn = findViewById(R.id.nick_btn);
        labMenu = findViewById(R.id.labMenu);

        btn_reset.setOnClickListener(this);
        nick_btn.setOnClickListener(this);
        labMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_reset:{
                new SeriouslyWithdrawDialog(this,this);
            }break;
            case R.id.nick_btn:{

            }break;
            case R.id.labMenu:{
                startActivity(new Intent(this, LabMenu.class));
            }break;
        }
    }
}

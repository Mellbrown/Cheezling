package cf.g2utools.cheezling.activity.MainPages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import cf.g2utools.cheezling.Base;
import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.custom_view.BottomMenu;
import cf.g2utools.cheezling.data.AccountData;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.data.ThreaData;
import cf.g2utools.cheezling.rec_adpater.ThreaCardAdpater;
import cf.g2utools.cheezling.util.MyAccount;

public class HomePage extends AppCompatActivity {

    BottomMenu bottomMenu;
    SearchView searchView;
    RecyclerView recyclerView;
    TextView txtResult;

    ThreaCardAdpater threaCardAdpater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottomMenu = findViewById(R.id.bot);
        bottomMenu.Init(this, BottomMenu.Here.home);

        MyAccount.AddLoginSuccessListener(new MyAccount.LoginSuccessListener() {
            @Override
            public void onLoginSuccess(AccountData myAccountData) {
                if (myAccountData == null) {
                    Toast.makeText(HomePage.this,"로그인 아웃 되었습니다.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(HomePage.this, Base.class));
                }
            }
        });

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        txtResult = findViewById(R.id.txtResult);

        ArrayList<ThreaData> threaData = new ArrayList<>();
        threaData.addAll(RemoteData.rootData.getThreadesData().getThreades());
        Collections.reverse(threaData);
        threaCardAdpater = new ThreaCardAdpater(this,recyclerView,threaData);
    }
}

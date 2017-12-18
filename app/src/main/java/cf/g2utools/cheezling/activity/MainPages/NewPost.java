package cf.g2utools.cheezling.activity.MainPages;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.data.AccountData;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.dialog.ConfirmCancleDialog;
import cf.g2utools.cheezling.dialog.EditHashTagesDialog;
import cf.g2utools.cheezling.util.Listener.ConfirmCancleListener;
import cf.g2utools.cheezling.util.Listener.DissmissStringArrayListener;
import cf.g2utools.cheezling.util.MyAccount;

public class NewPost extends AppCompatActivity implements View.OnClickListener {

    WebView viewHTML;
    EditText innerHTML;
    Button btnHTML;
    FloatingActionButton add;
    Button hash;

    ArrayList<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_new_post);

        viewHTML = findViewById(R.id.viewHTML );
        innerHTML = findViewById(R.id.innerHTML );
        btnHTML = findViewById(R.id.btnHTML );
        add = findViewById(R.id.add );
        hash = findViewById(R.id.hash );

        btnHTML.setOnClickListener(this);
        add.setOnClickListener(this);
        hash.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHTML:{

            }break;
            case R.id.add:{
                new ConfirmCancleDialog(this, "새글 추가", "이 내용대로 추가하시겠습니까?", new ConfirmCancleListener() {
                    @Override
                    public void onConfirm() {
                        AccountData accountData = MyAccount.getMyAccountData();
                        if(accountData == null){
                            Toast.makeText(NewPost.this,"문제가 발생하였습니다.\n어플을 종료후 다시 실행 시켜주세요.",Toast.LENGTH_LONG).show();
                            return;
                        }
                        RemoteData.rootData.getThreadesData().AddThrea(0,tags,accountData.getNickname(),accountData.getUid(),innerHTML.getText().toString());
                        RemoteData.commit();
                        Toast.makeText(NewPost.this,"글이 추가 되었습니다.",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancle() {
                        Toast.makeText(NewPost.this,"니엥",Toast.LENGTH_SHORT).show();
                    }
                });
            }break;
            case R.id.hash:{
                new EditHashTagesDialog(this, tags, new DissmissStringArrayListener() {
                    @Override
                    public void onDissmissStringArray(ArrayList<String> tags) {
                        NewPost.this.tags = tags;
                        RemoteData.rootData.getHashTagesData().pushHasTag(tags);
                        RemoteData.commit();
                    }
                });
            }break;
        }
    }
}

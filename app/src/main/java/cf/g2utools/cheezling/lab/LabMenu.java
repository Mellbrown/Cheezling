package cf.g2utools.cheezling.lab;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.lab.wrap_Activities.LabEditGlobalTages;
import cf.g2utools.cheezling.lab.wrap_Activities.LabSmallTalkOneSliding;
import cf.g2utools.cheezling.lab.wrap_Activities.LabZoomLayout;
import cf.g2utools.cheezling.rec_adpater.SimpleButtonAdapter;
import cf.g2utools.cheezling.util.Listener.ItemClickListener;

public class LabMenu extends AppCompatActivity {

    //UI 객체
    RecyclerView recyclerView;

    //Adpater, Data객체
    ArrayList<String> strings = new ArrayList<>();
    ArrayList<MenuItem> menuItems = new ArrayList<>();
    SimpleButtonAdapter simpleButtonAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_manager_menu);

        //UI 객체 로드
        recyclerView = findViewById(R.id.recyclerView);
        
        //아이템 추가
        new MenuItem("줌 레이아웃 테스트", LabZoomLayout.class);
        new MenuItem("전역 태그 추가 및 테스트", LabEditGlobalTages.class);
        new MenuItem("SmallTalkView 슬라이딩 테스트", LabSmallTalkOneSliding.class);

        //리스트 어뎁터 연결
        simpleButtonAdapter = new SimpleButtonAdapter(this,recyclerView,strings, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                menuItems.get(position).Start(LabMenu.this);
            }
        });
    }
    
    
    class MenuItem{
        String name;
        Class activity;
        public MenuItem(String name, Class activity){
            this.name = name;
            this.activity = activity;

            strings.add(name);
            menuItems.add(this);
        }
        public void Start(Context context){
            startActivity(new Intent(context,activity));
        }
    }
}

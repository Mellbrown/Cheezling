package cf.g2utools.cheezling.custom_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.rec_adpater.LittleButtonAdapter;
import cf.g2utools.cheezling.util.Listener.PressEnterListener;
import cf.g2utools.cheezling.util.SimpleChangeEditText;
import cf.g2utools.cheezling.util.Listener.TextChangeListener;


public class TypingHashTag extends FrameLayout implements TextChangeListener, LittleButtonAdapter.TagClickListener, PressEnterListener {

    static final String TAG = "TypingHashTag";

    //UI 객체
    RecyclerView compTag;
    RecyclerView autoquery;
    SimpleChangeEditText inputTag;

    //Adpater 객체
    LittleButtonAdapter adapterCompTag;
    LittleButtonAdapter adapterAutoQuery;

    //원본 태그, 쿼리 대상
    ArrayList<String> orgTags = new ArrayList<>();
    ArrayList<String> queryedTags = new ArrayList<>();

    public TypingHashTag(@NonNull Context context) {
        super(context);
        init();
    }

    public TypingHashTag(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TypingHashTag(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TypingHashTag(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        LayoutInflater.from(getContext()).inflate(R.layout.v_typing_hash_tag,this);

        //UI 객체 로드
        compTag = findViewById(R.id.compTag);
        autoquery = findViewById(R.id.autoquery);
        inputTag = new SimpleChangeEditText(((EditText) findViewById(R.id.input_tag)));
        inputTag.AddTextChangeListener(this);
        inputTag.AddPressEnterListener(this);

        //autoQuery,compTag 설정
        adapterCompTag = new LittleButtonAdapter("comp",getContext(), compTag, "x #", this);
        adapterAutoQuery = new LittleButtonAdapter("auto",getContext(), autoquery, "#", this);

        //해시태그 orgTag 동기화
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orgTags.clear();
                orgTags.addAll(RemoteData.rootData.getHashTagesData().getHashTages().keySet());
                Log.d(TAG,"query origin changed");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.d(TAG,"create");
    }

    public void loadTags(ArrayList<String> tags){
        adapterCompTag.DatasetChange(tags);
    }

    @Override //InputTag 텍스트 바뀌었을때
    public void onTextChange(String text) {
        //바뀐 테스트 내용을 쿼리 하여 auto
        Log.d(TAG,"query for " + text);
        queryedTags.clear();
        if(!text.equals(""))
            for(String tg : orgTags)
                if(tg.matches(".*"+text+".*"))
                    queryedTags.add(tg);
        adapterAutoQuery.DatasetChange(queryedTags);
    }

    @Override //InputTag 엔터를 눌렀다.
    public void onPressEnter(String text) {
        Log.d(TAG,"enter for " + text);
        adapterCompTag.Add(text);
        inputTag.setText(""); //쿼리 내용 날리고
        adapterAutoQuery.DatasetClear(); //오토 내용도 날리고
        inputTag.setFocuse(); //다시 포커즈 주고
    }

    @Override
    public void onTagClick(@NonNull String id, @NonNull String tagName) {
        switch (id) {
            // 컴플리트에서 누르면 해당 태그가 사라집니다.
            case "comp": {
                Log.d(TAG,"remove for " + tagName);
                adapterCompTag.Remove(tagName);
            }
            break;
            // 오토에서 누르면 해당 태그가 추가 됩니다.
            case "auto": {
                Log.d(TAG,"add for " + tagName);
                adapterCompTag.Add(tagName);
                inputTag.setText(""); //쿼리 내용 날리고
                adapterAutoQuery.DatasetClear(); //오토 내용도 날리고
            }
            break;
        }
    }

    public ArrayList<String> getResult(){
        return adapterCompTag.getTags();
    }
}

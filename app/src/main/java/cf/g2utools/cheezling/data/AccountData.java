package cf.g2utools.cheezling.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class AccountData {
    public String uid;
    public String nickname = "치즐러";
    public HashMap<String,String> hashTages; //해시데이터들
    public @NonNull String getUid() {
        if (uid == null) {
            uid = "";
        }
        return uid;
    }

    // 새 해시데이터 넣기
    public void pushHasTag(ArrayList<String> tages){
        for(String s :  tages)
            getHashTages().put(s,"");
    }

    //null체크 getter들
    public HashMap<String,String> getHashTages() {
        if (hashTages == null) {
            hashTages = new HashMap<>();
        }
        return hashTages;
    }

    public String getNickname() {
        if (nickname == null) {
            nickname = "";
        }
        return nickname;
    }
}

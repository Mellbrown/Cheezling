package cf.g2utools.cheezling.data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class HashTagesData {
    HashMap<String,String> hashTages; //해시데이터들

    // 새 해시데이터 넣기
    public void pushHasTag(ArrayList<String> tages){
        for(String s :  tages)
            hashTages.put(s,"");
    }

    //null체크 getter들
    public HashMap<String,String> getHashTages() {
        if (hashTages == null) {
            hashTages = new HashMap<>();
        }
        return hashTages;
    }
}

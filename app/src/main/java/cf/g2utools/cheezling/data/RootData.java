package cf.g2utools.cheezling.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mlyg2 on 2017-12-15.
 */

public class RootData {
    public HashMap<String, AccountData> accountDataes; // 계정
    public HashTagesData hashTagesData; // 해시태그들
    public ThreadesData threadesData; //스레드들(게시글들)

    // null 체크 gtter들
    public HashMap<String, AccountData> getAccountDataes() {
        if (accountDataes == null) {
            accountDataes = new HashMap<>();
        }
        return accountDataes;
    }
    public HashTagesData getHashTagesData() {
        if (hashTagesData == null) {
            hashTagesData = new HashTagesData();
        }
        return hashTagesData;
    }
    public ThreadesData getThreadesData() {
        if (threadesData == null) {
            threadesData = new ThreadesData();
        }
        return threadesData;
    }
}

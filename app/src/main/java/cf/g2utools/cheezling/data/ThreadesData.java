package cf.g2utools.cheezling.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mlyg2 on 2017-12-17.
 */

public class ThreadesData {
    public ArrayList<ThreaData> threades; //threa들 , 게시글들

    public void AddThrea(long parent_id, @NonNull ArrayList<String> tages, @NonNull String nickname, @NonNull String refOrgWriter, @NonNull String innerText){
        ThreaData threaData = new ThreaData();
        threaData.threa_id = getThreades().size();

        threaData.nickname = nickname;
        threaData.refOrgWriter = refOrgWriter;
        threaData.innerText = innerText;
        threaData.timeStamp = System.currentTimeMillis();

        threaData.like = 0;
        threaData.coment = 0;

        threaData.parent_id = parent_id;
        threaData.tag = tages;
        threaData.child_ides = new ArrayList<>();

        if(threades.contains(parent_id)){
            ThreaData threaParent = threades.get(((int) parent_id));
            threaParent.getChild_ides().add(threaData.threa_id);
            threaParent.coment = threaParent.getChild_ides().size();
        }else threaData.parent_id = 0;

        threades.add(threaData);
    }

    //null체크 gtter들

    public ArrayList<ThreaData> getThreades() {
        if (threades == null) {
            threades = new ArrayList<>();
        }
        return threades;
    }

    public ArrayList<String> toHtmles(){
        ArrayList<String> stringArrayList = new ArrayList<>();
        for(ThreaData threaData : getThreades())
            stringArrayList.add(threaData.toHtml());
        return stringArrayList;
    }
}

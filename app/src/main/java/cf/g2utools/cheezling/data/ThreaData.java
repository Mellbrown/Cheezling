package cf.g2utools.cheezling.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mlyg2 on 2017-12-17.
 */

public class ThreaData {

    public long threa_id;

    public String nickname;
    public String refOrgWriter;
    public String innerText;
    public long timeStamp;

    public long like;
    public long coment;

    public long parent_id;
    public ArrayList<String> tag;
    public ArrayList<Long> child_ides;

    public ArrayList<String> getTag() {
        if (tag == null) {
            tag = new ArrayList<>();
        }
        return tag;
    }

    public ArrayList<Long> getChild_ides() {
        if (child_ides == null) {
            child_ides = new ArrayList<>();
        }
        return child_ides;
    }

    public String toHtml(){
        String s = "";
        s += nickname + " | " + new SimpleDateFormat("yyMMdd HH:mm:ss").format(new Date(timeStamp)) + "<br />" ;
        s += "<hr />";
        s += innerText;
        s += "<br />";
        for(String t : getTag())
            s += "#" + t + " ";
        return s;
    }
}

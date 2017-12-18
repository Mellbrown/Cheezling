package cf.g2utools.cheezling.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cf.g2utools.cheezling.data.AccountData;
import cf.g2utools.cheezling.data.RemoteData;
import cf.g2utools.cheezling.data.RootData;

/**
 * Created by mlyg2 on 2017-12-16.
 * 익명 가입 시스템으로 계정 따윈 없다.
 * id는 그냥 AccountData에서 부여해준 rid가 다다
 * 3가지 메소드가 있다.
 * - MyAccount 이것은 가입 되었으면 로드 하고 아니면 만다.
 * - SignUp 가입 시킨다.
 * - Withdraw 탈퇴한다.!!!
 */


public class MyAccount {
    private static final String ACCOUNT_UID = "ACCOUNT_UID";
    public static Context context;
    public static AccountData myAccountData;
    public static boolean isOnLoaed = false;
    public static ArrayList<LoginSuccessListener> loginSuccessListeners = new ArrayList<>();

    public interface IsExsistAccountListener{ void onIsExsistAccount(boolean result); }
    public interface LoginSuccessListener{ void onLoginSuccess(AccountData myAccountData); }

    public static AccountData getMyAccountData(){
        return myAccountData;
    }

    public MyAccount(final Context context, @NonNull IsExsistAccountListener isExsistAccountListener){ //데이터 로드 및 아닌
        this.context = context;
        SharedPreferences setting = context.getSharedPreferences("setting",0);
        final String result = setting.getString(ACCOUNT_UID, "null");
        isExsistAccountListener.onIsExsistAccount(result.equals("null") ? false : true);
        if(!result.equals("null")) { //회원 -> 데이터 로드
            FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) { //데이터 로드 성공
                    isOnLoaed = true;
                    if (RemoteData.rootData.getAccountDataes().get(result) != null) {
                        myAccountData = RemoteData.rootData.getAccountDataes().get(result);
                    }else{
                        //데이터 담기, 없으면 역시 실패
                        //로컬 데이터 조정
                        SharedPreferences setting = context.getSharedPreferences("setting",0);
                        SharedPreferences.Editor editor = setting.edit();
                        editor.remove(ACCOUNT_UID);
                        editor.commit();
                    }
                    for(LoginSuccessListener listener : loginSuccessListeners)
                        listener.onLoginSuccess(myAccountData); //데이터 로드 알리기
                    loginSuccessListeners.clear();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {//데이터 로드 실패
                    isOnLoaed = true;
                    //데이터 담기, 없으면 역시 실패
                    //로컬 데이터 조정
                    SharedPreferences setting = context.getSharedPreferences("setting",0);
                    SharedPreferences.Editor editor = setting.edit();
                    editor.remove(ACCOUNT_UID);
                    editor.commit();
                    for(LoginSuccessListener listener : loginSuccessListeners)
                        listener.onLoginSuccess(myAccountData);//데이터 로드 실패 알리기
                    loginSuccessListeners.clear();
                }
            });
        }
    }

    //데이터 로딩 중... 데이터 로딩 됬으면 바로 검사하고, 아니면 로딩 될때까지 미뤄준다.
    public static void AddLoginSuccessListener(LoginSuccessListener loginSuccessListener){
        if(isOnLoaed){
            loginSuccessListener.onLoginSuccess(myAccountData);
            return;
        }
        else loginSuccessListeners.add(loginSuccessListener);
    }

    public static void SignUp(){
        //새로운 계정 데이터 생성
        String uid = FirebaseDatabase.getInstance().getReference("rootData/accountDataes").push().getKey();
        myAccountData = new AccountData();
        myAccountData.uid = uid;

        //로컬 데이터 저장
        SharedPreferences setting = context.getSharedPreferences("setting",0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(ACCOUNT_UID, uid);
        editor.commit();

        //원격 데이터 조정
        RemoteData.rootData.getAccountDataes().put(uid,myAccountData);
        RemoteData.commit();

        //이벤트 트리깅
        for(LoginSuccessListener listener : loginSuccessListeners)
            listener.onLoginSuccess(myAccountData);
        loginSuccessListeners.clear();
        isOnLoaed = true;
    }

    public static void apply(){
        RemoteData.rootData.getAccountDataes().put(myAccountData.getUid(),myAccountData);
    }

    public static void Withdraw(){
        //원격 데이터 조정
        RemoteData.rootData.getAccountDataes().remove(myAccountData.getUid());
        RemoteData.commit();

        //로컬 데이터 조정
        SharedPreferences setting = context.getSharedPreferences("setting",0);
        SharedPreferences.Editor editor = setting.edit();
        editor.remove(ACCOUNT_UID);
        editor.commit();
    }
}

package cf.g2utools.cheezling.data;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cf.g2utools.cheezling.util.Listener.LoadingListener;

/**
 * Created by mlyg2 on 2017-12-15.
 */

public class RemoteData {
    public static RootData rootData;
    boolean interlized = false;
    public RemoteData(final Context context, final LoadingListener loadingListener) {
        FirebaseDatabase.getInstance().getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!interlized){
                    Toast.makeText(context,"서비스에 연결되었습니다.",Toast.LENGTH_SHORT).show();
                    interlized = true;
                }
                rootData = dataSnapshot.getValue(RootData.class);
                if (rootData == null) {
                    rootData = new RootData();
                }
                if (loadingListener != null) {
                    loadingListener.onLoad();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(context,"서비스를 연결하는데 실패 하였습니다.",Toast.LENGTH_SHORT).show();
                Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public static void commit(){
        FirebaseDatabase.getInstance().getReference().setValue(rootData);
    }
}

package cf.g2utools.cheezling.rec_adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.rec_item.LittleButtonViewHolder;
import cf.g2utools.cheezling.util.Listener.ItemClickListener;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class LittleButtonAdapter  extends RecyclerView.Adapter implements ItemClickListener{
    static final String TAG = "LittleButtonAdapter";
    public interface TagClickListener{ void onTagClick(@NonNull String id,@NonNull String tagName); }
    ArrayList<String> tags = new ArrayList<>();
    TagClickListener tagClickListener;
    String prefix;
    String id;

    @Override
    public void onItemClick(int position) {
        if (tagClickListener != null) {
            tagClickListener.onTagClick(id,tags.get(position));
        }
    }

    public LittleButtonAdapter(@NonNull String id,@NonNull Context context,@NonNull RecyclerView recyclerView,@NonNull String prefix, TagClickListener tagClickListener){
        this.tagClickListener = tagClickListener;
        this.prefix = prefix;
        this.id = id;

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_little_button,parent,false);
        return new LittleButtonViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LittleButtonViewHolder) holder).setText(prefix + tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public void Add(String tagName){
        Log.d(TAG,"Add for" + tagName);
        tags.add(0,tagName);
//        notifyItemInserted(0);
        notifyDataSetChanged();
    }
    public void Remove(String tagName){
        Log.d(TAG,"Remove for" + tagName);
        if(!tags.contains(tagName)) return;
        int pos = tags.indexOf(tagName);
        tags.remove(tagName);
//        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }
    public void DatasetChange(ArrayList<String> newTags){
        Log.d(TAG,"Dataset Change");
        tags.clear();
        tags.addAll(newTags);
        notifyDataSetChanged();
    }

    public void DatasetClear(){
        Log.d(TAG,"Dataset clear");
        tags.clear();
        notifyDataSetChanged();
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}

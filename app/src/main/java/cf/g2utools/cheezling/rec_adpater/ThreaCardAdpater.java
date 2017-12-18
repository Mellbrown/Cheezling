package cf.g2utools.cheezling.rec_adpater;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;


import java.util.ArrayList;

import cf.g2utools.cheezling.data.ThreaData;
import cf.g2utools.cheezling.rec_item.ThreaViewHolder;
import cf.g2utools.cheezling.util.Listener.LikeOrCommentListener;

/**
 * Created by mlyg2 on 2017-12-19.
 */

public class ThreaCardAdpater extends RecyclerView.Adapter implements LikeOrCommentListener {

    ArrayList<ThreaData> threades = new ArrayList<>();

    public ThreaCardAdpater(Context context, RecyclerView recyclerView,ArrayList<ThreaData> threades){
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(this);

        this.threades = threades;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ThreaViewHolder.fatory(parent,this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ThreaViewHolder) holder).setDataBound(position,threades.get(position));
    }

    @Override
    public int getItemCount() {
        return threades.size();
    }

    @Override
    public void onLike(int position) {

    }

    @Override
    public void onComment(int position) {

    }
}

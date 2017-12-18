package cf.g2utools.cheezling.rec_adpater;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.rec_item.SimpleButtonViewHolder;
import cf.g2utools.cheezling.util.Listener.ItemClickListener;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class SimpleButtonAdapter extends RecyclerView.Adapter {

    ArrayList<String> strings;
    ItemClickListener itemClickListener;

    public SimpleButtonAdapter(Context context,RecyclerView recyclerView, ArrayList<String> strings, ItemClickListener itemClickListener){
        this.strings = strings;
        this.itemClickListener = itemClickListener;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_simple_button,parent,false);
        return new SimpleButtonViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SimpleButtonViewHolder) holder).setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
}

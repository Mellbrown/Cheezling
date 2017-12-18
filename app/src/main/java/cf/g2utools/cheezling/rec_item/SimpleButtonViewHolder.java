package cf.g2utools.cheezling.rec_item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.util.Listener.ItemClickListener;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class SimpleButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    Button button;
    ItemClickListener itemClickListener;

    public SimpleButtonViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        button = itemView.findViewById(R.id.button);
        button.setOnClickListener(this);
        this.itemClickListener = itemClickListener;
    }

    public void setText(String text){
        button.setText(text);
    }

    @Override
    public void onClick(View view) {
        int position = getLayoutPosition();
        itemClickListener.onItemClick(position);
    }
}

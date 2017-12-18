package cf.g2utools.cheezling.rec_item;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.util.Listener.ItemClickListener;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class LittleButtonViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView inner;
    ItemClickListener itemClickListener;

    public LittleButtonViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        inner = itemView.findViewById(R.id.inner);
        inner.setOnClickListener(this);
        this.itemClickListener = itemClickListener;
    }

    public void setText(String text){
        inner.setText(text);
    }

    @Override
    public void onClick(View view) {
        int position = getLayoutPosition();
        itemClickListener.onItemClick(position);
    }
}

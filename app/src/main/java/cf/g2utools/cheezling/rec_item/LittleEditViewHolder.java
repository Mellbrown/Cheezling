package cf.g2utools.cheezling.rec_item;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import cf.g2utools.cheezling.R;
import cf.g2utools.cheezling.util.Listener.TextChangeListener;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class LittleEditViewHolder extends RecyclerView.ViewHolder implements TextWatcher {

    TextChangeListener textChangeListener;
    EditText edit;

    public LittleEditViewHolder(View itemView, TextChangeListener textChangeListener) {
        super(itemView);
        edit = itemView.findViewById(R.id.edit);
        edit.addTextChangedListener(this);
        this.textChangeListener = textChangeListener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textChangeListener.onTextChange(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}

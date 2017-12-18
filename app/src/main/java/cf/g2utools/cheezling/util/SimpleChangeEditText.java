package cf.g2utools.cheezling.util;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import cf.g2utools.cheezling.util.Listener.PressEnterListener;
import cf.g2utools.cheezling.util.Listener.TextChangeListener;

/**
 * Created by mlyg2 on 2017-12-16.
 */

public class SimpleChangeEditText implements TextWatcher, View.OnKeyListener {
    EditText editText;
    TextChangeListener textChangeListener;
    PressEnterListener pressEnterListener;

    public SimpleChangeEditText(@NonNull EditText editText){
        this.editText = editText;
        editText.addTextChangedListener(this);
        editText.setOnKeyListener(this);
    }

    public void AddTextChangeListener(@NonNull TextChangeListener textChangeListener){
        this.textChangeListener = textChangeListener;
    }

    public void AddPressEnterListener(@NonNull PressEnterListener pressEnterListener){
        this.pressEnterListener = pressEnterListener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (textChangeListener != null) {
            textChangeListener.onTextChange(editText.getText().toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void setText(String text){
        editText.setText(text);
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if((keyEvent.getAction() == keyEvent.ACTION_DOWN ) && (keyCode == KeyEvent.KEYCODE_ENTER)){
            if (pressEnterListener != null) {
                pressEnterListener.onPressEnter(editText.getText().toString());
                return true;
            }
        }
        return false;
    }

    public void setFocuse(){
        editText.requestFocus();
    }
}


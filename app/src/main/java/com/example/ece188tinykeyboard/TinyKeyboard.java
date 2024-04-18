package com.example.ece188tinykeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class TinyKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;

    private Keyboard uppercaseKeyboard;

    // indicate which keyboard we are on
    private boolean upper = false;

    @Override
    public View onCreateInputView() {

        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.custom_keyboard_layout, null);
        keyboard = new Keyboard(this, R.xml.swipe_keyboard);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);

        // create upper case keyboard
        uppercaseKeyboard = new Keyboard(this, R.xml.uppercase_keyboard);

        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        // change to upper case
        if(String.valueOf((char) primaryCode).equals("^")){
            if(upper)
                keyboardView.setKeyboard(keyboard);
            else
                keyboardView.setKeyboard(uppercaseKeyboard);
            upper = !upper;
            return;
        }

        // else commit text
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection == null){
            return;
        }
        inputConnection.commitText(String.valueOf((char) primaryCode), 1);
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {
        // swipe left --> delete
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection == null){
            return;
        }
        CharSequence selectedText = inputConnection.getSelectedText(0);
        // if there are selection, delete one char, else delete selection
        if (TextUtils.isEmpty(selectedText)) {
            inputConnection.deleteSurroundingText(1, 0);
        } else {
            inputConnection.commitText("", 1);
        }
    }

    @Override
    public void swipeRight() {
        // swipe right --> space
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection == null){
            return;
        }
        inputConnection.commitText(" ", 1);
    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {
    }
}

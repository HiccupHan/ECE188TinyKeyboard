package com.example.ece188tinykeyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputConnection;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TinyKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;
    private PopupWindow popupWindow;
    private TextView popupTextView;

    @Override
    public View onCreateInputView() {

        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.custom_keyboard_layout, null);
        keyboard = new Keyboard(this, R.xml.swipe_keyboard);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {
//        List<String> lettersToShow = getLettersForPopup(primaryCode);
//        showKeyPopup(lettersToShow);
    }

    @Override
    public void onRelease(int primaryCode) {
//        InputConnection inputConnection = getCurrentInputConnection();
//        if(inputConnection == null){
//            return;
//        }
//        inputConnection.commitText(String.valueOf((char) primaryCode), 1);
    }


//    private List<String> getLettersForPopup(int keyCode) {
//        List<String> lettersToShow = new ArrayList<>();
//        switch (keyCode) {
//            case 97:
//                lettersToShow = Arrays.asList("A", "B", "C", "D");
//                break;
//            case 98:
//                lettersToShow = Arrays.asList("B", "ß", "Þ", "þ");
//                break;
//            // Add more cases for other keys as needed
//            default:
//                break;
//        }
//        return lettersToShow;
//    }

//    private void showKeyPopup(List<String> lettersToShow) {
//        // Inflate the key_popup_layout.xml to create the popup view
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.key_popup_layout, null);
//
//        // Initialize the PopupWindow
//        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//
//        // Find the TextView in the popup layout
//        popupTextView = popupView.findViewById(R.id.popupTextView);
//
//        // Build the string with letters for display in the popup
//        StringBuilder popupText = new StringBuilder();
//        for (String letter : lettersToShow) {
//            popupText.append(letter).append("\n");
//        }
//        popupTextView.setText(popupText.toString().trim()); // Set text with letters for selection
//
//        // Show the popup window above the keyboard view
//
//
//        // Handle letter selection (optional)
//        if (popupWindow != null && popupTextView != null && keyboardView != null) {
//            // Use popupWindow, popupTextView, and keyboardView safely
//            int[] location = new int[2];
//            keyboardView.getLocationOnScreen(location);
//            int yOffset = location[1] - popupView.getHeight(); // Adjust the y-offset for the popup position
//            popupWindow.showAtLocation(keyboardView, Gravity.NO_GRAVITY, 0, yOffset);
//            popupTextView.setOnClickListener(view -> {
//                String selectedLetter = ((TextView) view).getText().toString();
//                Toast.makeText(this, "Selected: " + selectedLetter, Toast.LENGTH_SHORT).show();
//                // Perform any desired action with the selected letter (e.g., commit to input connection)
//                InputConnection inputConnection = getCurrentInputConnection();
//                if (inputConnection != null) {
//                    inputConnection.commitText(selectedLetter, 1);
//                }
//                // Dismiss the popup window after selection
//                popupWindow.dismiss();
//            });
//        }

//    }
    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
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
        InputConnection inputConnection = getCurrentInputConnection();
        if(inputConnection == null){
            return;
        }
        CharSequence selectedText = inputConnection.getSelectedText(0);
        if (TextUtils.isEmpty(selectedText)) {
            // no selection, so delete previous character
            inputConnection.deleteSurroundingText(1, 0);
        } else {
            // delete the selection
            inputConnection.commitText("", 1);
        }
    }

    @Override
    public void swipeRight() {
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

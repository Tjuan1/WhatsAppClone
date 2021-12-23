package com.example.whatsappclone;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UserInput {

    private Context mContext;

    public UserInput(Context context) {
        mContext = context;
    }
    //    this hides the keyboard if we tap on the background
    public void onRootLayoutTapped(View mView) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

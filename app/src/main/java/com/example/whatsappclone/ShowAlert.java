package com.example.whatsappclone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

public class ShowAlert {

    private String title;
    private String message;
    private Context mContext;

    public ShowAlert(String title, String message, Context context) {
        this.title = title;
        this.message = message;
        this.mContext = context;
    }


    public void showAlertMessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}

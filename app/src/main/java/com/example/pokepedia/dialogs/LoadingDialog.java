package com.example.pokepedia.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;

import com.example.pokepedia.R;

public class LoadingDialog {

    private Activity activity;
    private Dialog dialog;


    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void startLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(View.inflate(activity.getApplicationContext(), R.layout.loading_screen,
                activity.findViewById(R.id.progress_bar_root)));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }


    public void dismiss() {
        dialog.dismiss();
    }
}

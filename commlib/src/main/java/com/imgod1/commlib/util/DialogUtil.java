package com.imgod1.commlib.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * DialogUtil.java
 *
 * @author gaokang
 * @version 1.0 2019/7/2 16:50
 * @update gaokang 2019/7/2 16:50
 * @updateDes
 * @include {@link }
 * @used {@link }
 */
public class DialogUtil {
    private DialogUtil() {
    }

    public static ProgressDialog createProgressDialog(Context context, String message, boolean cancelable) {
        @SuppressLint("InlinedApi")
        ProgressDialog progressDialog = new ProgressDialog(context,android.R.style.Theme_Material_Dialog);
        progressDialog.getWindow().setDimAmount(0.2f);
        if (message != null) {
            progressDialog.setMessage(message);
        }
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,int theme, String message, boolean cancelable) {
        ProgressDialog progressDialog = new ProgressDialog(context,theme);
        if (message != null) {
            progressDialog.setMessage(message);
        }
        return progressDialog;
    }
    public static AlertDialog createAlertDialog(Context context, String title, String message,
                                                String positive, String negative, boolean cancelable,
                                                final DialogButtonClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null) {
            builder.setTitle(title);
        }
        if (message != null) {
            builder.setMessage(message);
        }
        builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onNegativeButtonClick(dialog);
            }
        });
        builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onPositiveButtonClick(dialog);
            }
        });
        builder.setCancelable(cancelable);
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public interface DialogButtonClickListener {
        void onPositiveButtonClick(DialogInterface dialog);

        void onNegativeButtonClick(DialogInterface dialog);
    }

}

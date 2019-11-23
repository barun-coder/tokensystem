package com.displayfort.dftoken.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.displayfort.dftoken.OnValueReturn;
import com.displayfort.dftoken.R;

/**
 * Created by Husain on 26/04/2019 12:41.
 * SportsInCode
 */
public class DialogUtils {

    public static void showAlertDialog(Context context, String textMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }

    public static void showAlertDialog(Context context, String textMsg, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setCancelable(false)
                .setPositiveButton(R.string.ok, onClickListener);
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }


    public static void showAlertDialog(Context context, String title, String textMsg, int left, int right, DialogInterface.OnClickListener onClickLeftListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setTitle(title);
        builder.setCancelable(false)
                .setPositiveButton(left, onClickLeftListener);
        builder.setNegativeButton(right, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }

    public static void showAlertDialog(Context context, String title, String textMsg, int left, int right, DialogInterface.OnClickListener onClickLeftListener, DialogInterface.OnClickListener onClickRightListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(textMsg);
        builder.setTitle(title);
        builder.setCancelable(false)
                .setPositiveButton(left, onClickLeftListener);
        builder.setNegativeButton(right, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertdialog = builder.create();
        alertdialog.show();
    }

    public static void onGetNewTokenDialog(Context context, final OnValueReturn onValueReturn) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.select_token_dialog);

        final EditText input = dialog.findViewById(R.id.token);


        Button cancelBtn = dialog.findViewById(R.id.cancel_btn);
        cancelBtn.setTag(dialog);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button okBtn = dialog.findViewById(R.id.ok_btn);
        okBtn.setTag(dialog);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valuetxt = input.getText().toString();
                if (TextUtils.isDigitsOnly(valuetxt)) {
                    onValueReturn.getskipTokenID(valuetxt);
                    dialog.dismiss();
                } else {
                    Utility.ShowToast("Enter only number", context);
                }
//
            }
        });

        dialog.show();

    }


}

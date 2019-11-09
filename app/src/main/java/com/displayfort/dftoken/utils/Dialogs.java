package com.displayfort.dftoken.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.displayfort.dftoken.R;


/**
 * Created by prashantj on 4/18/2016.
 */
public class Dialogs {

    public interface DialogListener {
        void onDialogButtonClick(int id, Object o);
    }

    public static void showOkCancelDialog(Context context, String message, final View.OnClickListener clickInAdapter) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.ok_cancel_dialog);

        TextView titleTv = dialog.findViewById(R.id.title_tv);


        TextView messageTv = dialog.findViewById(R.id.message_tv);
        messageTv.setText(message);

        Button cancelBtn = dialog.findViewById(R.id.cancel_btn);
        cancelBtn.setTag(dialog);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button okBtn = dialog.findViewById(R.id.ok_btn);
        okBtn.setText("YES");
        okBtn.setTag(dialog);
        okBtn.setOnClickListener(clickInAdapter);

        dialog.show();

    }

    public static void showOkDialog(Context context, String message) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.ok_dialog);

        Button btnSubmit = dialog.findViewById(R.id.ok_btn);

        TextView messageTv = dialog.findViewById(R.id.message_tv);
        messageTv.setText(message);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

}


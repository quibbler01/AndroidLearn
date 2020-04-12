package com.easyicon.learnglide.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.easyicon.learnglide.R;

/**
 * ProjectName:    LearnGlide
 * Package:        com.easyicon.learnglide.ui.dialog
 * ClassName:      MyDialogFragment
 * Description:
 * Author:         61444
 * CreateDate:     2020/3/1 15:06
 */
public class MyDialogFragment extends DialogFragment {

    //在onCreate中拿到外部传入的参数
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //在onCreateDialog中构建一个alertDialog对象
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.NoBackgroundDialog)
                .setTitle("透明背景Dialog")
                .setMessage("Dialog信息")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false);
        return builder.create();
    }

    //处理dismiss响应事件
    public void dismiss() {
        super.dismiss();
    }
}

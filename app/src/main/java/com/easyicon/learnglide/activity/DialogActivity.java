package com.easyicon.learnglide.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.easyicon.learnglide.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    private String[] items = {"item 1", "item 2", "item 3", "item 4", "item 5", "item 6"};
    private boolean[] checkeds = {false, false, false, false, false, false};

    @BindView(R.id.show_dialog)
    Button mShowDialog;

    private Context mContext;

    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
        mContext = this;
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @OnClick(R.id.show_dialog)
    public void onViewClicked() {
        showDialog();
    }

    private void showDialog() {
//        DialogFragment dialogFragment = new MyDialogFragment();
//        dialogFragment.show(getSupportFragmentManager(), "DialogFragment");

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.NoBackgroundDialog)
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
        builder.create().show();


//        Dialog dialog = new Dialog(this);
//        dialog.show();

    }
}

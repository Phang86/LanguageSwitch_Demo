package com.hnhy.languageswitch_demo.view;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hnhy.languageswitch_demo.R;

public class UpdateDialog extends Dialog{
    private Context context;
    private EditText etContent;
    private Button dialog_cancelBtn, dialog_confirmBtn;
    private static UpdateDialog mInstance;
    private InputMethodManager inputMethodManager;
    private String content;

//    public static UpdateDialog getInstance(){
//        if (mInstance == null){
//            synchronized (UpdateDialog.class){
//                if (mInstance == null){
//                    mInstance = new UpdateDialog(mInstance.context, mInstance.content);
//                }
//            }
//        }
//        return mInstance;
//    }

    public UpdateDialog(@NonNull Context context,String content) {
        super(context);
        this.context = context;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
    }

    /**
     * todo 带有确认取消按钮的自定义dialog
     *
     * @param //context 上下文对象
    //     * @param title   标题
     * @param //content 内容
     */
    public void showConfirmDialog(OnDialogButtonClickListener mOnDialogButtonClickListener) {
        etContent = findViewById(R.id.dialog_et);
        etContent.setText(content);
        dialog_cancelBtn = findViewById(R.id.dialog_btn_cancel);
        dialog_confirmBtn = findViewById(R.id.dialog_btn_confirm);
        dialog_cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDialogButtonClickListener != null) {
                    mOnDialogButtonClickListener.onNegativeButtonClick();
                }
            }
        });
        //为确认按钮设置点击监听
        dialog_confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnDialogButtonClickListener != null) {
                    mOnDialogButtonClickListener.onPositiveButtonClick(etContent.getText().toString().trim());
                }
            }
        });
        //showInput();
        setWindowGravity();
    }

    private void setWindowGravity(){
        //获取窗口对象
        Window window = this.getWindow();//获取窗口对象参数
        WindowManager.LayoutParams wlp = window.getAttributes();
        //获取屏幕尺寸
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = d.getWidth();
        wlp.gravity = Gravity.CENTER;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
    }

    /**
     * 显示键盘
     */
    public void showInput() {
        etContent.requestFocus();
        inputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    //todo 按钮点击回调接口
    public static OnDialogButtonClickListener mOnDialogButtonClickListener;

    public void setMonDialogButtonClickListener(OnDialogButtonClickListener listener) {
        this.mOnDialogButtonClickListener = listener;
    }

    public interface OnDialogButtonClickListener {
        void onPositiveButtonClick(String content); //确认

        void onNegativeButtonClick(); //取消
    }
}

package com.hnhy.languageswitch_demo.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;

import androidx.core.content.ContextCompat;

import com.hnhy.languageswitch_demo.R;

public class MyPasswordEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    /**
     * 眼睛按钮
     */
    private Drawable mToggleDrawable;

    public MyPasswordEditText(Context context) {
        this(context,null);
    }

    public MyPasswordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public MyPasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        //设置禁用长按
        this.setLongClickable(false);
        // 禁止编辑框横屏时弹出另外一个编辑界面
        this.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        this.setCustomSelectionActionModeCallback(new ActionModeCallbackInterceptor());
        //使用该方式基本可以实现禁用粘贴复制功能,6.0以上可用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.setCustomInsertionActionModeCallback(new ActionModeCallbackInterceptor());
        }
    }


    /**
     * 初始化右边小眼睛的控件
     */
    private void init() {
        //获取EditText的DrawableRight,主要是通过xml或者外部设置右边的按钮，如果没有设置就采用默认的
        mToggleDrawable = getCompoundDrawables()[2];
        if (mToggleDrawable == null) {
            mToggleDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_eye_24);
        }
        mToggleDrawable.setBounds(0, 0, mToggleDrawable.getIntrinsicWidth(), mToggleDrawable.getIntrinsicHeight());
        setToggleIconVisible(false);
        setOnFocusChangeListener(this);
        addTextChangedListener(this);
    }

    /**
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在 EditText的宽度 - 图标到控件右边的间距 - 图标的宽度 和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                boolean touchable = event.getX() > (getWidth()
                        - getPaddingRight() - mToggleDrawable.getIntrinsicWidth())
                        && (event.getX() < ((getWidth() - getPaddingRight())));
                if (touchable) {
                    //显示密码明文
                    setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    postInvalidate();
                    CharSequence charSequence = getText();
                    //为了保证体验效果，需要保持输入焦点在文本最后一位
                    if (charSequence != null) {
                        Spannable spanText = (Spannable) charSequence;
                        Selection.setSelection(spanText, charSequence.length());
                    }
                }
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                //隐藏密码明文
                setTransformationMethod(PasswordTransformationMethod.getInstance());
                postInvalidate();
                setSelection(getText().length());
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     * @param visible
     */
    public void setToggleIconVisible(boolean visible) {
        Drawable right = visible ? mToggleDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setToggleIconVisible(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 当EditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setToggleIconVisible(getText().length() > 0);
        } else {
            setToggleIconVisible(false);
            setShakeAnimation();
        }
    }

    /**
     * 设置晃动动画
     */
    public void setShakeAnimation(){
        this.setAnimation(shakeAnimation(3));

    }

    /**
     * 晃动动画
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public Animation shakeAnimation(int counts){
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        return translateAnimation;
    }

    //禁止复制、粘贴
    private class ActionModeCallbackInterceptor implements ActionMode.Callback {
//        private final String TAG = this.class.getSimpleName();

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            return false;
        }


        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }


        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }


        public void onDestroyActionMode(ActionMode mode) {
        }
    }
}

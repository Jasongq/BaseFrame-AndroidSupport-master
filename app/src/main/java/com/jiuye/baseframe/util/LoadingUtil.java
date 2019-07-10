package com.jiuye.baseframe.util;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.WindowManager;
import android.widget.TextView;

import com.jiuye.baseframe.R;

/**
 * <pre>
 *     author : GuoQiang
 *     e-mail : 849199845@qq.com
 *     time   : 2018/12/10
 *     desc   : 加载动画
 *     version: 1.0
 * </pre>
 */
public class LoadingUtil extends AlertDialog {
    private String mTitle = "加载中";

    public LoadingUtil(@NonNull Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    public LoadingUtil(@NonNull Context context, String mTitle) {
        super(context, R.style.loadingDialogStyle);
        this.mTitle = mTitle;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        initView();
    }

    private void initView() {
        TextView tvTitle = (AppCompatTextView) findViewById(R.id.tvTitle);
        assert tvTitle != null;
        tvTitle.setText(mTitle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.9f;
        getWindow().setAttributes(attributes);
        setCanceledOnTouchOutside(false);
    }

}

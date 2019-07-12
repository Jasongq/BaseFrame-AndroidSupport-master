package com.jiuye.baseframe.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ImageView;

import com.jiuye.baseframe.R;
import com.jiuye.baseframe.base.BaseActivity;
import com.jiuye.baseframe.ui.MainActivity;
import com.jiuye.baseframe.util.AppUtil;
import com.jiuye.baseframe.util.CommonTools;
import com.jiuye.baseframe.util.GlideUtil;
import com.jiuye.baseframe.util.RxTimerUtil;
import com.jiuye.baseframe.util.SPUtils;
import com.jiuye.baseframe.util.StatusBarUtil;
import com.jiuye.baseframe.util.XLog;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks {
    private static final String TAG = "WelcomeActivity";
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.versionCodeTv)
    AppCompatTextView versionCodeTv;


    @Override
    protected void initTaskRoot() {
        if (!isTaskRoot()){
            finish();
            return;
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initData() {
        versionCodeTv.setText(String.format("v%1$s", AppUtil.getVersionName(this)));
        image.setImageResource(R.drawable.welcome);
        //GlideUtil.load(this, R.drawable.welcome, image);
        if (EasyPermissions.hasPermissions(this, CommonTools.COMMON_PERMISSION)) {
            goNext();
        } else {
            EasyPermissions.requestPermissions(this,
                    getString(R.string.rationale_location),
                    CommonTools.RC_LOCATION_PHONE_PERM,
                    CommonTools.COMMON_PERMISSION);
        }
    }

    @Override
    protected void setStatusBarColor() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0);
    }

    private void goNext() {
        RxTimerUtil.timer(3000, new RxTimerUtil.IRxAction() {
            @Override
            public void action(long number) {
                if (SPUtils.getBoolean(CommonTools.IS_FIRST_INTO, true)) {
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                    finish();
                } else {
                    SPUtils.putBooleanUser(CommonTools.USER_ISLOGIN,true);
                    if (SPUtils.getBooleanUser(CommonTools.USER_ISLOGIN, false)) {
                        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    }
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        XLog.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
        goNext();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        XLog.d(TAG, "onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                    .setTitle(R.string.title_settings)
                    .setRationale(R.string.rationale_ask_again)
                    .build().show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
        }
    }

    @Override
    public void onRationaleAccepted(int requestCode) {
        XLog.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    @Override
    public void onRationaleDenied(int requestCode) {
        XLog.d(TAG, "onRationaleDenied:" + requestCode);
    }

    @Override
    public void onBackPressed() {
    }
}

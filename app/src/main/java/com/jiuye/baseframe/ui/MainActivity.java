package com.jiuye.baseframe.ui;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RadioGroup;

import com.jiuye.baseframe.R;
import com.jiuye.baseframe.base.BaseActivity;
import com.jiuye.baseframe.ui.adapter.MyPagerAdapter;
import com.jiuye.baseframe.ui.fragment.MineFragment;
import com.jiuye.baseframe.ui.fragment.SimpleFragment;
import com.jiuye.baseframe.util.CommonTools;
import com.jiuye.baseframe.util.MessageEvent;
import com.jiuye.baseframe.util.StatusBarUtil;
import com.jiuye.baseframe.util.ToastUtil;
import com.jiuye.baseframe.widget.MyViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.mViewPager)
    MyViewPager mViewPager;
    @BindView(R.id.radioBtn1)
    AppCompatRadioButton radioBtn1;
    @BindView(R.id.radioBtn2)
    AppCompatRadioButton radioBtn2;
    @BindView(R.id.radioBtn3)
    AppCompatRadioButton radioBtn3;
    @BindView(R.id.radioBtn4)
    AppCompatRadioButton radioBtn4;
    @BindView(R.id.mGroup)
    RadioGroup mGroup;
    @BindView(R.id.centerTv)
    AppCompatTextView centerTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setStatusBarColor() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0,null);
        //字体颜色
        StatusBarUtil.setLightMode(this);
    }

    @Override
    protected void initData() {
        initFragment();
    }

    private void initFragment() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(SimpleFragment.newInstance("微信"));
        fragmentList.add(SimpleFragment.newInstance("通讯录"));
        fragmentList.add(SimpleFragment.newInstance("发现"));
        fragmentList.add(MineFragment.newInstance("我的"));
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(onPageChangeListener);
        mGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        radioBtn1.setChecked(true);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    radioBtn1.setChecked(true);
                    break;
                case 1:
                    radioBtn2.setChecked(true);
                    break;
                case 2:
                    radioBtn3.setChecked(true);
                    break;
                case 3:
                    radioBtn4.setChecked(true);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioBtn1:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.radioBtn2:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.radioBtn3:
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.radioBtn4:
                    mViewPager.setCurrentItem(3);
                    break;
                default:
                    break;
            }
        }
    };

    @OnClick({R.id.centerTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.centerTv:
                ToastUtil.show("上传");
                break;
            default:
                break;
        }
    }

    /**
     * 注册EventBus
     * @return
     */
    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void receiveEvent(MessageEvent event) {
        switch (event.getCode()) {
            case CommonTools.EVENT_CODE_BACK_GROUND:
                ToastUtil.show("Android Support已经切换到后台");
                break;
            case CommonTools.EVENT_CODE_FORE_GROUND:
                //后台回到app 如果用户已经登录，需要更新用户信息

                break;
            default:
                break;
        }
    }
}

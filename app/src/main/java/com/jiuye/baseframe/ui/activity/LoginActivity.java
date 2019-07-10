package com.jiuye.baseframe.ui.activity;

import com.jiuye.baseframe.R;
import com.jiuye.baseframe.base.BaseMvpActivity;
import com.jiuye.baseframe.mvp.contract.LoginContract;
import com.jiuye.baseframe.mvp.presenter.LoginPresenterImple;

public class LoginActivity extends BaseMvpActivity<LoginContract.IPresenter> implements LoginContract.View{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
    @Override
    protected LoginContract.IPresenter createPresenter() {
        return new LoginPresenterImple();
    }

    @Override
    protected void initData() {
        presenter.login("15162148600","123456");
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    public void loginSuccess(Object data) {

    }

    @Override
    public void loginFail(String errorMsg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }
}

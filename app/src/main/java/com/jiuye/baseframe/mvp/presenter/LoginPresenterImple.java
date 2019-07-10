package com.jiuye.baseframe.mvp.presenter;

import com.jason.rxhttp.RxHttpUtils;
import com.jason.rxhttp.interceptor.Transformer;
import com.jason.rxhttp.observer.StringObserver;
import com.jiuye.baseframe.base.BasePresenter;
import com.jiuye.baseframe.http.ApiService;
import com.jiuye.baseframe.mvp.contract.LoginContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : GuoQiang
 * e-mail : 849199845@qq.com
 * time   : 2019/04/01  14:25
 * desc   :
 * version: 1.0
 */
public class LoginPresenterImple extends BasePresenter<LoginContract.View> implements LoginContract.IPresenter{
    @Override
    public void login(String userName, String password) {
        if (isViewAttached()){
            getView().showLoading();
        }
        RxHttpUtils.createApi(ApiService.class)
                .login(userName,password)
                .compose(Transformer.<String>switchSchedulers())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new StringObserver() {
                    @Override
                    protected void onError(String errorMsg) {

                    }

                    @Override
                    protected void onSuccess(String data) {
                        if (!isViewAttached())return;
                        getView().loginSuccess(data);
                    }
                });

    }
}

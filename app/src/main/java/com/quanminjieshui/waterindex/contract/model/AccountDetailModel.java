package com.quanminjieshui.waterindex.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.AccountDetailResponseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:我的资产
 */

public class AccountDetailModel {

    public void getAccountDetail(BaseActivity activity, final AccountDetailCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .accountDetail(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<AccountDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<AccountDetailResponseBean>>io())
                .subscribe(new BaseObserver<AccountDetailResponseBean>(activity) {

                    @Override
                    protected void onSuccess(AccountDetailResponseBean accountDetailBean) throws Exception {
                        callBack.success(accountDetailBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.failed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.failed(e.getMessage());
                            }
                        } else {
                            callBack.failed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.failed(msg);
                    }
                });
    }

    public interface AccountDetailCallBack{
        void success(AccountDetailResponseBean accountDetailResponseBean);
        void failed(String msg);
    }
}


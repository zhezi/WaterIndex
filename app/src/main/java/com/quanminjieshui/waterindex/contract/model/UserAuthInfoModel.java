package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.UserAuthInfo;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:身份认证--info 与注册登录认证不同
 */

public class UserAuthInfoModel {

    public void userAuthInfo(BaseActivity activity, BaseBean params,final UserAuthInfoCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .userAuthInfo(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity<UserAuthInfo>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<UserAuthInfo>>io())
                .subscribe(new BaseObserver<UserAuthInfo>(activity) {
                    @Override
                    protected void onSuccess(UserAuthInfo userAuthInfo) throws Exception {
                        callBack.success(userAuthInfo);
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

    public interface UserAuthInfoCallBack{
        void success(UserAuthInfo userAuthInfo);
        void failed(String msg);
    }
}

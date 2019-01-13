package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.SystemMsgResponseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class SystemtMsgModel {

    public void getSystemMsg(BaseActivity activity, BaseBean params,final SystemMsgCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .systemMsg(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity<SystemMsgResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<SystemMsgResponseBean>>io())
                .subscribe(new BaseObserver<SystemMsgResponseBean>(activity) {
                    @Override
                    protected void onSuccess(SystemMsgResponseBean systemMsgResponseBean) throws Exception {
                        callBack.success(systemMsgResponseBean);
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

    public interface SystemMsgCallBack{
        void success(SystemMsgResponseBean systemMsgResponseBean);
        void failed(String msg);
    }
}

package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.PayMentResponseBean;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:支付方式
 */

public class PayMentTypeModel {

    public void payMentType(BaseActivity activity, BaseBean params,final PayMentTypeCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .payMentType(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity<PayMentResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<PayMentResponseBean>>io())
                .subscribe(new BaseObserver<PayMentResponseBean>(activity) {
                    @Override
                    protected void onSuccess(PayMentResponseBean payMentResponseBean) throws Exception {
                        callBack.success(payMentResponseBean);
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

    public interface PayMentTypeCallBack{
        void success(PayMentResponseBean payMentResponseBean);
        void failed(String msg);
    }
}

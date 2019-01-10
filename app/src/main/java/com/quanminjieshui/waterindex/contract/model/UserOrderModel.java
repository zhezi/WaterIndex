package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.contract.model.callback.CommomCallback;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;

public class UserOrderModel {

    public void createOrder(BaseActivity activity, String trade_id, String total, final CommomCallback callback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("trade_id", trade_id);
        params.put("total", total);
        RetrofitFactory.getInstance().createService()
                .userOrder(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {


                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.onRequestSuccess(o);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFailed(e.getMessage());
                            }
                        } else {
                            callback.onRequestFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onRequestFailed(msg);
                    }
                });
    }
}

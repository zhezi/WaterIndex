package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.SysConfigResponseBean;
import com.quanminjieshui.waterindex.contract.model.callback.CommonCallback;
import com.quanminjieshui.waterindex.contract.model.callback.SencondRequestCallback;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;

public class UserOrderModel {
    public UserOrderModel() {
    }

    public void createOrder(BaseActivity activity, String trade_id, String total, final CommonCallback callback) {

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

    public void getSysConfig(BaseActivity activity,  final SencondRequestCallback callback){
        HashMap<String,Object>params=new HashMap<>();
        params.put("param","ds_price");
        RetrofitFactory.getInstance().createService()
                .sysConfig(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<SysConfigResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<SysConfigResponseBean>>io())
                .subscribe(new BaseObserver<SysConfigResponseBean>(activity) {

                    @Override
                    protected void onSuccess(SysConfigResponseBean o) throws Exception {

                        callback.onSecondRequstSuccess(o);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onSecondRequstFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onSecondRequstFailed(e.getMessage());
                            }
                        } else {
                            callback.onSecondRequstFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onSecondRequstFailed(msg);
                    }
                });
    }

}

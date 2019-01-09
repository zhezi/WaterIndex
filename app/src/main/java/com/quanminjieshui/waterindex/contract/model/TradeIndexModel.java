package com.quanminjieshui.waterindex.contract.model;

import android.text.TextUtils;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.TradeIndexBase;
import com.quanminjieshui.waterindex.http.BaseObserver;
import com.quanminjieshui.waterindex.http.RetrofitFactory;
import com.quanminjieshui.waterindex.http.bean.BaseEntity;
import com.quanminjieshui.waterindex.http.config.HttpConfig;
import com.quanminjieshui.waterindex.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterindex.http.utils.RequestUtil;
import com.quanminjieshui.waterindex.utils.LogUtils;

import java.util.HashMap;

public class TradeIndexModel {

    public void getTradeIndex(BaseActivity activity,String type,String page, String page_size,final CommomCallback callback){
        HashMap<String,Object>params=new HashMap<>();
        if(!TextUtils.isEmpty(type)){
            params.put("type",type);
        }
        if(!TextUtils.isEmpty(type)){
            params.put("page",page);
        }
        if (!TextUtils.isEmpty(type)) {
            params.put("page_size", page_size);
        }
        RetrofitFactory.getInstance().createService()
                .tradeIndex(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<TradeIndexBase>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TradeIndexBase>>io())
                .subscribe(new BaseObserver<TradeIndexBase>(activity) {
                    @Override
                    protected void onSuccess(TradeIndexBase tradeIndexBase) throws Exception {
                        callback.onRequestSuccess(tradeIndexBase);
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

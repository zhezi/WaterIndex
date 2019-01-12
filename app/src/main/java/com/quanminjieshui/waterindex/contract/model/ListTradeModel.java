package com.quanminjieshui.waterindex.contract.model;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.ListTradeResponseBean;
import com.quanminjieshui.waterindex.beans.request.ListTradeReqParams;
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

public class ListTradeModel {

    public void getListTrade(BaseActivity activity, ListTradeReqParams params, final ListTradeCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .listTrade(RequestUtil.getRequestBeanBody(params,false))
                .compose(activity.<BaseEntity<ListTradeResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<ListTradeResponseBean>>io())
                .subscribe(new BaseObserver<ListTradeResponseBean>(activity) {
                    @Override
                    protected void onSuccess(ListTradeResponseBean listTradeResponseBean) throws Exception {
                        callBack.success(listTradeResponseBean);
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

    public interface ListTradeCallBack{
        void success(ListTradeResponseBean listTradeResponseBean);
        void failed(String msg);
    }
}

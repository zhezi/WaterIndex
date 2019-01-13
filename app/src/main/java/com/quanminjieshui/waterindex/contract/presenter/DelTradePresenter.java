package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.DelTradeReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.DelTradeModel;
import com.quanminjieshui.waterindex.contract.view.DelTradeViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class DelTradePresenter extends BasePresenter<DelTradeViewImpl> {

    private DelTradeModel delTradeModel;

    public DelTradePresenter(){}

    public DelTradePresenter(DelTradeModel delTradeModel){
        this.delTradeModel = delTradeModel;
    }

    public void delTrade(BaseActivity activity, DelTradeReqParams params){
        if (delTradeModel == null){
            delTradeModel = new DelTradeModel();
        }
        delTradeModel.delTrade(activity, params, new DelTradeModel.DelTradeCallBack() {
            @Override
            public void success() {
                if (mView!=null){
                    mView.onDelTradeSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onDelTradeFailed(msg);
                }

            }
        });
    }
}

package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.callback.CommonCallback;
import com.quanminjieshui.waterindex.contract.model.TradeIndexModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

public class TradeIndexPresenter extends BasePresenter<CommonViewImpl> {
    private TradeIndexModel tradeIndexModel;

    public TradeIndexPresenter(TradeIndexModel tradeIndexModel) {
        this.tradeIndexModel = tradeIndexModel;
    }

    public void getTradeIndex(BaseActivity activity, String type, String page, String page_size){
        if(tradeIndexModel==null){
            tradeIndexModel=new TradeIndexModel();
        }
        tradeIndexModel.getTradeIndex(activity, type, page, page_size, new CommonCallback() {
            @Override
            public void onRequestSuccess(Object bean) {
                if(mView!=null){
                    mView.onRequestSuccess(bean);
                }
            }

            @Override
            public void onRequestFailed(String msg) {
                if(mView!=null){
                    mView.onRequestFailed(msg);
                }
            }
        });

    }
}

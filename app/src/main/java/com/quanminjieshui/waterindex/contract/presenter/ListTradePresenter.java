package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.ListTradeResponseBean;
import com.quanminjieshui.waterindex.beans.request.ListTradeReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.ListTradeModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class ListTradePresenter extends BasePresenter<CommonViewImpl> {

    private ListTradeModel listTradeModel;

    public ListTradePresenter(){}

    public ListTradePresenter(ListTradeModel listTradeModel){
        this.listTradeModel = listTradeModel;
    }

    public void getListTrade(BaseActivity activity, ListTradeReqParams params){
        if (listTradeModel == null){
            listTradeModel.getListTrade(activity, params, new ListTradeModel.ListTradeCallBack() {
                @Override
                public void success(ListTradeResponseBean listTradeResponseBean) {
                    if (mView!=null){
                        mView.onRequestSuccess(listTradeResponseBean);
                    }
                }

                @Override
                public void failed(String msg) {
                    if (mView!=null){
                        mView.onRequestFailed(msg);
                    }

                }
            });
        }
    }
}

package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.AddTradeReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.AddTradeModel;
import com.quanminjieshui.waterindex.contract.view.AddTradeViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class AddTradePresenter extends BasePresenter<AddTradeViewImpl> {

    private AddTradeModel addTradeModel;

    public AddTradePresenter(){}

    public AddTradePresenter(AddTradeModel addTradeModel){
        this.addTradeModel = addTradeModel;
    }

    public void addTrade(BaseActivity activity, AddTradeReqParams params){
        if (addTradeModel == null){
            addTradeModel = new AddTradeModel();
        }
        addTradeModel.addTrade(activity, params, new AddTradeModel.AddTradeCallBack() {
            @Override
            public void success() {
                if (mView!=null){
                    mView.onAddTradeSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onAddTradeFailed(msg);
                }

            }
        });
    }
}

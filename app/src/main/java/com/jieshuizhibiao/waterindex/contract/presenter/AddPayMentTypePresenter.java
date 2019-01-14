package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.request.AddPayMentTypeReqParams;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.AddPayMentTypeModel;
import com.jieshuizhibiao.waterindex.contract.view.AddPayMentTypeViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class AddPayMentTypePresenter extends BasePresenter<AddPayMentTypeViewImpl> {

    private AddPayMentTypeModel addPayMentTypeModel;

    public AddPayMentTypePresenter(){}

    public AddPayMentTypePresenter(AddPayMentTypeModel addPayMentTypeModel){
        this.addPayMentTypeModel = addPayMentTypeModel;
    }

    public void addPayMentType(BaseActivity activity, AddPayMentTypeReqParams params){
        if (addPayMentTypeModel == null){
            addPayMentTypeModel = new AddPayMentTypeModel();
        }
        addPayMentTypeModel.addPayMentType(activity, params, new AddPayMentTypeModel.AddPayMentTypeCallBack() {
            @Override
            public void success() {
                if (mView!=null){
                    mView.onAddPaymentTypeSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onAddPaymentTypeFailed(msg);
                }

            }
        });
    }
}

package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.AddPayMentTypeReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.AddPayMentTypeModel;
import com.quanminjieshui.waterindex.contract.view.AddPayMentTypeViewImpl;

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

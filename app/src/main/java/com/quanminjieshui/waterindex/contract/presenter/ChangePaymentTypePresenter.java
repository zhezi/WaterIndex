package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.ChangePayMentTypeReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.ChangePaymentTypeModel;
import com.quanminjieshui.waterindex.contract.view.ChangePaymentTypeViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class ChangePaymentTypePresenter extends BasePresenter<ChangePaymentTypeViewImpl> {

    private ChangePaymentTypeModel changePaymentTypeModel;

    public ChangePaymentTypePresenter(){}

    public ChangePaymentTypePresenter(ChangePaymentTypeModel changePaymentTypeModel){
        this.changePaymentTypeModel = changePaymentTypeModel;
    }

    public void changePaymentType(BaseActivity activity, ChangePayMentTypeReqParams params){
        if (changePaymentTypeModel==null){
            changePaymentTypeModel = new ChangePaymentTypeModel();
        }
        changePaymentTypeModel.changePaymentType(activity, params, new ChangePaymentTypeModel.ChangePayMentTypeCallBack() {
            @Override
            public void success() {
                if (mView!=null){
                    mView.changePaymentTyoeSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.changePaymentTypeFalied(msg);
                }

            }
        });
    }

}

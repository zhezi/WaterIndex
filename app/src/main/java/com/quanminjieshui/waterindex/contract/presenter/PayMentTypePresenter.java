package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.BaseBean;
import com.quanminjieshui.waterindex.beans.PayMentResponseBean;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.PayMentTypeModel;
import com.quanminjieshui.waterindex.contract.view.CommonViewImpl;

/**
 * Created by songxiaotao on 2019/1/12.
 * Class Note:
 */

public class PayMentTypePresenter extends BasePresenter<CommonViewImpl> {

    private PayMentTypeModel payMentTypeModel;

    public PayMentTypePresenter(){}

    public PayMentTypePresenter(PayMentTypeModel payMentTypeModel){
        this.payMentTypeModel = payMentTypeModel;
    }

    public void payMentType(BaseActivity activity, BaseBean params){
        if (payMentTypeModel == null){
            payMentTypeModel = new PayMentTypeModel();
        }
        payMentTypeModel.payMentType(activity, params, new PayMentTypeModel.PayMentTypeCallBack() {
            @Override
            public void success(PayMentResponseBean payMentResponseBean) {
                if (mView!=null){
                    mView.onRequestSuccess(payMentResponseBean);
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

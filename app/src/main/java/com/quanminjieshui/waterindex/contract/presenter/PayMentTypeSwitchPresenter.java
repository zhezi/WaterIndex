package com.quanminjieshui.waterindex.contract.presenter;

import com.quanminjieshui.waterindex.base.BaseActivity;
import com.quanminjieshui.waterindex.beans.request.PayMentTypeSwitchReqParams;
import com.quanminjieshui.waterindex.contract.BasePresenter;
import com.quanminjieshui.waterindex.contract.model.PayMentTypeSwitchModel;
import com.quanminjieshui.waterindex.contract.view.PayMentTypeSwitchViewImpl;

/**
 * Created by songxiaotao on 2019/1/13.
 * Class Note:
 */

public class PayMentTypeSwitchPresenter extends BasePresenter<PayMentTypeSwitchViewImpl> {

    private PayMentTypeSwitchModel payMentTypeSwitchModel;

    public PayMentTypeSwitchPresenter(){}

    public PayMentTypeSwitchPresenter(PayMentTypeSwitchModel payMentTypeSwitchModel){
        this.payMentTypeSwitchModel = payMentTypeSwitchModel;
    }

    public void setPayMentTypeSwitch(BaseActivity activity, PayMentTypeSwitchReqParams params){
        if (payMentTypeSwitchModel == null){
            payMentTypeSwitchModel = new PayMentTypeSwitchModel();
        }
        payMentTypeSwitchModel.payMentTypeSwitch(activity, params, new PayMentTypeSwitchModel.PayMentTypeSwitchCallBack() {
            @Override
            public void success() {
                if (mView!=null){
                    mView.onPaymentTypeSwitchSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onPaymentTypeSwitcFailed(msg);
                }

            }
        });

    }
}

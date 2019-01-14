package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.TraderUnpayModel;
import com.jieshuizhibiao.waterindex.contract.model.callback.CommonCallback;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;

public class TraderUnpayPresenter extends BasePresenter<CommonViewImpl> {

    private TraderUnpayModel model;

    public TraderUnpayPresenter(TraderUnpayModel model) {
        this.model = model;
    }

    public void buyerUnpay(BaseActivity activity, long order_id) {
        if (model == null) {
            model = new TraderUnpayModel();

        }

        model.buyerUnpay(activity, order_id, new CommonCallback() {
            @Override
            public void onRequestSuccess(Object bean) {
                if (mView != null) {
                    mView.onRequestSuccess(bean);
                }
            }

            @Override
            public void onRequestFailed(String msg) {
                if (mView != null) {
                    mView.onRequestFailed(msg);
                }
            }
        });
    }

    public void sellerUnpay(BaseActivity activity, long order_id) {
        if (model == null) {
            model = new TraderUnpayModel();

        }

        model.sellerUnpay(activity, order_id, new CommonCallback() {
            @Override
            public void onRequestSuccess(Object bean) {
                if (mView != null) {
                    mView.onRequestSuccess(bean);
                }
            }

            @Override
            public void onRequestFailed(String msg) {
                if (mView != null) {
                    mView.onRequestFailed(msg);
                }
            }
        });
    }

}

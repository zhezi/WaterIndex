package com.jieshuizhibiao.waterindex.contract.presenter;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.contract.BasePresenter;
import com.jieshuizhibiao.waterindex.contract.model.TraderModel;
import com.jieshuizhibiao.waterindex.contract.model.callback.CommonCallback;
import com.jieshuizhibiao.waterindex.contract.view.CommonViewImpl;

public class TraderPresenter extends BasePresenter<CommonViewImpl> {

    private TraderModel model;

    public TraderPresenter(TraderModel model) {
        this.model = model;
    }

    public void buyerUnpay(BaseActivity activity, long order_id) {
        if (model == null) {
            model = new TraderModel();

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
            model = new TraderModel();

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

    public void buyerPaid(BaseActivity activity, long order_id) {
        if (model == null) {
            model = new TraderModel();

        }

        model.buyerPaid(activity, order_id, new CommonCallback() {
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

    public void sellerPaid(BaseActivity activity, long order_id) {
        if (model == null) {
            model = new TraderModel();

        }

        model.sellerPaid(activity, order_id, new CommonCallback() {
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

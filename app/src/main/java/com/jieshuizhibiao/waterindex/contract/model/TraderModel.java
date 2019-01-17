package com.jieshuizhibiao.waterindex.contract.model;

import com.jieshuizhibiao.waterindex.base.BaseActivity;
import com.jieshuizhibiao.waterindex.beans.buyerpaid.BuyerPaidResponse;
import com.jieshuizhibiao.waterindex.beans.sellerpaid.SellerPaidResponse;
import com.jieshuizhibiao.waterindex.beans.unpay.BuyerUnpayResponse;
import com.jieshuizhibiao.waterindex.beans.unpay.SellerUnpayResponse;
import com.jieshuizhibiao.waterindex.contract.model.callback.CommonCallback;
import com.jieshuizhibiao.waterindex.http.BaseObserver;
import com.jieshuizhibiao.waterindex.http.RetrofitFactory;
import com.jieshuizhibiao.waterindex.http.bean.BaseEntity;
import com.jieshuizhibiao.waterindex.http.config.HttpConfig;
import com.jieshuizhibiao.waterindex.http.utils.ObservableTransformerUtils;
import com.jieshuizhibiao.waterindex.http.utils.RequestUtil;
import com.jieshuizhibiao.waterindex.utils.LogUtils;

import java.util.HashMap;

public class TraderModel {

    public void buyerUnpay(BaseActivity activity, long order_id, final CommonCallback callback) {

        RetrofitFactory.getInstance().createService()
                .buyerUnpay(RequestUtil.getRequestHashBody(initParams(order_id), false))
                .compose(activity.<BaseEntity<BuyerUnpayResponse>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<BuyerUnpayResponse>>io())
                .subscribe(new BaseObserver<BuyerUnpayResponse>() {
                    @Override
                    protected void onSuccess(BuyerUnpayResponse buyerUnpayResponse) throws Exception {
                        callback.onRequestSuccess(buyerUnpayResponse);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFailed(e.getMessage());
                            }
                        } else {
                            callback.onRequestFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        callback.onRequestFailed(msg);
                    }
                });

    }

    public void sellerUnpay(BaseActivity activity, long order_id, final CommonCallback callback) {

        RetrofitFactory.getInstance().createService()
                .sellerUnpay(RequestUtil.getRequestHashBody(initParams(order_id), false))
                .compose(activity.<BaseEntity<SellerUnpayResponse>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<SellerUnpayResponse>>io())
                .subscribe(new BaseObserver<SellerUnpayResponse>() {
                    @Override
                    protected void onSuccess(SellerUnpayResponse sellerUnpayResponse) throws Exception {
                        callback.onRequestSuccess(sellerUnpayResponse);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFailed(e.getMessage());
                            }
                        } else {
                            callback.onRequestFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        callback.onRequestFailed(msg);
                    }
                });

    }

    public void buyerPaid(BaseActivity activity, long order_id, final CommonCallback callback) {

        RetrofitFactory.getInstance().createService()
                .buyerPaid(RequestUtil.getRequestHashBody(initParams(order_id), false))
                .compose(activity.<BaseEntity<BuyerPaidResponse>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<BuyerPaidResponse>>io())
                .subscribe(new BaseObserver<BuyerPaidResponse>() {
                    @Override
                    protected void onSuccess(BuyerPaidResponse buyerPaidResponse) throws Exception {
                        callback.onRequestSuccess(buyerPaidResponse);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFailed(e.getMessage());
                            }
                        } else {
                            callback.onRequestFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        callback.onRequestFailed(msg);
                    }
                });

    }

    public void sellerPaid(BaseActivity activity, long order_id, final CommonCallback callback) {

        RetrofitFactory.getInstance().createService()
                .sellerPaid(RequestUtil.getRequestHashBody(initParams(order_id), false))
                .compose(activity.<BaseEntity<SellerPaidResponse>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<SellerPaidResponse>>io())
                .subscribe(new BaseObserver<SellerPaidResponse>() {
                    @Override
                    protected void onSuccess(SellerPaidResponse sellerPaidResponse) throws Exception {
                        callback.onRequestSuccess(sellerPaidResponse);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFailed(e.getMessage());
                            }
                        } else {
                            callback.onRequestFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        callback.onRequestFailed(msg);
                    }
                });

    }

    private HashMap<String, Object> initParams(long order_id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("order_id", String.valueOf(order_id));
        return params;
    }


}

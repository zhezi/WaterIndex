package com.jieshuizhibiao.waterindex.beans.sellerpaid;

import com.jieshuizhibiao.waterindex.beans.unpay.PayInfo;

public class SellerPaidResponse {
    private SellerPaidOrderInfo order_info;
    private PayInfo pay_info;

    public SellerPaidOrderInfo getOrder_info() {
        return order_info;
    }

    public PayInfo getPay_info() {
        return pay_info;
    }
}

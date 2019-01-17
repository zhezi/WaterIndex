package com.jieshuizhibiao.waterindex.beans.buyerpaid;

import com.jieshuizhibiao.waterindex.beans.unpay.PayInfo;

public class BuyerPaidResponse {
    private BuyerPaidOrderInfo orderinfo;
    private PayInfo pay_info;

    public BuyerPaidOrderInfo getOrderinfo() {
        return orderinfo;
    }

    public PayInfo getPay_info() {
        return pay_info;
    }
}

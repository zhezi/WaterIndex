package com.jieshuizhibiao.waterindex.beans.paid;

import com.jieshuizhibiao.waterindex.beans.appeal.PayInfo;

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

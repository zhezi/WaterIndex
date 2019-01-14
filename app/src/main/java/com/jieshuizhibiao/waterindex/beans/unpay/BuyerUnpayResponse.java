package com.jieshuizhibiao.waterindex.beans.unpay;

import java.util.List;

public class BuyerUnpayResponse {
    private BuyerOrderInfo order_info;
    private List<PayInfo> pay_info_list;

    public BuyerOrderInfo getOrder_info() {
        return order_info;
    }

    public List<PayInfo> getPay_info_list() {
        return pay_info_list;
    }
}

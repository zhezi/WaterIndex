package com.jieshuizhibiao.waterindex.beans.unpay;

import java.util.List;

public class SellerUnpayResponse {
    private SellerOrderInfo order_info;
    private List<PayInfo> pay_info_list;

    public SellerOrderInfo getOrder_info() {
        return order_info;
    }

    public List<PayInfo> getPay_info_list() {
        return pay_info_list;
    }
}

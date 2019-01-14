package com.jieshuizhibiao.waterindex.contract.view;

import com.jieshuizhibiao.waterindex.beans.CreateOrderResponseBean;
import com.jieshuizhibiao.waterindex.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public interface CreateOrderViewImpl extends IBaseViewImpl {
    void onCreateOrderSuccess(CreateOrderResponseBean createOrderResponseBean);
    void onCreateOrderFailed(String msg);
}
